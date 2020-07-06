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
import com.example.datn_10116101.model.user1s;
import com.example.datn_10116101.R;
import com.example.datn_10116101.ViewModel.UserViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import org.greenrobot.eventbus.EventBus;

public class MatKhauActivity extends AppCompatActivity {
    private boolean Bancotheditiep;
    private Button button_b3_dangky;
    private EditText editText_b3_nhapmatkhau;
    private TextView textView_b3_yeucau_matkhau;
    private ImageView imageView_b3_cancel_nhapmatkhau;
    private CheckBox checkBox_b3_toichapnhan;
    private CheckBox checkBox_b3_thongtin;
    private int images[] = {R.drawable.ic_visibility_black_24dp, R.drawable.ic_visibility_off_black_24dp};

    private String TaiKhoan;
    private String MatKhau;
    private String TenDangNhap;
    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mat_khau);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        button_b3_dangky = findViewById(R.id.button_b3_dangky);
        editText_b3_nhapmatkhau = findViewById(R.id.editText_b3_nhapmatkhau);
        textView_b3_yeucau_matkhau = findViewById(R.id.textView_b3_yeucau_matkhau);
        imageView_b3_cancel_nhapmatkhau = findViewById(R.id.imageView_b3_cancel_nhapmatkhau);
        checkBox_b3_toichapnhan = findViewById(R.id.checkBox_b3_toichapnhan);
        checkBox_b3_thongtin = findViewById(R.id.checkBox_b3_thongtin);
        buoc1();
        buoc2();
    }

    public void buoc1() {
        TaiKhoan = getIntent().getStringExtra("Taikhoan");
        /*TenDangNhap = getIntent().getStringExtra( "Tendangnhap" );*/
        textView_b3_yeucau_matkhau.setVisibility(View.GONE);
        editText_b3_nhapmatkhau.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String matkhau = editText_b3_nhapmatkhau.getText().toString();
                Bancotheditiep = false;
                if (matkhau.length() == 0) {
                    textView_b3_yeucau_matkhau.setVisibility(View.GONE);
                    return;
                }
                if (matkhau.length() < 8 || matkhau.length() > 20) {
                    textView_b3_yeucau_matkhau.setVisibility(View.VISIBLE);
                    Bancotheditiep = false;
                } else {
                    textView_b3_yeucau_matkhau.setVisibility(View.GONE);
                    Bancotheditiep = true;
                }
            }
        });
        editText_b3_nhapmatkhau.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        imageView_b3_cancel_nhapmatkhau.setImageResource(images[1]);
        imageView_b3_cancel_nhapmatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText_b3_nhapmatkhau.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
                    editText_b3_nhapmatkhau.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    imageView_b3_cancel_nhapmatkhau.setImageResource(images[0]);
                } else if (editText_b3_nhapmatkhau.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)) {
                    editText_b3_nhapmatkhau.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    imageView_b3_cancel_nhapmatkhau.setImageResource(images[1]);
                }
            }
        });
    }

    public void buoc2() { // call api đăng kí này
        button_b3_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                Intent intent1 = getIntent();
                String tk, mk, email;
                tk = intent1.getStringExtra("Taikhoan");
                mk = editText_b3_nhapmatkhau.getText().toString();
                if (Bancotheditiep) {
                    if (checkBox_b3_toichapnhan.isChecked()) {
                        userViewModel.Register(tk, tk, mk).observe(MatKhauActivity.this, new Observer<ResponseUser1s>() {
                            @Override
                            public void onChanged(ResponseUser1s responseUser1s) {
                                if (responseUser1s.getStatus().equals("SUCCESS")) {
                                    Toast.makeText(MatKhauActivity.this, "" + responseUser1s.getMess(), Toast.LENGTH_SHORT).show();
                                    user1s muser = responseUser1s.getData();
                                    EventBus.getDefault().postSticky(muser);
                                    startActivity(new Intent(MatKhauActivity.this,DangNhapActivity.class));
                                } else {
                                    Toast.makeText(MatKhauActivity.this, " "+responseUser1s.getMess(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(MatKhauActivity.this,
                                "Vui lòng kiểm tra chính sách bảo mật", Toast.LENGTH_LONG).show();

                    }
                    inputMethodManager.hideSoftInputFromWindow(editText_b3_nhapmatkhau.getWindowToken(), 0);
                } else {
                    inputMethodManager.showSoftInput(editText_b3_nhapmatkhau, 0);
                }
            }

        });
    }
}
