package com.example.datn_10116101.DangKy_DangNhap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.datn_10116101.BaseResponse.ResponseUser1s;
import com.example.datn_10116101.DangKy_DangNhap.viewModel.DangkyViewModel;
import com.example.datn_10116101.R;
import com.example.datn_10116101.ViewModel.UserViewModel;
import com.example.datn_10116101.config.Common;
import com.example.datn_10116101.databinding.ActivityDangKyBinding;
import com.example.datn_10116101.model.user1s;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class DangKyActivity extends AppCompatActivity {
    UserViewModel userViewModel;
    DangkyViewModel dangkyViewModel;
    ActivityDangKyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDangKyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dangkyViewModel = ViewModelProviders.of(this).get(DangkyViewModel.class);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        checkSDT();
        chuyenTrang();
    }

    private void chuyenTrang() {
        binding.buttonB1ChuyentrangFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangKyActivity.this,XacMinhTaiKhoanActivity.class));
            }
        });
    }

    private void checkSDT() {
        binding.includeView.edtB1Sdt.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {
                if(s.length()<12){
                    binding.includeView.edtB1Sdt.setError("hãy nhập số điện thoại");
                }else{
                    checkPhone(s.toString());
                }
            }
        });

    }

    private void checkPhone(final String s) {
        dangkyViewModel.CheckPhone(s).observe(this, new Observer<ResponseUser1s>() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onChanged(ResponseUser1s responseUser1s) {
                if(responseUser1s.getStatus().equals("SUCCESS")){
                    user1s us =new user1s();
                    us.setPhone(s);
                    Common.muser1s = us;
                    Toast.makeText(DangKyActivity.this, ""+Common.muser1s.getPhone(), Toast.LENGTH_SHORT).show();
                    binding.buttonB1ChuyentrangFab.setVisibility(View.VISIBLE);
                }else{
                    Toast.makeText(DangKyActivity.this, "số điện thoại đã được đăng ký ", Toast.LENGTH_SHORT).show();
                    binding.buttonB1ChuyentrangFab.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

}

