package com.example.datn_10116101.MenuToobar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datn_10116101.Activity.BaseActivity;
import com.example.datn_10116101.Adapter.TimKiemAdapter;
import com.example.datn_10116101.Model.products;
import com.example.datn_10116101.R;
import com.example.datn_10116101.ViewModel.ProductViewModel;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class TimKiemActivity extends BaseActivity {
    ListView listview_sanpham;
    TextView textview_trongtimkiem;
    ImageView imageview_thoat;

    TimKiemAdapter timKiemAdapter;
    ArrayList<products> listproductl=new ArrayList<>();

    ProductViewModel productViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timkiem);
        Anhxa();
    }

    private void populateData(String name) {
        productViewModel.searchProduct(name).observe(this, new Observer<List<products>>() {
            @Override
            public void onChanged(List<products> products) {
                if(products!=null){
                    listproductl.addAll(products);
                    timKiemAdapter = new TimKiemAdapter(listproductl,TimKiemActivity.this);
                    listview_sanpham.setAdapter(timKiemAdapter);
                }else{
                    listview_sanpham.setEmptyView(textview_trongtimkiem);
                }
            }
        });
    }

    private void Anhxa() {
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class); // phải có
        listview_sanpham = findViewById(R.id.listview_sanpham);
        textview_trongtimkiem = findViewById(R.id.textview_trongtimkiem);
        imageview_thoat = findViewById(R.id.imageview_thoat);
        imageview_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String event) {
        Toast.makeText(this, ""+event, Toast.LENGTH_SHORT).show();
        populateData(event);
    }
}



