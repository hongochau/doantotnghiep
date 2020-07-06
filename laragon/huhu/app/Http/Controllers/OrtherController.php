<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use App\User1s;

class OrtherController extends Controller
{
    public function getAllBillWithType(Request $request) // check nếu chưa tồn tại thì tạo mới 
    {
        $stt = $request->input('stt');
        $bill = DB::table('hoadon')
            ->where('Status', $stt)
            ->leftJoin('user1s', 'hoadon.IdUser', '=', 'user1s.id')
            ->select('user1s.name','user1s.imagefb', 'Hoadon.*')
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
        ->orderBy('daban','DESC')
        ->get();
        
        return response()->json($result);
        
    }
    
}
