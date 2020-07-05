package com.example.datn_10116101.Fragment.CaNhan;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.example.datn_10116101.Activity.BaseActivity;
import com.example.datn_10116101.Adapter.QuanlysanphamdaxemAdapter;
import com.example.datn_10116101.Model.Quanlysanphamdaxem;
import com.example.datn_10116101.R;
import com.example.datn_10116101.config.Database;

import java.util.ArrayList;

public class QuanLySanPhamDaXemActivity extends BaseActivity {
    public  SQLiteDatabase database;
    ImageView imageview_thoat;
    public  QuanlysanphamdaxemAdapter quanlysanphamdaxemAdapter;
    RecyclerView recylerview_quanlysanphamdaxem;
    StaggeredGridLayoutManager layoutManager;
     ArrayList<Quanlysanphamdaxem> Quanlysanphamdaxem = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlysanphamdaxem);
        Anhxa();
        Quanlysanphamdaxem();
    }

    private void Anhxa() {
        database = Database.initDatabase(this, "huhu.db");
        imageview_thoat = findViewById(R.id.imageview_thoat);
        recylerview_quanlysanphamdaxem = findViewById(R.id.recylerview_quanlysanphamdaxem);
        imageview_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Quanlysanphamdaxem() {
        Cursor cursor = database.rawQuery("select * from quanlysanphamdaxem", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String tenproduct = cursor.getString(1);
            String hinhanhproduct = cursor.getString(2);
            String motaproduct = cursor.getString(3);
            int idloaisanpham = cursor.getInt(4);
            Quanlysanphamdaxem.add( new Quanlysanphamdaxem( id, tenproduct, hinhanhproduct,  motaproduct, idloaisanpham));
        }
        quanlysanphamdaxemAdapter = new QuanlysanphamdaxemAdapter(Quanlysanphamdaxem,this);
        recylerview_quanlysanphamdaxem.setHasFixedSize(false);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recylerview_quanlysanphamdaxem.setLayoutManager(layoutManager);
        recylerview_quanlysanphamdaxem.setAdapter(quanlysanphamdaxemAdapter);
    }
}

