<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use App\FeedbackProduct;
class FeedBackController extends Controller
{
    public function getfeedback(Request $request){
        $feed = DB::table('feedback_products')
        ->leftJoin('user1s', 'feedback_products.idUser', '=', 'user1s.id')
        ->select('user1s.name','user1s.imagefb', 'feedback_products.*')
        ->where('idProduct', $request->input('idProduct'))
        ->orderBy('rate', 'desc') // sắp xếp giảm dần
        ->paginate(5);
        return response()->json($feed);
    }

    public function pushFeedback(Request $request){
        $data =  json_decode($request->getContent(), true);
          
        DB::table('feedback_products')->insert($data);
          

        $avgpro = DB::table('feedback_products')
                ->where('idProduct',$data['idproduct'])
                ->avg('rate');
            
                $hmm= DB::table('products')
                ->where('id', $data['idproduct'])
                ->update(['rate' => $avgpro]);
                
                return response()->json($hmm);

    }

    public function getfeedbackWithIdUser(Request $request){
        $feed = DB::table('feedback_products')
        ->leftJoin('products', 'feedback_products.idProduct', '=', 'products.id')
        ->select('feedback_products.*','products.*')
        ->where('idUser', $request->input('idUser'))
        ->get();
        return response()->json($feed);
    }
}
