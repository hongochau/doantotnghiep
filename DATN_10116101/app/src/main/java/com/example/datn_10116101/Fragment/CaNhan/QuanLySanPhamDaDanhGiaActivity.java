package com.example.datn_10116101.Fragment.CaNhan;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.datn_10116101.Activity.BaseActivity;
import com.example.datn_10116101.Adapter.QuanlysanphamdadanhgiaAdapter;
import com.example.datn_10116101.Model.FeedbackUser;
import com.example.datn_10116101.Model.user1s;
import com.example.datn_10116101.R;
import com.example.datn_10116101.ViewModel.FeedbackViewModel;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class QuanLySanPhamDaDanhGiaActivity extends BaseActivity {
    int iduser;
    FeedbackViewModel feedbackViewModel;
    ListView lvDanhGia;
    ArrayList<FeedbackUser> list=new ArrayList<>();
    QuanlysanphamdadanhgiaAdapter adapterSanPhamDanhGia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanphamdadanhgia);
        feedbackViewModel= ViewModelProviders.of(this).get(FeedbackViewModel.class);
        initview();
    }

    private void initview() {
        lvDanhGia=findViewById(R.id.lvDanhGia);
    }

    private void populateData() {
        feedbackViewModel.getfeedbackwithUser(iduser).observe(this, new Observer<List<FeedbackUser>>() {
            @Override
            public void onChanged(List<FeedbackUser> feedbackUsers) {
                if(feedbackUsers.size()>0){
                    list.addAll(feedbackUsers);
                    adapterSanPhamDanhGia=new QuanlysanphamdadanhgiaAdapter(list,getApplicationContext());
                    lvDanhGia.setAdapter(adapterSanPhamDanhGia);
                }else{
                    Toast.makeText(QuanLySanPhamDaDanhGiaActivity.this, "Không có đánh giá", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(user1s event) { // get model test
        iduser=event.getId();
        populateData();
    }
}
