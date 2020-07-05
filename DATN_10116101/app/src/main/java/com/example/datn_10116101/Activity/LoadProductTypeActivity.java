package com.example.datn_10116101.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datn_10116101.Adapter.LoadMoreAdapter;
import com.example.datn_10116101.Adapter.ProductTypeAdapter;
import com.example.datn_10116101.BaseResponse.ProductBaseResponse;
import com.example.datn_10116101.ItemClickSupport;
import com.example.datn_10116101.Model.product_types;
import com.example.datn_10116101.Model.products;
import com.example.datn_10116101.R;
import com.example.datn_10116101.ViewModel.ProductTypeViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class LoadProductTypeActivity extends BaseActivity {
    RecyclerView recyclerView,recyclerview_sanpham;
    FloatingActionButton btnscrolltop;
    LoadMoreAdapter loadMoreAdapter;
    ArrayList<products> rowsArrayList = new ArrayList<>();
    GridLayoutManager layoutManager;
    boolean isLoading = false;
    ProductTypeViewModel productTypeViewModel;
    int page=1;
    private ProgressDialog dialog;
    SearchView searchView;
    TextView txtbadge, txttitle;
    ImageView imgMenu;
    ArrayList<products> listproduct = new ArrayList<>();

    product_types protypes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_product_type);
        productTypeViewModel = ViewModelProviders.of(this).get(ProductTypeViewModel.class);
        initview();
        initScrollListener();
        imgbackEvent();
        btnscrolltop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView
                        .getLayoutManager();
                layoutManager.scrollToPositionWithOffset(0, 0);
            }
        });

    }

    private void imgbackEvent() {
        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initview() {
        recyclerview_sanpham = findViewById(R.id.recyclerview_sanpham);
        recyclerView = findViewById(R.id.recyclerView);
        btnscrolltop=findViewById(R.id.btnscrolltop);
        imgMenu = findViewById(R.id.imgage_quaylai);
        txtbadge = findViewById(R.id.text);
        txttitle = findViewById(R.id.txttile);
        searchView = findViewById(R.id.imageview_timkiem);
    }

    private void populateData(int idtype) {
        productTypeViewModel.LoadListProductType(idtype,1).observe(this, new Observer<ProductBaseResponse>() {
            @Override
            public void onChanged(ProductBaseResponse productBaseResponse) {
                rowsArrayList = (ArrayList<products>) productBaseResponse.getData();
                recyclerview_sanpham.setHasFixedSize(true);
                layoutManager = new GridLayoutManager(LoadProductTypeActivity.this,2);
                recyclerview_sanpham.setLayoutManager(layoutManager);
                loadMoreAdapter = new LoadMoreAdapter(rowsArrayList);
                recyclerview_sanpham.setAdapter(loadMoreAdapter);


                ItemClickSupport.addTo(recyclerview_sanpham).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        products pro=rowsArrayList.get(position);
                        EventBus.getDefault().postSticky(pro);
                        startActivity(new Intent(LoadProductTypeActivity.this, ProductDetailActivity.class));
                    }
                });

            }
        });
    }

    private void initScrollListener() {
        recyclerview_sanpham.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                        dialog = new ProgressDialog(LoadProductTypeActivity.this);
                        dialog.setMessage("Đang load sản phẩm, quý khách vui lòng đợi trong giây lát...");
                        dialog.show();
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
        productTypeViewModel.LoadListProductType(protypes.getId(),page).observe(this, new Observer<ProductBaseResponse>() {
            @Override
            public void onChanged(ProductBaseResponse productBaseResponse) {
                if(productBaseResponse.getLastPage()>page){
                    rowsArrayList.remove(rowsArrayList.size() - 1);
                    int scrollPosition = rowsArrayList.size();
                    loadMoreAdapter.notifyItemRemoved(scrollPosition);
                    rowsArrayList.addAll((ArrayList<products>) productBaseResponse.getData());
                    loadMoreAdapter.notifyDataSetChanged();
                    isLoading = false;
                    dialog.dismiss();
                }else{
                    dialog.dismiss();
                    Toast.makeText(LoadProductTypeActivity.this, "Mặt hàng này đã hết, quý khách vui lòng xem mặt hàng khác", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(product_types event) { // get model test
        protypes=new product_types();
        protypes=event;
        populateData(protypes.getId());

    }
}
