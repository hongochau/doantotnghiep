<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use App\User1s;

class LoginController extends Controller
{
    public function Register(Request $request){
        $user=new User1s();
        $user->password=$request->input('password');
        $user->phone=$request->input('phone');
        $user->account=$request->input('account');
        $us=User1s::where('account',$request->input('account'))->first();
        $mail=User1s::where('phone',$request->input('phone'))->first();

        if($us!=null){
            return response()->json([
                'status' => 'FAIL',
                 'mess' => 'Đã có người đăng ký account này ',
                'data'=> null
                ]);  
        }
        if($mail!=null){
            return response()->json([
                'status' => 'FAIL',
                 'mess' => 'Đã có người đăng ký bằng mail này ',
                'data'=> null
                ]);
        }else{
            $user->save();
                return response()->json([
                'status' => 'SUCCESS',
                 'mess' => 'Đăng ký thành công ',
                'data'=> $user
                ]);
                }
            }
        public function CheckUser(Request $request){ 
            $mail=User1s::where('phone',$request->input('phone'))
            ->where('password',$request->input('password')) 
            ->first();
            if($mail==null){ 
                return response()->json([ 
                    'status' => 'FAIL',
                     'mess' => 'Sai tên đăng nhập hoặc mật khẩu',
                    'data'=> null
                    ]);
            }else{ 
                    return response()->json([
                    'status' => 'SUCCESS',
                     'mess' => 'Đăng Nhập thành công',
                    'data'=> $mail
                    ]);
    }
}
        public function UpdateThongTinTaiKhoan (Request $request){
            $user=User1s::where('account',$request->input('account'))
            ->update(['name'=>$request->input('name'),
            'email'=>$request->input('email'),
            'address'=>$request->input('address'),
            'phone'=>$request->input('phone')]);        
            $user=User1s::where('account',$request->input('account'))->first();
            return response()->json([
                'status' => 'SUCCESS',
                 'mess' => 'update thành công ',
                'data'=> $user
                ]); 
        }
    }