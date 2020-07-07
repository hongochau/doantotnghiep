package com.example.datn_10116101.DangKy_DangNhap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datn_10116101.BaseResponse.ResponseUser1s;
import com.example.datn_10116101.config.Common;
import com.example.datn_10116101.databinding.ActivityMatKhauBinding;
import com.example.datn_10116101.model.user1s;
import com.example.datn_10116101.R;
import com.example.datn_10116101.ViewModel.UserViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import org.greenrobot.eventbus.EventBus;

public class MatKhauActivity extends AppCompatActivity {
    ActivityMatKhauBinding binding;
    UserViewModel userViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMatKhauBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        editextChange();
        checkeddieukhoan();
        binding.buttonB3Dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(checkeddieukhoan()){
                   handlergister();
               }else{
                   Toast.makeText(MatKhauActivity.this, "đmm chấp nhận điều khoản đi con chó ", Toast.LENGTH_SHORT).show();
               }
            }
        });

    }

    private void handlergister() {
        userViewModel.Register(Common.muser1s.getPhone(), Common.muser1s.getAccount(),
                binding.editTextB3Nhapmatkhau.getText().toString()).observe(this, new Observer<ResponseUser1s>() {
            @Override
            public void onChanged(ResponseUser1s responseUser1s) {
                if (responseUser1s.getStatus().equals("SUCCESS")) {
                    Common.muser1s.setPassword(binding.editTextB3Nhapmatkhau.getText().toString().trim());
                    startActivity(new Intent(MatKhauActivity.this,DangNhapActivity.class));
                } else {
                    Toast.makeText(MatKhauActivity.this, "" + responseUser1s.getMess(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkeddieukhoan() {
        if(binding.checkBoxB3Thongtin.isChecked() && binding.checkBoxB3Toichapnhan.isChecked()){
            return true;
        }else{
            return false;
        }
    }

    private void editextChange() {
        binding.editTextB3Nhapmatkhau.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 8 && s.length() < 20) {
                    binding.buttonB3Dangky.setVisibility(View.VISIBLE);
                    binding.textViewB3YeucauMatkhau.setVisibility(View.INVISIBLE);
                }else{
                    binding.buttonB3Dangky.setVisibility(View.INVISIBLE);
                    binding.textViewB3YeucauMatkhau.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
