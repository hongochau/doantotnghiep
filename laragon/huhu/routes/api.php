<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});
//Thông báo
Route::post('putnotifi', 'AdminController@putnotifi')->name('putnotifi');


// user 
Route::post('RegisterNomal', 'User1sController@RegisterNomal')->name('RegisterNomal');
Route::post('loginNomal', 'User1sController@loginNomal')->name('loginNomal');


// product
Route::get('getAll1', 'ProductController@getAll1')->name('getAll1'); 
Route::get('getCount', 'ProductController@getCount')->name('getCount');
Route::post('LoadmoreWithproductType', 'ProductController@LoadmoreWithproductType')->name('LoadmoreWithproductType');



// product detail 
Route::post('getnewpro', 'ProductController@getnewpro')->name('getnewpro');
Route::post('getimageSP', 'ProductController@getimageSP')->name('getimageSP');
Route::post('getSPLQ', 'ProductController@getSPLQ')->name('getSPLQ'); 
Route::post('getfullSPLQ', 'ProductController@getfullSPLQ')->name('getfullSPLQ'); 
Route::post('getfullfeedback', 'ProductController@getfullfeedback')->name('getfullfeedback');
Route::post('getproImage', 'ImageProductController@getproImage')->name('getproImage');


// product type 
Route::get('getAllLoaiSp', 'ProductTypeController@getAllLoaiSp')->name('getAllLoaiSp');
Route::post('searchProduct', 'SearchController@searchProduct')->name('searchProduct');
Route::post('searchByid', 'SearchController@searchByid')->name('searchByid');
Route::post('checkVoucher', 'VoucherController@checkVoucher')->name('checkVoucher');


// chitiethoadon
Route::post('HoaDon', 'ChitiethoadonController@HoaDon')->name('HoaDon');
Route::post('ChiTietHoaDon', 'ChitiethoadonController@ChiTietHoaDon')->name('ChiTietHoaDon');
Route::post('getHoaDon', 'ChitiethoadonController@getHoaDon')->name('getHoaDon');
Route::post('getChiTietHoaDon', 'ChitiethoadonController@getChiTietHoaDon')->name('getChiTietHoaDon');

// login 
Route::post('Register', 'LoginController@Register')->name('Register');
Route::post('CheckUser', 'LoginController@CheckUser')->name('CheckUser');
Route::post('UpdateThongTinTaiKhoan', 'LoginController@UpdateThongTinTaiKhoan')->name('UpdateThongTinTaiKhoan');


//Feedback
Route::post('pushFeedback', 'FeedBackController@pushFeedback')->name('pushFeedback');
Route::post('getfeedback', 'FeedBackController@getfeedback')->name('getfeedback');
Route::post('getfeedbackWithIdUser', 'FeedBackController@getfeedbackWithIdUser')->name('getfeedbackWithIdUser');



//Quanlysanpham
Route::post('Sanphamdaxem', 'QuanlysanphamController@Sanphamdaxem')->name('Sanphamdaxem');

// orther

Route::post('getAllBillWithType', 'OrtherController@getAllBillWithType')->name('getAllBillWithType');
Route::post('changeStatus', 'OrtherController@changeStatus')->name('changeStatus');
Route::get('getDescProduct', 'OrtherController@getDescProduct')->name('getDescProduct');




