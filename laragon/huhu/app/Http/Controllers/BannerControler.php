<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
class BannerControler extends Controller
{
  public function getBanner(){
    $banner = DB::table('banners')->get();
    return response()->json($banner);
   }
   public function getProductWithId(Request $request){
    $pro=Product::where('id',$request->input('id'))->get();
    return response()->json($pro);
   }
}

//     public function insert(Request $request1)
//   {
//     $data = array(
//       'ten' => $request1->input('ten'),
//       'hinh' => $request1->input('hinh'),
//     );
//     $last_id = DB::table('banner')
//       ->insert($data); // insert và lấy ra id cuối cùng của bảng
//     return response()->json($last_id);
//   }
// }