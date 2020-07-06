package com.example.datn_10116101.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.datn_10116101.Activity.BaseActivity;
import com.example.datn_10116101.Adapter.ProductAdapter;
import com.example.datn_10116101.Admin.Adapter.BanchaynhatAdapter;
import com.example.datn_10116101.Admin.viewModel.AdminViewModel;
import com.example.datn_10116101.databinding.ActivitybanchayBinding;
import com.example.datn_10116101.model.products;

import java.util.ArrayList;
import java.util.List;

public class BanChayNhatActivity extends BaseActivity implements BanchaynhatAdapter.OnClickItemBills {
    ActivitybanchayBinding binding;
    ArrayList<products> listproduct = new ArrayList<>();
    BanchaynhatAdapter adapter;
    AdminViewModel adminViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitybanchayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        adminViewModel = new ViewModelProvider(this).get(AdminViewModel.class);
        initAdapter();
        populateData();
    }

    private void populateData() {
        adminViewModel.loadSanphamBanChay().observe(this, new Observer<List<products>>() {
            @Override
            public void onChanged(List<products> products) {
                if (products != null) {
                    listproduct.addAll(products);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initAdapter() {
        adapter = new BanchaynhatAdapter(BanChayNhatActivity.this, listproduct, BanChayNhatActivity.this);
        binding.rcproduct.setHasFixedSize(true);
        binding.rcproduct.setLayoutManager(new LinearLayoutManager(BanChayNhatActivity.this, RecyclerView.VERTICAL, false));
        binding.rcproduct.setAdapter(adapter);
    }

    @Override
    public void onCLick(int type, products products) {

    }
}