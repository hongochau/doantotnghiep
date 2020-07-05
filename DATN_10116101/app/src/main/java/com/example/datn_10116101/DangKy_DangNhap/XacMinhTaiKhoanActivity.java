package com.example.datn_10116101.DangKy_DangNhap;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datn_10116101.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

public class XacMinhTaiKhoanActivity extends AppCompatActivity {
    private boolean Bancotheditiep;
    private String Taikhoan = "HnhElectronicShopHelp@gmail.com";
    private String Matkhau = "0000";

    private TextView textView_b2_nhaplaitendangnhap;
    private TextView textView_b2_nhaplaimacode;
    private EditText editText_b2_nhaptendangnhap;
    private ImageView imageView_b2_tendangnhap_cancel;
    private LinearLayout linearlayout_b2_chitiet;
    private EditText editText_b2_nhapmacode;
    private TextView textView_b2_email;
    private InputMethodManager input;
    private FloatingActionButton button_b2_chuyentrang_fab;
    private LinearLayout linearlayout_b2_khongtimthaytaikhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_minh_tai_khoan);
        textView_b2_nhaplaitendangnhap = findViewById( R.id.textView_b2_nhaplaitendangnhap );
        textView_b2_nhaplaimacode = findViewById( R.id.textView_b2_nhaplaitendangnhap );
        editText_b2_nhaptendangnhap = findViewById( R.id.editText_b2_nhaptendangnhap );
        imageView_b2_tendangnhap_cancel = findViewById( R.id.imageView_b2_tendangnhap_cancel );
        linearlayout_b2_chitiet = findViewById( R.id.linearlayout_b2_chitiet );
        editText_b2_nhapmacode = findViewById( R.id.editText_b2_nhapmacode );
        textView_b2_email = findViewById( R.id.textView_b2_email );
        button_b2_chuyentrang_fab = findViewById( R.id.button_b2_chuyentrang_fab );
        linearlayout_b2_khongtimthaytaikhoan = findViewById( R.id.linearlayout_b2_khongtimthaytaikhoan );

        input = (InputMethodManager) getSystemService( Context.INPUT_METHOD_SERVICE );
        editText_b2_nhapmacode.requestFocus();
        input.showSoftInput( editText_b2_nhapmacode, 0 );

        buoc_1();
        buoc_2();
        buoc_3();
        buoc_4();
    }

    public void buoc_1() {
        Taikhoan = getIntent().getStringExtra( "Taikhoan" );
        Matkhau = getIntent().getStringExtra( "Code" );
        textView_b2_email.setText( Taikhoan );

        linearlayout_b2_chitiet.setVisibility( View.GONE );
        button_b2_chuyentrang_fab.setVisibility( View.GONE );
        textView_b2_nhaplaimacode.setVisibility( View.GONE );
        editText_b2_nhapmacode.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                textView_b2_nhaplaimacode.setVisibility( View.GONE );
                linearlayout_b2_chitiet.setVisibility( View.GONE );

                String result = editText_b2_nhapmacode.getText().toString();
                if (result.length() == 6) {
                    if (result.equals( Matkhau )) {
                        editText_b2_nhapmacode.setEnabled( false );
                        linearlayout_b2_chitiet.setVisibility( View.VISIBLE );
                        button_b2_chuyentrang_fab.setVisibility( View.VISIBLE );
                        linearlayout_b2_khongtimthaytaikhoan.setVisibility( View.GONE );
                    } else {
                        textView_b2_nhaplaimacode.setVisibility( View.VISIBLE );
                    }
                }

            }
        } );

    }

    public void buoc_2() {
        imageView_b2_tendangnhap_cancel.setVisibility( View.GONE );
        textView_b2_nhaplaitendangnhap.setVisibility( View.GONE );
        editText_b2_nhaptendangnhap.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) { // chỗ này nó check dữ liệu từ input text xong rồi ẩn hiện view
                String name = editText_b2_nhaptendangnhap.getText().toString();
                Bancotheditiep = false;
                if (name.length() == 0) {
                    imageView_b2_tendangnhap_cancel.setVisibility( View.GONE );
                    textView_b2_nhaplaitendangnhap.setVisibility( View.GONE );
                    return;
                }
                if (name.length() > 0) { //bắt đầu có dữ liệu thì hiện image view đăng nhập lên
                    imageView_b2_tendangnhap_cancel.setVisibility( View.VISIBLE );
                }
                if (name.length() < 2) {
                    textView_b2_nhaplaitendangnhap.setVisibility( View.VISIBLE );
                } else {
                    textView_b2_nhaplaitendangnhap.setVisibility( View.GONE );
                    Bancotheditiep = true;
                }
            }
        } );
        imageView_b2_tendangnhap_cancel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText_b2_nhaptendangnhap.setText( "" );
                imageView_b2_tendangnhap_cancel.setVisibility( View.GONE );
                textView_b2_nhaplaitendangnhap.setVisibility( View.GONE );
                Bancotheditiep = false;

            }
        } );
    }

    public void buoc_3() {
        linearlayout_b2_khongtimthaytaikhoan.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new nhanmaemail_getemailcodeclass().execute(); // bắt đầu chạy async task
            }
        } );

    }

    public void buoc_4() { // chuyển bước
        button_b2_chuyentrang_fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.hideSoftInputFromWindow( editText_b2_nhapmacode.getWindowToken(), 0 );
                input.hideSoftInputFromWindow( editText_b2_nhaptendangnhap.getWindowToken(), 0 );

                if (Bancotheditiep) {
                    String name = editText_b2_nhaptendangnhap.getText().toString();
                    Toast.makeText( XacMinhTaiKhoanActivity.this,
                            "Email:" + Taikhoan
                                    + "\nName :" + name, Toast.LENGTH_LONG ).show();
                    Intent intent = new Intent( XacMinhTaiKhoanActivity.this, MatKhauActivity.class );
                    intent.putExtra( "Taikhoan", Taikhoan );
                    intent.putExtra( "Tendangnhap", name );
                    startActivity( intent );
                }
            }
        } );

    }

    public class nhanmaemail_getemailcodeclass extends AsyncTask<Void, Void, Void> {

        Phattrien_progreesBar phattrien_progreesBar = new Phattrien_progreesBar( XacMinhTaiKhoanActivity.this );

        @Override
        protected void onPreExecute() { // truwosfc khi chạy async task
            super.onPreExecute();
            String code = taomaEmail_createEmailcode();
            editText_b2_nhapmacode.setText( "" );
            button_b2_chuyentrang_fab.setVisibility( View.GONE );
            textView_b2_nhaplaimacode.setVisibility( View.GONE );
            linearlayout_b2_chitiet.setVisibility( View.GONE );


            phattrien_progreesBar.show();
            phattrien_progreesBar.setCancelable( false );
            Toast.makeText( XacMinhTaiKhoanActivity.this, "Mã xác minh" + code, Toast.LENGTH_LONG ).show();
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
            phattrien_progreesBar.dismiss();
            editText_b2_nhapmacode.requestFocus();
        }
    }

    public String taomaEmail_createEmailcode() { //gen code
        String[] nums = {"8", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String newCode = new String();
        for (int i = 0; i < 6; i++) {
            int random = (int) (Math.random() * nums.length);
            newCode += nums[random];
        }
        return newCode;
    }
}

