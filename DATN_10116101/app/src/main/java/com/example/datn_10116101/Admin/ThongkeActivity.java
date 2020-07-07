package com.example.datn_10116101.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.example.datn_10116101.Admin.Adapter.BillsAdapter;
import com.example.datn_10116101.Admin.viewModel.AdminViewModel;
import com.example.datn_10116101.databinding.ActivityBillManagerBinding;
import com.example.datn_10116101.databinding.ActivityThongkeBinding;
import com.example.datn_10116101.model.HoaDon;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ThongkeActivity extends AppCompatActivity implements BillsAdapter.OnClickItemBills {
    ActivityThongkeBinding binding;
    AdminViewModel adminViewModel;

    BillsAdapter billsAdapter;
    ArrayList<HoaDon> listhoadon=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThongkeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        adminViewModel = new ViewModelProvider(this).get(AdminViewModel.class);
        setuplist();
        ClickDate();
    }

    private void setuplist() {
        billsAdapter=new BillsAdapter(ThongkeActivity.this,listhoadon,ThongkeActivity.this);
        binding.rcbill.setLayoutManager(new LinearLayoutManager(ThongkeActivity.this, RecyclerView.VERTICAL,false));
        binding.rcbill.setHasFixedSize(true);
        binding.rcbill.setAdapter(billsAdapter);
    }

    private void ClickDate() {
        binding.edtfrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int nam = calendar.get(Calendar.YEAR);     // lấy ra năm hiện tại
                int thang = calendar.get(calendar.MONTH);   // tháng hiện tại
                int ngay = calendar.get(Calendar.DATE);     // ngày hiện tại
                DatePickerDialog datePickerDialog = new DatePickerDialog(ThongkeActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        // i=nawm , i1 = thang ,i2=ngay
                        calendar.set(i, i1, i2);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        binding.edtfrom.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, nam, thang, ngay);
                datePickerDialog.show();
            }
        });

        binding.edtto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int nam = calendar.get(Calendar.YEAR);     // lấy ra năm hiện tại
                int thang = calendar.get(calendar.MONTH);   // tháng hiện tại
                int ngay = calendar.get(Calendar.DATE);     // ngày hiện tại
                DatePickerDialog datePickerDialog = new DatePickerDialog(ThongkeActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        // i=nawm , i1 = thang ,i2=ngay
                        calendar.set(i, i1, i2);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        binding.edtto.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, nam, thang, ngay);
                datePickerDialog.show();
            }
        });

        binding.btnload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminViewModel.getHoadonbeetween(binding.edtfrom.getText().toString(),
                        binding.edtto.getText().toString()).observe(ThongkeActivity.this, new Observer<List<HoaDon>>() {
                    @Override
                    public void onChanged(List<HoaDon> hoaDons) {
                        listhoadon.clear();
                        listhoadon.addAll(hoaDons);
                        billsAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

    }


    @Override
    public void onCLick(int type, HoaDon hoaDon) {

    }
}