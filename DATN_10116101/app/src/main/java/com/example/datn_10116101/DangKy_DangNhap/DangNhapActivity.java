package com.example.datn_10116101.DangKy_DangNhap;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datn_10116101.Activity.BaseActivity;
import com.example.datn_10116101.Activity.MainActivity;
import com.example.datn_10116101.Admin.AdminActivity;
import com.example.datn_10116101.BaseResponse.ResponseUser1s;
import com.example.datn_10116101.config.Common;
import com.example.datn_10116101.model.user1s;
import com.example.datn_10116101.R;
import com.example.datn_10116101.ViewModel.UserViewModel;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DangNhapActivity extends BaseActivity implements View.OnClickListener {
    private TextView textView_Quenmatkhau;
    private EditText editText_Email;
    private EditText editText_Password;
    private CheckBox checkBox_Nhomatkhau;
    private Button button_Dangnhap;
    private TextView textView_Dangky;
    private ImageView imageView_Anhienmatkhau;
    private int images[] = {R.drawable.ic_visibility_black_24dp, R.drawable.ic_visibility_off_black_24dp};
    UserViewModel userViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        userViewModel = ViewModelProviders.of(DangNhapActivity.this).get(UserViewModel.class);

        textView_Quenmatkhau = findViewById(R.id.textView_quenmatkhau);
        SpannableString spannableString = new SpannableString("Quên mật khẩu ?");
        spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);
        textView_Quenmatkhau.setText(spannableString);

        editText_Email = findViewById(R.id.edittext_email);
        editText_Password = findViewById(R.id.edittext_password);
        checkBox_Nhomatkhau = findViewById(R.id.checkbox_nhomatkhau);
        button_Dangnhap = findViewById(R.id.button_dangnhap);
        textView_Dangky = findViewById(R.id.textview_dangky);
        imageView_Anhienmatkhau = findViewById(R.id.imageview_anhienmatkhau);
        imageView_Anhienmatkhau.setOnClickListener(this);

        editText_Password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        imageView_Anhienmatkhau.setImageResource(images[1]);
        textView_Dangky.setOnClickListener(this);
        button_Dangnhap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == imageView_Anhienmatkhau) {
            if (editText_Password.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
                editText_Password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                imageView_Anhienmatkhau.setImageResource(images[0]);
            } else if (editText_Password.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)) {
                editText_Password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                imageView_Anhienmatkhau.setImageResource(images[1]);
            }
        } else if (v == textView_Dangky) {
            startActivity(new Intent(DangNhapActivity.this, DangKyActivity.class));
        } else if (v == button_Dangnhap) {
            String phone = editText_Email.getText().toString().toLowerCase();
            String Matkhau = editText_Password.getText().toString();
            // call api đăng nhập
            userViewModel.Login(phone, Matkhau).observe(DangNhapActivity.this, new Observer<ResponseUser1s>() {
                @Override
                public void onChanged(ResponseUser1s responseUser1s) {
                    if (responseUser1s.getStatus().equals("SUCCESS")) { // check status trả về
                        if (responseUser1s.getData().getPhone().equals("0971004685")) {
                            startActivity(new Intent(DangNhapActivity.this, AdminActivity.class));
                        } else {
                            user1s us = responseUser1s.getData();
                            Common.muser1s=us;
                            EventBus.getDefault().postSticky(us); // đưa dữ liệu vào Eventbus
                            startActivity(new Intent(DangNhapActivity.this, MainActivity.class));
                        }
                    } else {
                        Toast.makeText(DangNhapActivity.this, "" + responseUser1s.getMess(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(user1s event) { // get model test
        editText_Email.setText("" + event.getEmail());
        editText_Password.setText("" + event.getPassword());
    }
}




