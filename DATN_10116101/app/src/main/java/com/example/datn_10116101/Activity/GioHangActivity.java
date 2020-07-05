package com.example.datn_10116101.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datn_10116101.Adapter.GioHangAdapter;
import com.example.datn_10116101.Model.Cart;
import com.example.datn_10116101.Model.ChiTietHoaDon;
import com.example.datn_10116101.R;
import com.example.datn_10116101.ViewModel.GioHangViewModel;
import com.example.datn_10116101.config.Database;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.http.DELETE;

import static android.provider.Settings.System.DATE_FORMAT;

public class GioHangActivity extends BaseActivity {
    RecyclerView recyclerview_giohang;
    GridLayoutManager layoutManager;
    static GioHangAdapter gioHangAdapter;
    ImageView imageview_thoat;
    Button button_dathang;
    CardView carview;
    static TextView txtthongbao;
    static TextView txtsoluong;
    static TextView textview_thanhtien;
    static TextView textview_tencuahang;
    static SQLiteDatabase database;
    static ArrayList<Cart> listcart = new ArrayList<>();
    ArrayList<ChiTietHoaDon> listCTHD = new ArrayList<>();

    GioHangViewModel gioHangViewModel; // khai báo view model

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_giohang);
        gioHangViewModel= ViewModelProviders.of(this).get(GioHangViewModel.class); // khởi tạo viewmodel
        Anhxa();
        setuplistCart();
        Tinhtien();
        dathang();


        imageview_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listcart.clear();
                finish();
            }
        });
    }
    public static String getDateString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return format.format(date);
    }
static int tongtien1;
    private void dathang() {
        button_dathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd"); // Format date
                String date = df1.format(Calendar.getInstance().getTime());
                    // lắng nghe dữ liệu thay đổi được trả về từ server
                    gioHangViewModel.GhiHoadon(1,"Đơn hàng",tongtien1,  date).observe(GioHangActivity.this, new Observer<Integer>() {
                        @Override
                        public void onChanged(Integer integer) {
                           listCTHD.clear();
                            castChitiethoadon(integer);
                                gioHangViewModel.GhiChiTietHoaDon(listCTHD).observe(GioHangActivity.this, new Observer<Boolean>() {
                                    @Override
                                    public void onChanged(Boolean aBoolean) {
                                        if(aBoolean){
                                            Toast.makeText(GioHangActivity.this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                                            listcart.clear();
                                            tongtien1=0;
                                            textview_thanhtien.setText(""+ 0 +"Đ");
                                            gioHangAdapter.notifyDataSetChanged();
                                            database.execSQL("delete from "+ "Cart");
                                            finish();

                                        }
                                    }
                                });
                        }
                    });

            }
        });
    }

    public void  castChitiethoadon(int idhoadon){
       for (int i=0;i<listcart.size();i++){
           ChiTietHoaDon ct=new ChiTietHoaDon();
           ct.setIdhoadon(idhoadon);
           ct.setGia(listcart.get(i).getPrice());
           ct.setMasanpham(listcart.get(i).getIdproduct());
           ct.setHinhanh(listcart.get(i).getHinhanhproduct());
           ct.setMota(listcart.get(i).getMotaproduct());
           ct.setSoluong(listcart.get(i).getCout());
           ct.setTensanpham(listcart.get(i).getTenproduct());
           listCTHD.add(ct);
       }
    }

    public static void Tinhtien() {
        long tongtien = 0;
        for (int i = 0; i< listcart.size(); i++){
            tongtien += listcart.get(i).getPrice() * listcart.get(i).getCout() ;
        }
        tongtien1= (int) tongtien;
        textview_thanhtien.setText(format(tongtien)+"Đ");
    }


    private void setuplistCart() {
        Cursor cursor = database.rawQuery("select * from cart", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int idproduct = cursor.getInt(1);
            int cout = cursor.getInt(2);
            int price = cursor.getInt(3);
            String voucher = cursor.getString(4);
            String date = cursor.getString(5);
            String tenproduct = cursor.getString(6);
            String hinhanhproduct = cursor.getString(7);
            String motaproduct = cursor.getString(8);
            Cart c = new Cart(id, idproduct, cout, price, voucher, date, tenproduct, hinhanhproduct, motaproduct);
            listcart.add(c);
        }
        recyclerview_giohang.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 1);
        recyclerview_giohang.setLayoutManager(layoutManager);
        gioHangAdapter = new GioHangAdapter(listcart,this);
        recyclerview_giohang.setAdapter(gioHangAdapter);
        setemtyview();
    }

    public static void setemtyview(){
        if(listcart.size()==0){
            txtthongbao.setText("Giỏ hàng trống");
            txtsoluong.setText("Giỏ hàng ("+listcart.size()+")");
        }else{
            txtthongbao.setText("");
            txtsoluong.setText("Giỏ hàng ("+listcart.size()+")");
        }
    }

    public static void xoagiohang(int id,int position){
        database.delete("Cart", "id" + "=?", new String[] { String.valueOf(id) });
        listcart.remove(position);
        gioHangAdapter.notifyDataSetChanged();
        setemtyview();
    }

    public static void Themgiohang(Cart cart,int pos){
        listcart.set(pos,cart);
        gioHangAdapter.notifyItemChanged(pos);
        int cout=cart.getCout();
        ContentValues contentValues=new ContentValues();
        contentValues.put("cout",cout);
        database.update("Cart",contentValues,"id = ?",new String[]{String.valueOf(cart.getId())});
        Tinhtien();
    }

    private void Anhxa() {
        database = Database.initDatabase(this, "huhu.db");
        imageview_thoat = findViewById(R.id.imageview_thoat);
        button_dathang = findViewById(R.id.button_dathang);
        carview = findViewById(R.id.carview);
        textview_thanhtien = findViewById(R.id.textview_thanhtien);
        recyclerview_giohang = findViewById(R.id.recyclerview_giohang);
        textview_tencuahang = findViewById(R.id.textview_tencuahang);
        txtthongbao=findViewById(R.id.textview_thongbao);
        txtsoluong=findViewById(R.id.textview_soluong);
    }
    public static String format(double number){
        NumberFormat formatter = new DecimalFormat("#,###,###");
        return formatter.format(number);
    }

    @Override
    public void onBackPressed() {
        listcart.clear();
        finish();
    }



    private void initScrollListener() {
        recyclerview_giohang.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }
}