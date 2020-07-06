package com.example.datn_10116101.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datn_10116101.model.feedback_products;
import com.example.datn_10116101.model.products;
import com.example.datn_10116101.model.user1s;
import com.example.datn_10116101.R;
import com.example.datn_10116101.ViewModel.FeedbackViewModel;
import com.google.android.material.textfield.TextInputEditText;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PhanHoiActivity extends BaseActivity {
    TextView textview_tensanpham , textview_post;
    ImageView imageview_hinhanhsanpham;
    RatingBar rateing_danhgia;
    TextInputEditText edittext_content;
    FeedbackViewModel feedbackViewModel;
    products pro;
    user1s us;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phanhoi);
        feedbackViewModel = ViewModelProviders.of(this).get(FeedbackViewModel.class);
        Anhxa();
        textview_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd"); // Format date
                String date = df1.format(Calendar.getInstance().getTime());
                float rate =rateing_danhgia.getRating();
                feedback_products feedbackProducts =new feedback_products(pro.getId(),us.getId(),edittext_content.getText().toString(),date,rate);

                feedbackViewModel.pushFeedback(feedbackProducts).observe(PhanHoiActivity.this, new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer integer) {
                        if(integer!=null){
                            Toast.makeText(PhanHoiActivity.this, "Lỗi ", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(PhanHoiActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void Anhxa() {
        textview_post = findViewById(R.id.textview_post);
        textview_tensanpham = findViewById(R.id.textview_tensanpham);
        imageview_hinhanhsanpham  =  findViewById(R.id.imageview_hinhanhsanpham);
        rateing_danhgia = findViewById(R.id.rateing_danhgia);
        edittext_content = findViewById(R.id.edittext_content);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(products event) { // get model test
        pro = new products();
        pro = event;
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(user1s event) { // get model test
        us = new user1s();
        us = event;
    }
}
