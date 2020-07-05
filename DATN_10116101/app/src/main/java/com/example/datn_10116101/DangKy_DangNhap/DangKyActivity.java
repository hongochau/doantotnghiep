package com.example.datn_10116101.DangKy_DangNhap;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.datn_10116101.R;
import com.example.datn_10116101.ViewModel.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class DangKyActivity extends AppCompatActivity {
    private int i;
    private boolean Bancotheditiep;
    private ArrayList<GetterSetter> arrayList;
    private FloatingActionButton button_b1_chuyentrang_fab;
    private EditText editText_b1_email;
    private TextView textView_b1_ketthuc_dangnhap;
    private ImageView imageView_b1_email_cancel;
    private TextView textView_b1_email_tontaihaychua;
    private InputMethodManager inputMethodManager;
    private boolean noEmail = false;

    UserViewModel userViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        userViewModel= ViewModelProviders.of(this).get(UserViewModel.class);
        inputMethodManager = (InputMethodManager) getSystemService( Context.INPUT_METHOD_SERVICE );
        arrayList = new ArrayList<>();
        arrayList.add (new GetterSetter(0,"HnhElectronicShopHelp@gmail.com","123","hnh"));
        button_b1_chuyentrang_fab = findViewById( R.id.button_b1_chuyentrang_fab );
        textView_b1_ketthuc_dangnhap = findViewById( R.id.textView_b1_ketthuc_dangnhap );
        editText_b1_email = findViewById( R.id.editText_b1_email );
        imageView_b1_email_cancel = findViewById( R.id.imageView_b1_email_cancel );
        textView_b1_email_tontaihaychua = findViewById( R.id.textView_b1_email_tontaihaychua );

        b_1_Step();
        b_1_Step1();
    }

    public void b_1_Step() { // bắt text khi chưa nhập đúng
        textView_b1_email_tontaihaychua.setVisibility( View.GONE );
        imageView_b1_email_cancel.setVisibility( View.GONE );
        editText_b1_email.addTextChangedListener( new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) { // bắt sự kiện viết trong editext
                String Email = editText_b1_email.getText().toString();
                textView_b1_email_tontaihaychua.setVisibility( View.GONE );
                Bancotheditiep = false;
                if (Email.length() == 0) {
                    imageView_b1_email_cancel.setVisibility( View.GONE );
                } else {
                    imageView_b1_email_cancel.setVisibility( View.VISIBLE );
                    if (Emailcohoplekhong_isvalidEmail( Email )) { // valid email
                        if (noEmail) {
                            Bancotheditiep = true;
                            return;
                        }
                        for (int i = 0; i < arrayList.size(); i++) ;
                        {

                            if (arrayList.get( i ).getTaiKhoan().equals( Email.toLowerCase() )) {
                                textView_b1_email_tontaihaychua.setText( "Tài khoản này đã dược đăng ký" );
                                textView_b1_email_tontaihaychua.setVisibility( View.VISIBLE );
                            } else {
                                Bancotheditiep = true;
                            }
                        }
                    } else {
                        textView_b1_email_tontaihaychua.setText( "Địa chỉ Email chưa hợp lệ" );
                        textView_b1_email_tontaihaychua.setVisibility( View.VISIBLE );
                    }
                }
            }
        } );
    }

    public void b_1_Step1() {
        textView_b1_ketthuc_dangnhap.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
        imageView_b1_email_cancel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText_b1_email.setText( "" );
                textView_b1_email_tontaihaychua.setVisibility( View.VISIBLE );
                imageView_b1_email_cancel.setVisibility( View.VISIBLE );
                Bancotheditiep = false;
            }
        } );
        button_b1_chuyentrang_fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Bancotheditiep) {
                    new nhanmaemail_getemailcodeclass().execute(); // chạy đoạn progress bar
                } else {
                    editText_b1_email.requestFocus();
                    inputMethodManager.toggleSoftInput( InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY );
                }
            }
        } );
    }

    public String taomaEmail_createEmailcode() { // tạo otp code
        String[] nums = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String newCode = new String();
        for (int i = 0; i < 6; i++) {
            int random = (int) (Math.random() * nums.length);
            newCode += nums[random];
        }
        return newCode;

    }

    public boolean Emailcohoplekhong_isvalidEmail(CharSequence text) {
        if (text == null) {
            return false;
        }
        return Patterns.EMAIL_ADDRESS.matcher( text ).matches();
    }

    private class nhanmaemail_getemailcodeclass extends AsyncTask<Void, Void, Void> {
        Phattrien_progreesBar phattrien_progreesBar = new Phattrien_progreesBar( DangKyActivity.this );
        String code = taomaEmail_createEmailcode();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            inputMethodManager.hideSoftInputFromWindow( editText_b1_email.getWindowToken(), 0 );
            phattrien_progreesBar.show();
            phattrien_progreesBar.setCancelable( false );
            Toast.makeText( DangKyActivity.this, "Mã xác minh" + code, Toast.LENGTH_LONG ).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep( 3000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute( aVoid );
            Intent intent = new Intent( DangKyActivity.this, XacMinhTaiKhoanActivity.class );
            intent.putExtra( "Taikhoan", editText_b1_email.getText().toString());
            intent.putExtra( "Code", code);
            startActivity( intent );
            phattrien_progreesBar.dismiss();
        }
    }
}

