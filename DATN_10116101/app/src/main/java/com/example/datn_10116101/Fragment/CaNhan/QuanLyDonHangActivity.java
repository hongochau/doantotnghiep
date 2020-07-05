package com.example.datn_10116101.Fragment.CaNhan;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.datn_10116101.Activity.BaseActivity;
import com.example.datn_10116101.Activity.ChiTietDonHangActivity;
import com.example.datn_10116101.Adapter.HoaDonAdapter;
import com.example.datn_10116101.ItemClickSupport;
import com.example.datn_10116101.Model.HoaDon;
import com.example.datn_10116101.R;
import com.example.datn_10116101.ViewModel.HoaDonViewModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class QuanLyDonHangActivity extends BaseActivity {
    ImageView imageview_thoat;
    RecyclerView lv;
    HoaDonViewModel hoaDonViewModel;
    LinearLayoutManager layoutManager;
    HoaDonAdapter adapter;
    ArrayList<HoaDon> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoadon);
        hoaDonViewModel= ViewModelProviders.of(this).get(HoaDonViewModel.class);
        Anhxa();
        hoaDonViewModel.getHoadon(1,"Đơn hàng").observe(this, new Observer<List<HoaDon>>() {
            @Override
            public void onChanged(List<HoaDon> hoaDons) {
                list.addAll(hoaDons);
                adapter = new HoaDonAdapter(list, QuanLyDonHangActivity.this);
                lv.setAdapter(adapter);
            }
        });
        ItemClickSupport.addTo(lv).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                HoaDon pro=list.get(position);
                EventBus.getDefault().postSticky(pro); // gửi dữ liệu vào trong Eventbuss
                startActivity(new Intent(QuanLyDonHangActivity.this, ChiTietDonHangActivity.class));
            }
        });
    }
    private void Anhxa() {
        lv=findViewById(R.id.lvhoadon);
        list=new ArrayList<>();
        imageview_thoat = findViewById(R.id.imageview_thoat);
        imageview_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(QuanLyDonHangActivity.this, LinearLayoutManager.VERTICAL, false);
        lv.setLayoutManager(layoutManager);

    }
}


