package com.example.datn_10116101.Fragment.CaNhan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.datn_10116101.DangKy_DangNhap.DangNhapActivity;
import com.example.datn_10116101.R;

public class CaNhanActivity extends AppCompatActivity {
    TextView textview_dangnhap_dangky;
    RelativeLayout relativelayout_quanlydonhang;
    RelativeLayout relativelayout_thongtincanhan;
    RelativeLayout relativelayout_quanlysanphamdaxem;
    RelativeLayout relativelayout_quanlysanphamdanhgia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canhan);
        Anhxa();
    }

    private void Anhxa() {
        textview_dangnhap_dangky = findViewById(R.id.textview_dangnhap_dangky);
        relativelayout_quanlydonhang = findViewById(R.id.relativelayout_quanlydonhang);
        relativelayout_thongtincanhan = findViewById(R.id.relativelayout_thongtincanhan);
        relativelayout_quanlysanphamdaxem = findViewById(R.id.relativelayout_quanlysanphamdaxem);
        relativelayout_quanlysanphamdanhgia = findViewById(R.id.relativelayout_quanlysanphamdanhgia);


        relativelayout_quanlydonhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaNhanActivity.this, QuanLyDonHangActivity.class);
                startActivity(intent);
            }
        });
        textview_dangnhap_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaNhanActivity.this, DangNhapActivity.class);
                startActivity(intent);
            }
        });
        relativelayout_thongtincanhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaNhanActivity.this, QuanLyThongTinCaNhanActivity.class);
                startActivity(intent);
            }
        });
        relativelayout_quanlysanphamdaxem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaNhanActivity.this, QuanLySanPhamDaXemActivity.class);
                startActivity(intent);
            }
        });

        relativelayout_quanlysanphamdanhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaNhanActivity.this, QuanLySanPhamDaDanhGiaActivity.class);
                startActivity(intent);
            }
        });
    }
}
