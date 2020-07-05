<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use App\Quanlysanpham;
class QuanlysanphamController extends Controller
{
    public function Sanphamdaxem(Request $request){
        $quanlysanpham = DB::table('hoadon')
        ->where('idUser', $request->input('idUser'))
        ->where('status', $request->input('status'))->get();
        return response()->json($quanlysanpham);
    }
}
