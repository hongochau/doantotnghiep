<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use App\Product;
class ProductController extends Controller
{
    public function getAll1(){
        $pro = DB::table('products')->paginate(15);
        return response()->json($pro);
       }

       public function getCount(){ 
        $count = Product::count(); 
        return response()->json($count);
       }
       // trả về detail của sản phẩm 
       public function getimageSP(Request $request){
        $user = DB::table('image_products')->where('idProduct',$request->input('id'))->get();
        return response()->json($user);
       }
       public function getSPLQ(Request $request){ // sản phẩm liên quan 
        $user = DB::table('products')->where('idProductType',$request->input('idProductype'))->paginate(5);
        return response()->json($user);
       }
       

       public function getfullSPLQ(Request $request){ // full sản phẩm liên quan 
        $user = DB::table('products')->where('idProductType',$request->input('idProduct'))->get();
        return response()->json($user);
       }
       public function getfullfeedback(Request $request){ // full feedback
        $user = DB::table('feedback_products')->where('idProduct',$request->input('idProduct'))->get();
        return response()->json($user);
       }
       // sản phẩm liên quan
       public function getnewpro(Request $request){
        $pro = DB::table('products')->latest('id')->paginate(16);
          
        return response()->json($pro);
       }

       public function LoadmoreWithproductType(Request $request){
        $pro = DB::table('products')
        ->where('idProductType',$request->input('idProductType'))
        ->paginate(15);
        return response()->json($pro);
        // return response()->json([ // trả về response
        //     'status' => 'SUCCESS',
        //      'mess' => 'SUCCESS',
        //     'data'=> $pro
        //     ]);
       }
 
}
