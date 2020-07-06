package com.example.datn_10116101.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.datn_10116101.Adapter.FeedbackAdapter;
import com.example.datn_10116101.BaseResponse.BaseResponseFeedback;
import com.example.datn_10116101.model.feedback_products;
import com.example.datn_10116101.model.products;
import com.example.datn_10116101.R;
import com.example.datn_10116101.ViewModel.FeedbackViewModel;
import com.example.datn_10116101.config.Database;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ProductDetailActivity extends BaseActivity {
    TextView txttitle, txtprice, txtdescription;
    RecyclerView rclistfeedback;
    Button btnchonmua, button_vietnhanxet, btncheckvoucher;
    EditText edtvou;
    SQLiteDatabase database;
    ArrayList<products> listproduct = new ArrayList<>();
    products pro;
    FeedbackViewModel feedbackViewModel;
    FeedbackAdapter feedbackAdapter;
    ArrayList<feedback_products> list=new ArrayList<>();
    LinearLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        feedbackViewModel=ViewModelProviders.of(this).get(FeedbackViewModel.class);
        initview();
        btnchonmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ProductDetailActivity.this,GioHangActivity.class);
                startActivity(intent);
                int idproduct=pro.getId();
                int cout=1;
                int price=pro.getPrice();
                String voucher=edtvou.getText().toString();
                Calendar c = Calendar.getInstance();
                SimpleDateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy");
                String date = dateformat.format(c.getTime());
                String tenproduct = pro.getName();
                String hinhanhproduct = pro.getImage();
                String motaproduct = pro.getDescribe();
                ContentValues contentValues=new ContentValues();
                contentValues.put("idproduct", idproduct);
                contentValues.put("cout",cout);
                contentValues.put("price",price);
                contentValues.put("voucher",voucher);
                contentValues.put("date",date);
                contentValues.put("tenproduct",tenproduct);
                contentValues.put("hinhanhproduct",hinhanhproduct);
                contentValues.put("motaproduct",motaproduct);
                database.insert("cart",null,contentValues);
            }
        });
    }

    private void initview() {
        database= Database.initDatabase(this,"huhu.db");
        txttitle = findViewById(R.id.txttitle);
        txtprice = findViewById(R.id.txtprice);
        txtdescription = findViewById(R.id.txtdescription);
        rclistfeedback = findViewById(R.id.rclistfeedback);
        btnchonmua = findViewById(R.id.btnchonmua);
        button_vietnhanxet = findViewById(R.id.button_vietnhanxet);
        button_vietnhanxet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (ProductDetailActivity.this,PhanHoiActivity.class);
                startActivity(intent);
            }
        });
        btncheckvoucher = findViewById(R.id.checkvoucher);
        edtvou = findViewById(R.id.edtvoucher);
        String id = getIntent().getStringExtra("noti");
        if (id != null) {

        }
    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(products event) { // get model test
        pro = new products();
        pro = event;
        txttitle.setText("" + event.getName());
        txtdescription.setText("" + event.getDescribe());
        txtprice.setText("" + event.getPrice());
        getFeedback(pro.getId(),1);
    }

    private void getFeedback(Integer id, int i) {
        feedbackViewModel.getfeedback(id,i).observe(this, new Observer<BaseResponseFeedback>() {
            @Override
            public void onChanged(BaseResponseFeedback baseResponseFeedback) {
                System.out.println(baseResponseFeedback.getData());
                list.addAll(baseResponseFeedback.getData());
                feedbackAdapter =new FeedbackAdapter(list);
                rclistfeedback.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(ProductDetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
                rclistfeedback.setLayoutManager(layoutManager);
                rclistfeedback.setAdapter(feedbackAdapter);
            }
        });
    }
}
