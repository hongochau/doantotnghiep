<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use App\Bill;

class BillUserController extends Controller
{
    public function CreateBillUser(Request $request) // check nếu chưa tồn tại thì tạo mới 
    {
        $idUser = $request->input('idUser');
        $price = $request->input('price');
        $count = $request->input('count');
        $status = $request->input('status');
        $orderdate = $request->input('orderdate');

        $voucher = $request->input('voucher');
        $idProduct = $request->input('idProduct');

           $billus=DB::table('billuser') // tìm thằng id user và status = b1 
            ->where('idUser', $request->input('idUser'))
            ->where('status', $request->input('status'))
            ->get()->first();
            
             if($billus==null){ // chưa có thì tạo thăng mới 
                $id=DB::table('billuser')
                ->insertGetId(
                    ['idUser' => $idUser,
                     'price' =>  $price,
                     'count' => $count,
                     'status' => $status,
                     'orderdate' => $orderdate,
                     
                     ]);
        
                     $billsIS = array(
                        'idBill' =>$id,
                        'idProduct' => $idProduct,
                        'price' => $price,
                        'count' => $count,
                        'status' => $status,
                        'voucher' => $voucher,
                        'billdate' => $orderdate,
                       );
                       $billis=DB::table('bills')
                       ->insert($billsIS);
                return response()->json([
                    'status' => 'SUCCESS',
                    'mess' => 'INSERTOK',
                    'data' =>null
                    ]); 
                 }else{ // nếu có rồi thì thêm vào bills và update thằng hóa đơn 

                $checkpro=DB::table('bills') // tìm thằng id user và status = b1 
                ->where('idBill', $request->input('idBill'))
                ->where('idProduct', $request->input('idProduct'))
                ->first();
                   
                    if($checkpro==null){
                        $billsIS = array(
                            'idBill' =>$billus->id,
                            'idProduct' => $idProduct,
                            'price' => $price,
                            'count' => $count,
                            'status' => $status,
                            'voucher' => $voucher,
                            'billdate' => $orderdate,
                           );
                            DB::table('bills')
                           ->insert($billsIS);
    
                           $count = DB::table('bills')
                           ->where('idBill',$billus->id)
                           ->count();
    
                           $total=$this->totalmoney($billus->id);
                            DB::table('billuser') // update lại bảng bills user
                           ->where('id',$billus->id)
                           ->update(
                            [
                                'count' => $count,
                                'price'=>$total
                            ]
                        );
                       
                    }else{ // neesu sp ddax cos trong gio hafng
                        $countupdate=$checkpro->count+1;

                      

                        DB::table('bills') // update lại bảng bills user
                        ->where('idbill',$billus->id)
                        ->where('idProduct',$idProduct)
                        ->update(
                         [
                             'count' => $countupdate,
                         ]
                        );
    
                           $countbills = DB::table('bills')
                           ->where('idBill',$billus->id)
                           ->get();
                            $count=0;
                           foreach($countbills as $co){
                            $count+=$co->count;
                           }

                           // ddoanj nayf chuwa up date  được nè hic chic
                           $total=$this->totalmoney($billus->id);
                            DB::table('billuser') // update lại bảng bills user
                           ->where('id',$billus->id)
                           ->update(
                            [
                                'count' => $count,
                                'price'=>$total
                            ]
                        );
                    }
                 }
    }

    public function UpdateCount(Request $request) // xử lý tăng giảm trong giỏ hàng 
    {
        $idbill = $request->input('idBill');
        $count = $request->input('count');
        $countUS = $request->input('countUS');

            DB::table('bills')
            ->where('idBill', $idbill)
            ->update(
                [
                    'count' => $count,
                ]
            );

            DB::table('billuser')
            ->where('id', $idbill)
            ->update(
                [
                'count' => $countUS,
                ]
            );
            return response()->json([
                'status' => 'SUCCESS',
                'mess' => 'UPDATECOUNT',
                'data' =>null
                ]); 
    }
    // lấy ra danh sách hóa đơn 
    public function getBillUser(Request $request)
    { 
        $pro = DB::table('billuser')
            ->where('idUser', $request->input('idUser'))
            ->where('status', $request->input('status'))
            ->get();   
        return response()->json([
            'status' => 'SUCCESS',
            'mess' => 'GETORDER',
            'data' => $pro
        ]);
    }
    // danh sách giỏ hàng 
    public function getBills(Request $request)
    { 
        $pro = DB::table('bills')
        ->where('idbill', $request->input('idbill'))
        //->where('status', $request->input('status'))
        ->leftJoin('products', 'bills.idProduct', '=', 'products.id')
        ->select('products.name', 'products.image', 'products.origin', 'bills.*')
        ->get();
    
        if($pro!=null){
            return response()->json([
                'status' => 'SUCCESS',
                'mess' => 'DEAILOK',
                'data' => $pro
            ]);
        }else{
            return response()->json([
                'status' => 'SUCCESS',
                'mess' => 'DETAILNULL',
                'data' => null
            ]);
        }
        
    }

    public function deletebills(Request $request){
        $deletebill = DB::table('bills')
        ->where('id', $request->input('id'))
        ->delete();
        if($deletebill!=null){
            return response()->json([
                'status' => 'SUCCESS',
                'mess' => 'DELOK',
                'data' =>null
                ]); 
        }else{
            return response()->json([
                'status' => 'SUCCESS',
                'mess' => 'DELFALSE',
                'data' =>null
                ]);  
        }
       
    }

    public function bunghang(Request $request){
        $deletebill = DB::table('billuser')
        ->where('id', $request->input('id'))
        ->delete();
        if($deletebill!=null){
            return response()->json([
                'status' => 'SUCCESS',
                'mess' => 'BUNGOK',
                'data' =>null
                ]); 
        }else{
            return response()->json([
                'status' => 'SUCCESS',
                'mess' => 'BUNGFALSE',
                'data' =>null
                ]);  
        }
       
    }

    public function addnewbills(Request $request){
        
        
    }


    // các hàm chức năng 
    public function totalmoney($idBill){
        $pro = DB::table('bills')
        ->where('idbill', $idBill)
        ->get();
        $total=0;
        foreach ($pro as $t) { // lấy ra list product tương đương với bills
           $total+=$t->count * $t->price;
        }

        return $total;

    }



  
}
