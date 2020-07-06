package com.example.datn_10116101.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.datn_10116101.Adapter.ChiTietHoaDonAdapter;
import com.example.datn_10116101.Adapter.HoaDonAdapter;
import com.example.datn_10116101.Model.ChiTietHoaDon;
import com.example.datn_10116101.Model.HoaDon;
import com.example.datn_10116101.Model.user1s;
import com.example.datn_10116101.R;
import com.example.datn_10116101.ViewModel.ChiTietHoaDonViewModel;
import com.example.datn_10116101.ViewModel.HoaDonViewModel;
import com.vinay.stepview.StepView;
import com.vinay.stepview.models.Step;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class ChiTietDonHangActivity extends BaseActivity {
    RecyclerView recyclerview_donhang;
    ChiTietHoaDonViewModel chiTietHoaDonViewModel;
    ChiTietHoaDonAdapter chiTietHoaDonAdapter;
    ArrayList<ChiTietHoaDon> chiTietHoaDonArrayList;
    StepView StepView;
    TextView textview_tenkhachhang;
    TextView textview_diachigiaohang;
    TextView textview_sodienthoai;
    TextView textview_ghichu;
    TextView textview_email;
    ImageView imageview_thoat;
    HoaDon hoaDon;
    List<Step> stepList = new ArrayList<>();
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietdonhang);
        chiTietHoaDonViewModel = ViewModelProviders.of(this).get(ChiTietHoaDonViewModel.class);
        AnhXa();
    }
    private void AnhXa () {
        recyclerview_donhang = findViewById(R.id.recyclerview_donhang);
        chiTietHoaDonArrayList = new ArrayList<>();
        recyclerview_donhang.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(ChiTietDonHangActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerview_donhang.setLayoutManager(layoutManager);
        StepView = findViewById(R.id.step_view);
        textview_tenkhachhang = findViewById(R.id.textview_tenkhachhang);
        textview_diachigiaohang = findViewById(R.id.textview_diachigiaohang);
        textview_email = findViewById(R.id.textview_email);
        textview_sodienthoai = findViewById(R.id.textview_sodienthoai);
        imageview_thoat = findViewById(R.id.imageview_thoat);
        imageview_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent (HoaDon event){ // get model test
        hoaDon = new HoaDon();
        hoaDon = event;
        loadchitiethoadon(hoaDon.getId());
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent (user1s event){ // get model test
            loadthongtinkhachang(event);
    }

    private void loadthongtinkhachang(user1s event) {
         textview_tenkhachhang.setText(""+event.getName());
         textview_email.setText(""+event.getEmail());
         textview_diachigiaohang.setText(""+event.getAddress());
         textview_sodienthoai.setText(""+event.getPhone());
         //textview_ghichu.setText(""+event.getSex());
    }

    private void loadchitiethoadon(int idhoadon) {
        chiTietHoaDonViewModel.getChiTietHoaDon(idhoadon).observe(this, new Observer<List<ChiTietHoaDon>>() {
            @Override
            public void onChanged(List<ChiTietHoaDon> hoaDons) {
                chiTietHoaDonArrayList.addAll(hoaDons);
                chiTietHoaDonAdapter = new ChiTietHoaDonAdapter(chiTietHoaDonArrayList, ChiTietDonHangActivity.this);
                recyclerview_donhang.setAdapter(chiTietHoaDonAdapter);
                stepList.add(new Step("Đã đặt "));
                stepList.add(new Step("Chờ xác nhận"));
                stepList.add(new Step("Đang giao"));
                stepList.add(new Step("Đã giao"));
                StepView.setTextSize(13).setLineLength(70);
                StepView.setSteps(stepList);
                stepList.get(1).setState(Step.State.COMPLETED);
                stepList.get(0).setState(Step.State.CURRENT);
            }
        });
    }

}