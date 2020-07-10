<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use App\Products;

class OrtherController extends Controller
{
    public function getAllBillWithType(Request $request) // check nếu chưa tồn tại thì tạo mới 
    {
        $stt = $request->input('stt');
        $bill = DB::table('hoadon')
            ->where('Status', $stt)
            ->leftJoin('user1s', 'hoadon.IdUser', '=', 'user1s.id')
            ->select('user1s.name', 'user1s.imagefb', 'Hoadon.*')
            ->get();
        if (Count($bill) > 0) {
            return response()->json([
                'status' => 'SUCCESS',
                'mess' => 'SUCCESS',
                'data' => $bill
            ]);
        }
        return response()->json([
            'status' => 'FAIL',
            'mess' => 'không có bill',
            'data' => null
        ]);
    }
    public function changeStatus(Request $request)
    {
        $stt = $request->input('stt');
        $idbill = $request->input('idbill');
        $result = DB::table('hoadon')
            ->where('id', $idbill)
            ->update(['Status' => $stt]);
        return response()->json([
            'status' => 'SUCCESS',
            'mess' => 'SUCCESS',
            'data' => null
        ]);
    }

    public function getDescProduct(Request $request)
    {
        $result = DB::table('products')
            ->take(15)
            ->orderBy('daban', 'DESC')
            ->get();

        return response()->json($result);
    }

    public function getHoadonbeetween(Request $request)
    {
        $from = $request->input('from');
        $to = $request->input('to');

        $billus = DB::table('Hoadon')
            ->whereBetween('Bookingtime', [$from, $to])
            ->where('Status', 'Đã giao')
            ->leftJoin('user1s', 'hoadon.IdUser', '=', 'user1s.id')
            ->select('user1s.name', 'user1s.imagefb', 'Hoadon.*')
            ->get();
        return response()->json($billus);
    }

    // quản lý sản phẩm 
    public function getAllProduct()
    {
        $pro = DB::table('products')->get();
        return response()->json([
            'status' => 'SUCCESS',
            'mess' => 'đã tạo một sản phẩm mới ',
            'data' => $pro
        ]);
    }

    public function CreateProduct(Request $request)
    {
        $pro = new Products();
        $pro->idProductType = $request->input('idProductType');
        $pro->name = $request->input('name');
        $pro->describe = $request->input('describe');
        $pro->price = $request->input('price');
        $pro->image = $request->input('image');
        $pro->save();
        return response()->json([
            'status' => 'SUCCESS',
            'mess' => 'đã tạo một sản phẩm mới ',
            'data' => $pro
        ]);
    }

    public function updateProduct(Request $request)
    {
        $pro = Products::find($request->input('id'));
        if ($pro != null) {

            $pro->idProductType = $request->input('idProductType');
            $pro->name = $request->input('name');
            $pro->describe = $request->input('describe');
            $pro->price = $request->input('price');
            $pro->image = $request->input('image');

            $pro->save();
            return response()->json([
                'status' => 'SUCCESS',
                'mess' => 'update thành công rồi nè :v ',
                'data' => null
            ]);
        }
        return response()->json([
            'status' => 'FAIL',
            'mess' => 'không tìm thấy thằng nào :v ',
            'data' => null
        ]);
    }
    public function deleteProduct(Request $request)
    {
        $pro = Products::find($request->input('id'));
        $pro->delete();
        return response()->json([
            'status' => 'SUCCESS',
            'mess' => 'đã xóa sản phẩm ',
            'data' => null
        ]);
    }
}
