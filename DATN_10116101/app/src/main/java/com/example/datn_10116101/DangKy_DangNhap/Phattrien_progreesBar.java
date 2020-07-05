package com.example.datn_10116101.DangKy_DangNhap;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;


import com.example.datn_10116101.R;

import androidx.annotation.NonNull;

public class Phattrien_progreesBar extends Dialog {
    public Phattrien_progreesBar(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_phattrien_progreebar);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}
