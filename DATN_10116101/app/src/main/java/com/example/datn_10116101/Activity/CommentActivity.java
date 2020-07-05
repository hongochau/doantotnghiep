package com.example.datn_10116101.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datn_10116101.Model.feedback_products;
import com.example.datn_10116101.Model.products;
import com.example.datn_10116101.Model.user1s;
import com.example.datn_10116101.R;
import com.example.datn_10116101.ViewModel.FeedbackViewModel;
import com.google.android.material.textfield.TextInputEditText;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommentActivity extends BaseActivity {

    TextView txttensp;
    TextView txtpost;
    ImageView imghinhsp;
    RatingBar rateting;
    TextInputEditText edtcontent;

    feedback_products feedback=new feedback_products();
    FeedbackViewModel feedbackViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        feedbackViewModel = ViewModelProviders.of(this).get(FeedbackViewModel.class);
        initView();
    }

    private void getcontent() {
        String content=edtcontent.getText().toString();
        if(content!=null){
            feedback.setContent(content);
        }else{
            Toast.makeText(this, "Bạn phải nhập nội dung", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        txttensp=findViewById(R.id.txttensanpham);
        txtpost=findViewById(R.id.txtpost);
        imghinhsp=findViewById(R.id.imghinhsp);
        rateting=findViewById(R.id.rateingpro);
        edtcontent=findViewById(R.id.edtcontent);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(user1s event) { // get model test
        feedback.setIdUser(event.getId());
        feedback.setName(event.getName());
    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(products event) { // get model test
        feedback.setIdProduct(event.getId());
    }


}

