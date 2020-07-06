package com.example.datn_10116101.Activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.datn_10116101.Adapter.TimKiemSpinnerAdapter;
import com.example.datn_10116101.Adapter.LoadMoreAdapter;
import com.example.datn_10116101.BaseResponse.ProductBaseResponse;
import com.example.datn_10116101.model.product_types;
import com.example.datn_10116101.model.products;
import com.example.datn_10116101.R;
import com.example.datn_10116101.ViewModel.ProductTypeViewModel;
import com.example.datn_10116101.ViewModel.ProductViewModel;

import java.util.ArrayList;
import java.util.List;


public class LoadMoreActivity extends BaseActivity {
    Spinner spinner_sanpham;
    ProductTypeViewModel productTypeViewModel;
    TimKiemSpinnerAdapter adapterSpinner;
    ArrayList<product_types> list=new ArrayList<>();

    RecyclerView recyclerView;
    LoadMoreAdapter loadMoreAdapter;
    ArrayList<products> rowsArrayList = new ArrayList<>();
    GridLayoutManager layoutManager;
    boolean isLoading = false;
    ProductViewModel productViewModel;

    int page=1;
    int idproduct=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadmore);
        productTypeViewModel = ViewModelProviders.of(this).get(ProductTypeViewModel.class); // phải có
        productViewModel=ViewModelProviders.of(this).get(ProductViewModel.class); // phải có
        Anhxa();
            // load được snpinner về
            // gửi lên id của spinner ý cho api call
        setupSnpinner();

        populateData();
        initScrollListener();
        spinner_sanpham.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // call api load dữ liệu rồi set lại cho thằng recyclerview
                idproduct=list.get(position).getId();
                productViewModel.LoadProductwithType(1,list.get(position).getId()).observe(LoadMoreActivity.this, new Observer<ProductBaseResponse>() {
                    @Override
                    public void onChanged(ProductBaseResponse productBaseResponse) {

                        rowsArrayList.clear();
                        rowsArrayList.addAll((ArrayList<products>) productBaseResponse.getData());
                        loadMoreAdapter.notifyDataSetChanged();
                        isLoading = false;
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setupSnpinner() {
        productTypeViewModel.getProducttype().observe(LoadMoreActivity.this, new Observer<List<product_types>>() {
            @Override
            public void onChanged(List<product_types> producttype) {
                // populate data
                list.addAll(producttype);
                adapterSpinner=new TimKiemSpinnerAdapter(LoadMoreActivity.this,
                        R.layout.item_spinner,list);
               // adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_sanpham.setAdapter(adapterSpinner);
            }
        });
    }

    private void Anhxa() {
        spinner_sanpham  = findViewById(R.id.spinner_sanpham);
        recyclerView = findViewById(R.id.recyclerView);
    }
    private void populateData() {
        productViewModel.LoadProductwithType(1,1).observe(this, new Observer<ProductBaseResponse>() {
            @Override
            public void onChanged(ProductBaseResponse productBaseResponse) {
                rowsArrayList = (ArrayList<products>) productBaseResponse.getData();
                recyclerView.setHasFixedSize(true);
                layoutManager = new GridLayoutManager(LoadMoreActivity.this,2);
                recyclerView.setLayoutManager(layoutManager);
                loadMoreAdapter = new LoadMoreAdapter(rowsArrayList);
                recyclerView.setAdapter(loadMoreAdapter);
            }
        });
    }
    private void initScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                GridLayoutManager staggeredGridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                if (!isLoading) {
                    if (staggeredGridLayoutManager != null && staggeredGridLayoutManager.findLastCompletelyVisibleItemPosition() == rowsArrayList.size() - 1) {
                        loadMore();
                        isLoading = true;
                    }
                }

            }
        });
    }
    private void loadMore() {
        rowsArrayList.add(null);
        loadMoreAdapter.notifyItemInserted(rowsArrayList.size() - 1);
        page=page+1;
        productViewModel.LoadProductwithType(page,idproduct).observe(this, new Observer<ProductBaseResponse>() {
            @Override
            public void onChanged(ProductBaseResponse productBaseResponse) {
                if(productBaseResponse.getLastPage()>page){
                    rowsArrayList.remove(rowsArrayList.size() - 1);
                    int scrollPosition = rowsArrayList.size();
                    loadMoreAdapter.notifyItemRemoved(scrollPosition);
                    rowsArrayList.addAll((ArrayList<products>) productBaseResponse.getData());
                    loadMoreAdapter.notifyDataSetChanged();
                    isLoading = false;
                }else{
                    Toast.makeText(LoadMoreActivity.this, "Mặt hàng này đã hết, quý khách vui lòng xem mặt hàng khác ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}