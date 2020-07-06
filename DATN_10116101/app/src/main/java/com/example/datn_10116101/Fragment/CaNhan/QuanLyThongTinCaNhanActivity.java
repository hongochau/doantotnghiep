package com.example.datn_10116101.Fragment.CaNhan;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.datn_10116101.Activity.BaseActivity;
import com.example.datn_10116101.Activity.MainActivity;
import com.example.datn_10116101.BaseResponse.ResponseUser1s;
import com.example.datn_10116101.model.user1s;
import com.example.datn_10116101.R;
import com.example.datn_10116101.ViewModel.UpdateThongTinTaiKhoanViewModel;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class QuanLyThongTinCaNhanActivity extends BaseActivity {
    EditText edittext_hoten,edittext_email,edittext_diachi,edittext_sodienthoai;
    ImageView imageview_thoat;
    Button button_updatethongtin;
    LinearLayoutManager layoutManager;
    UpdateThongTinTaiKhoanViewModel updateThongTinTaiKhoanViewModel;
    String account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtincanhan);
        updateThongTinTaiKhoanViewModel= ViewModelProviders.of(this).get(UpdateThongTinTaiKhoanViewModel.class);
        Anhxa();

    }

    private void Anhxa() {
        edittext_hoten =  findViewById(R.id.edittext_hoten);
        edittext_email =  findViewById(R.id.edittext_email);
        edittext_diachi =  findViewById(R.id.edittext_diachi);
        layoutManager = new LinearLayoutManager(QuanLyThongTinCaNhanActivity.this, LinearLayoutManager.VERTICAL, false);
        edittext_sodienthoai =  findViewById(R.id.edittext_sodienthoai);
        button_updatethongtin = findViewById(R.id.button_updatethongtin);
        button_updatethongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateThongTinTaiKhoanViewModel.ResponseUser1s(account,edittext_hoten.getText().toString(),
                        edittext_email.getText().toString(),
                        edittext_diachi.getText().toString(),
                        edittext_sodienthoai.getText().toString()).observe(QuanLyThongTinCaNhanActivity.this, new Observer<ResponseUser1s>() {
                    @Override
                    public void onChanged(ResponseUser1s responseUser1s) {
                        user1s us=responseUser1s.getData();
                        EventBus.getDefault().postSticky(us);
                        startActivity(new Intent(QuanLyThongTinCaNhanActivity.this, MainActivity.class));
                    }
                });
            }
        });
        imageview_thoat = findViewById(R.id.imageview_thoat);
        imageview_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(user1s event) { // get model test
        account=event.getAccount();
        edittext_hoten.setText(""+event.getName());
        edittext_email.setText(""+event.getEmail());
        edittext_diachi.setText(""+event.getAddress());
        edittext_sodienthoai.setText(""+event.getPhone());

    }
}
