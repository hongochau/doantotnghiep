<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class AdminController extends Controller
{
    public function putnotifi(Request $req){
        if (!defined('API_ACCESS_KEY')) 
        define( 'API_ACCESS_KEY', 'AAAAVseURUI:APA91bFjzCGm-jGQdag8jVgXfCgVVzLiVs-cwgmm71AfpFL7MMUKcPR0FUNaG18_3HMMXu04kW6mdW-cAoXDqs91DiCvPCL8DV44_cGaHGi155pc_dR-9TeejIIChRLjs1dWqt1d3Thc' );
        // prep the bundle
        $msg = array
        (
            // 'title'     => "thông báo",
            // 'message'     => "bạn có mặt hàng mới",
            // 'body'     => "1",
            // 'img'     => "https://media.tinmoi.vn/upload/honghanh/2019/05/27/133248-truong-giang-lo-anh-gia-gai-sau-khi-cuoi-nha-phuong-fan-ban-loan.jpg",
                'title'=>$req->input('title'),
                'message'=>$req->input('message'),
                'body'=>$req->input('body'),
                'img'=>$req->input('img'),
        );


        $fields = array
        (
            'to'     => "/topics".$req->input('topic'),
            "content_available" => true,
            'data'            => $msg
        );

        $headers = array
        (
            'Authorization: key='.API_ACCESS_KEY,
            'Content-Type: application/json'
        );

        $ch = curl_init();
        curl_setopt( $ch,CURLOPT_URL, 'https://fcm.googleapis.com/fcm/send');
        curl_setopt( $ch,CURLOPT_POST, true );
        curl_setopt( $ch,CURLOPT_HTTPHEADER, $headers );
        curl_setopt( $ch,CURLOPT_RETURNTRANSFER, true );
        curl_setopt( $ch,CURLOPT_SSL_VERIFYPEER, false );
        curl_setopt( $ch,CURLOPT_POSTFIELDS, json_encode( $fields ) );
        $result = curl_exec($ch );
        curl_close( $ch );
        return $result;
    }
}
