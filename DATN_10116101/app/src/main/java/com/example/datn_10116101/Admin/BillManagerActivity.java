package com.example.datn_10116101.Admin;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.datn_10116101.Activity.BaseActivity;
import com.example.datn_10116101.Admin.Adapter.BillsAdapter;
import com.example.datn_10116101.Admin.viewModel.AdminViewModel;
import com.example.datn_10116101.BaseResponse.ResponseBill;
import com.example.datn_10116101.model.HoaDon;
import com.example.datn_10116101.databinding.ActivityBillManagerBinding;

import java.util.ArrayList;

public class BillManagerActivity extends BaseActivity implements BillsAdapter.OnClickItemBills {
    ActivityBillManagerBinding binding;
    ArrayList<String> listSp = new ArrayList<>();
    ArrayList<HoaDon> listhoadon=new ArrayList<>();
    BillsAdapter billsAdapter;
    AdminViewModel adminViewModel;
    int pos;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBillManagerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adminViewModel = new ViewModelProvider(this).get(AdminViewModel.class);
        setupList();
        setupSpinner();

    }

    private void setupList() {
        billsAdapter=new BillsAdapter(this,listhoadon,this);
        binding.rclistbill.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
        binding.rclistbill.setHasFixedSize(true);
        binding.rclistbill.setAdapter(billsAdapter);
    }

    private void setupSpinner() {
        listSp.add("Đơn hàng");
        listSp.add("Đang giao");
        listSp.add("Đã giao");
        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listSp);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adp1);

        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos=position;
                listhoadon.clear();
                adminViewModel.getallWithtype(listSp.get(position)).observe(BillManagerActivity.this, new Observer<ResponseBill>() {
                    @Override
                    public void onChanged(ResponseBill responseBill) {
                        listhoadon.clear();
                        if(responseBill.getStatus().equals("SUCCESS")){
                            listhoadon.addAll(responseBill.getData());
                            billsAdapter.notifyDataSetChanged();
                        }
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onCLick(int type, final HoaDon hoaDon) {
       adminViewModel.changeStatus(listSp.get(pos+1),hoaDon.getId()).observe(this, new Observer<ResponseBill>() {
           @Override
           public void onChanged(ResponseBill responseBill) {
               listhoadon.remove(hoaDon);
               billsAdapter.notifyDataSetChanged();
               Toast.makeText(BillManagerActivity.this, ""+responseBill.getMess(), Toast.LENGTH_SHORT).show();
           }
       });
    }
}