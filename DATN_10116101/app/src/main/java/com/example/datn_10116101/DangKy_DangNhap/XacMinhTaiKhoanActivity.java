package com.example.datn_10116101.DangKy_DangNhap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.datn_10116101.BaseResponse.ResponseUser1s;
import com.example.datn_10116101.DangKy_DangNhap.viewModel.DangkyViewModel;
import com.example.datn_10116101.R;
import com.example.datn_10116101.config.Common;
import com.example.datn_10116101.databinding.ActivityXacMinhTaiKhoanBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.concurrent.TimeUnit;

public class XacMinhTaiKhoanActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private String verificationId;
    ActivityXacMinhTaiKhoanBinding binding;
    PhoneAuthProvider.ForceResendingToken token;
    DangkyViewModel dangkyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityXacMinhTaiKhoanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        dangkyViewModel = ViewModelProviders.of(this).get(DangkyViewModel.class);
        binding.include.textViewB2Email.setText(""+Common.muser1s.getPhone());
        sendVerificationCode(Common.muser1s.getPhone());
        resendCode();
        VerifiCode();
        editextEvent();
    }

    private void editextEvent() {
        binding.include.editTextB2Nhapmacode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() >= 6) {
                    verifyCode(s.toString());
                }
            }
        });
    }

    private void VerifiCode() {
        binding.buttonB2ChuyentrangFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account=binding.include.editTextB2Nhaptendangnhap.getText().toString().trim();
                if(account!=""){
                    checkAccount(account);
                }else{
                   binding.include.editTextB2Nhaptendangnhap.setError("nhập tên đăng nhập vào cha nội @@");
                }

            }
        });
    }

    private void checkAccount(final String acc) {
        dangkyViewModel.checkAccount(acc).observe(XacMinhTaiKhoanActivity.this, new Observer<ResponseUser1s>() {
            @Override
            public void onChanged(ResponseUser1s responseUser1s) {
                if(responseUser1s.getStatus().equals("SUCCESS")){
                    Common.muser1s.setAccount(acc);
                startActivity(new Intent(XacMinhTaiKhoanActivity.this,MatKhauActivity.class));
                }else{
                    binding.include.editTextB2Nhaptendangnhap.setError("tên đăng nhập này đã tồn tại ");
                }
            }
        });
    }


    private void resendCode() {
        binding.textviewB2Khongthaytaikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (token != null) {
                    resendVerificationCode(Common.muser1s.getPhone(), token);
                } else {
                    Toast.makeText(XacMinhTaiKhoanActivity.this, "token trống ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    // firebase

    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                XacMinhTaiKhoanActivity.this,
                mCallBack,
                token);
    }

    private void sendVerificationCode(String number) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBack
        );
    }

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            binding.include.linearlayout.setVisibility(View.VISIBLE);
                            Toast.makeText(XacMinhTaiKhoanActivity.this, "thành công ", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(XacMinhTaiKhoanActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            Log.d("huhuhu", "onComplete: " + task.getException().getMessage());
                        }
                    }
                });
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
            token = forceResendingToken;
            Toast.makeText(XacMinhTaiKhoanActivity.this, "đã gửi code", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                binding.include.editTextB2Nhapmacode.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(XacMinhTaiKhoanActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.d("huhuhu", "onVerificationFailed: " + e.getMessage());
        }
    };
}

