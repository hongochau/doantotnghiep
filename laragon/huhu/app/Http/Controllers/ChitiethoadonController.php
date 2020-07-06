<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use App\Bill;

class ChitiethoadonController extends Controller
{
  public function HoaDon(Request $request) // check nếu chưa tồn tại thì tạo mới 
  {
    $idUser = $request->input('idUser');
    $status = $request->input('status');
    $totalprice = $request->input('totalprice');
    $bookingtime = $request->input('bookingtime');

    $id = DB::table('hoadon')
      ->insertGetId(
        [
          'idUser' => $idUser,
          'status' =>  $status,
          'totalprice' => $totalprice,
          'bookingtime' => $bookingtime,
        ]
      );
    return response()->json($id);
  }

  public function ChiTietHoaDon(Request $request) // insert Array list 
  {
    $data =  json_decode($request->getContent(), true);

    $last_id = DB::table('chitiethoadon')
      ->insert($data);

    foreach ($data as $value) {  // ddocj json nef 
      DB::table('products')
      ->where('id',$value['Masanpham'])
      ->increment('daban', $value['Soluong']);
    }
    return response()->json($last_id);
  }

  public function getHoaDon(Request $request)
  {
    $billus = DB::table('hoadon') // tìm thằng id user và status = b1 
      ->where('idUser', $request->input('idUser'))
      ->where('status', $request->input('status'))
      ->get();
    return response()->json($billus);
  }

  public function getChiTietHoaDon(Request $request)
  {
    $billus = DB::table('chitiethoadon') // tìm thằng id
      ->where('idhoadon', $request->input('idhoadon'))
      ->get();
    return response()->json($billus);
  }
}
