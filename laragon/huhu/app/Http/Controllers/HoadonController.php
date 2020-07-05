<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use App\Bill;

class BillController extends Controller
{
    public function CreateBill(Request $request)
    { // b1 thêm vào giỏ hàng nếu đã có rồi thì update lại count
        // $feed = DB::table('feedback_products')
        // ->leftJoin('user1s', 'feedback_products.idUser', '=', 'user1s.id')
        // ->select('user1s.name','user1s.imagefb', 'feedback_products.*')
        // ->where('idProduct', $request->input('idProduct'))
        // ->orderBy('rate', 'desc') // sắp xếp giảm dần
        // ->paginate(5);

        $idUser = $request->input('idUser');
        $nameUser = $request->input('nameUser');
        $idProduct = $request->input('idProduct');
        $price = $request->input('price');
        $count = $request->input('count');
        $status = $request->input('status');
        $voucher = $request->input('voucher');
        $billdate = $request->input('billdate');

        $check = DB::table('bills')
            ->where('idUser', $request->input('idUser'))
            ->where('idProduct', $request->input('idProduct'))
            ->where('status', 'b1')
            ->first();

        if ($check != null) {
            $bill = $check->count = $check->count + 1;
            DB::table('bills')
                ->where('idUser', $request->input('idUser'))
                ->where('idProduct', $request->input('idProduct'))
                ->update(['count' => $check->count]);
            return response()->json([
                'status' => 'SUCCESS',
                'mess' => 'UP',
                'data' => null
            ]);
        } else {
            $id = DB::table('bills')
                ->insertGetId(
                    [
                        'idUser' => $idUser,
                        'nameUser' =>  $nameUser,
                        'idProduct' => $idProduct,
                        'price' => $price,
                        'count' => $count,
                        'status' => $status,
                        'voucher' => $voucher,
                        'billdate' => $billdate
                    ]
                );

            $pro = DB::table('bills')
                ->where('id', $id)->get();
            return response()->json([
                'status' => 'SUCCESS',
                'mess' => 'IN',
                'data' => $pro
            ]);
        }
    }
    public function getBill(Request $request)
    { //lay san pham da dat hang
        $pro = DB::table('bills')
            ->where('idUser', $request->input('idUser'))
            ->where('status', $request->input('status'))
            ->leftJoin('products', 'bills.idProduct', '=', 'products.id')
            ->select('products.name', 'products.image', 'products.origin', 'bills.*')
            ->get();
        return response()->json([
            'status' => 'SUCCESS',
            'mess' => 'MESS',
            'data' => $pro
        ]);
    }

    public function deleteBill(Request $request)
    {
        $deletebill = DB::table('bills')
            ->where('idUser', $request->input('idUser'))
            ->where('idProduct', $request->input('idProduct'))
            ->delete();
        return response()->json($deletebill);
    }

    public function updateBill(Request $request)
    {
        DB::table('bills')
            ->where('idUser', $request->input('idUser'))
            ->where('idProduct', $request->input('idProduct'))
            ->update(
                [
                    'count' => $request->input('count'),
                    'status' => $request->input('status')
                ]
            );
        return response()->json([
            'status' => 'SUCCESS',
            'mess' => 'SUCCESS',
            'data' => null
        ]);
    }

    public function getCountBill(Request $request)
    {
        $count = DB::table('bills')
            ->where('idUser', $request->input('idUser'))
            ->where('status', $request->input('status'))
            ->count();
        return response()->json($count);
    }

    // đặt hàng
    public function orderProduct(Request $request)
    {
        $count = DB::table('bills')
            ->where('idUser', $request->input('idUser'))
            ->where('status', $request->input('status'))
            ->update(
                [
                    'status' => $request->input('statusUpdate')
                ]
            );

        $bills = DB::table('bills') // lấy ra danh sách bills
            ->where('idUser', $request->input('idUser'))
            // ->where('idProduct',$request->input('idProduct'))
            ->where('status', 'b2')
            ->get();

        $product = array();
        foreach ($bills as $t) { // lấy ra list product tương đương với bills
            $pro = DB::table('products')
                ->where('id', $t->idProduct)
                ->get()->first();
            array_push($product, $pro);
        }
        foreach ($product as $i) {
            $idproduct = $i->id;
            foreach ($bills as $j) {
                if ($j->idProduct == $idproduct) {
                    $amoutupdate = $i->amount - $j->count;
                    DB::table('products')
                        ->where('id', $i->id)
                        ->update(
                            [
                                'amount' => $amoutupdate
                            ]
                        );
                }
            }
        }
        return response()->json([
            'status' => 'SUCCESS',
            'mess' => 'ORDER',
            'data' => null
        ]);
    }
// viết ngày 18/05/2020
    public function loadcart(Request $request){
    
        $idProduct = $request->input('idProduct');

        $pro = DB::table('products')
        ->where('id', $idProduct)
        ->get();
        return response()->json($pro);
       
    }

    
}
