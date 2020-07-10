package com.example.datn_10116101.Admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.datn_10116101.Admin.Adapter.ProductAdminAdapter;
import com.example.datn_10116101.Admin.viewModel.AdminProductViewModel;
import com.example.datn_10116101.Admin.viewModel.AdminViewModel;
import com.example.datn_10116101.BaseResponse.productObjectResponse;
import com.example.datn_10116101.BaseResponse.productResponse;
import com.example.datn_10116101.R;
import com.example.datn_10116101.ViewModel.ProductTypeViewModel;
import com.example.datn_10116101.databinding.ActivityBillManagerBinding;
import com.example.datn_10116101.databinding.ActivityProductDetailBinding;
import com.example.datn_10116101.databinding.ActivityProductManagerBinding;
import com.example.datn_10116101.databinding.DialogProductBinding;
import com.example.datn_10116101.model.product_types;
import com.example.datn_10116101.model.products;

import java.util.ArrayList;
import java.util.List;

public class ProductManagerActivity extends AppCompatActivity implements ProductAdminAdapter.OnClickItemProduct {
    ActivityProductManagerBinding binding;
    DialogProductBinding dialogBinding;

    ArrayList<products> listproduct = new ArrayList<>();
    ArrayList<product_types> listsp = new ArrayList<>();
    ProductAdminAdapter adminAdapter;
    AdminProductViewModel adminProductViewModel;
    ProductTypeViewModel productTypeViewModel;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductManagerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        adminProductViewModel = new ViewModelProvider(this).get(AdminProductViewModel.class);
        productTypeViewModel = ViewModelProviders.of(this).get(ProductTypeViewModel.class); // phải có
        initAdapter();
        populateData();
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                products products = new products();
                displayAlertDialog(products, 1, "ADD");
            }
        });
    }

    private void populateData() { // load sản phẩm
        adminProductViewModel.getAllProduct().observe(this, new Observer<productResponse>() {
            @Override
            public void onChanged(productResponse productResponse) {
                if (productResponse.getStatus().equals("SUCCESS")) {
                    listproduct.addAll(productResponse.getData());
                    adminAdapter.notifyDataSetChanged();

                }
            }
        });
    }

    private void initAdapter() {
        adminAdapter = new ProductAdminAdapter(this, listproduct, this);
        binding.recyclerView2.setHasFixedSize(true);
        binding.recyclerView2.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        binding.recyclerView2.setAdapter(adminAdapter);
    }


    @Override
    public void onCLickItemProduct(products product, int position) {
        displayAlertDialog(product,position,"UPDATE");
    }

    public void displayAlertDialog(final products product, final int position, final String type) {
        dialogBinding = DialogProductBinding.inflate(getLayoutInflater());
        View root = dialogBinding.getRoot();

        AlertDialog.Builder alert = new AlertDialog.Builder(this, R.style.myFullscreenAlertDialogStyle);
        alert.setView(root);
        dialog = alert.create();

    if(!type.equals("ADD")){
        dialogBinding.edtprice.setText(""+product.getPrice());
        dialogBinding.edtdescribe.setText(""+product.getDescribe());
        dialogBinding.edtimage.setText(""+product.getImage());
        dialogBinding.edtname.setText(""+product.getName());
    }

        setupspinner(product);

        if (type.equals("ADD")) {
            dialogBinding.btnsua.setVisibility(View.INVISIBLE);
            dialogBinding.btnxoa.setVisibility(View.INVISIBLE);
        } else {
            dialogBinding.btnthem.setVisibility(View.INVISIBLE);
        }


        dialogBinding.btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialogBinding.btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                products pro=new products();
                pro.setPrice(Integer.parseInt(dialogBinding.edtprice.getText().toString()));
                pro.setDescribe(dialogBinding.edtdescribe.getText().toString());
                product_types type= (product_types) dialogBinding.spinner2.getSelectedItem();
                pro.setIdProductType(type.getId());
                pro.setImage(dialogBinding.edtimage.getText().toString());
                pro.setName(dialogBinding.edtname.getText().toString());
                addProduct(pro);
            }
        });
        dialogBinding.btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateProductFunction(product, position);
            }
        });
        dialogBinding.btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProductFunction(product, position);

            }
        });
        dialog.show();
    }

    private void setupspinner(final products mproducts) {
        productTypeViewModel.getProducttype().observe(this, new Observer<List<product_types>>() {
            @Override
            public void onChanged(List<product_types> product_types) {
                listsp.clear();
                listsp.add(new product_types(00, "chọn loại hàng", ""));
                listsp.addAll(product_types);
                ArrayAdapter<product_types> adapter =
                        new ArrayAdapter<product_types>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, listsp);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dialogBinding.spinner2.setAdapter(adapter);

                for (int i=0;i<listsp.size();i++){
                    if(listsp.get(i).getId().equals(mproducts.getIdProductType())){
                        dialogBinding.spinner2.setSelection(i);
                    }
                }

            }
        });
    }

    private void deleteProductFunction(products product, final int position) {
        adminProductViewModel.deleteProduct(product.getId()).observe(ProductManagerActivity.this, new Observer<productResponse>() {
            @Override
            public void onChanged(productResponse productResponse) {
                Toast.makeText(ProductManagerActivity.this, "" + productResponse.getMess(), Toast.LENGTH_SHORT).show();
                listproduct.remove(position);
                adminAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
    }

    private void updateProductFunction(final products product, final int position) {
        product.setPrice(Integer.parseInt(dialogBinding.edtprice.getText().toString()));
        product.setDescribe(dialogBinding.edtdescribe.getText().toString());
        product_types type= (product_types) dialogBinding.spinner2.getSelectedItem();
        product.setIdProductType(type.getId());
        product.setImage(dialogBinding.edtimage.getText().toString());
        product.setName(dialogBinding.edtname.getText().toString());

        adminProductViewModel.updateProduct(product.getId(),product.getIdProductType(),
                product.getName(),product.getDescribe(),product.getPrice()
                ,product.getImage()).observe(ProductManagerActivity.this, new Observer<productResponse>() {
            @Override
            public void onChanged(productResponse productResponse) {
                Toast.makeText(ProductManagerActivity.this, ""+productResponse.getMess(), Toast.LENGTH_SHORT).show();
                listproduct.set(position,product);
                adminAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
    }

    private void addProduct(final products product) {
        adminProductViewModel.CreateProduct(product.getIdProductType(),
                product.getName(),product.getDescribe(),product.getPrice()
                ,product.getImage()).observe(this, new Observer<productObjectResponse>() {
            @Override
            public void onChanged(productObjectResponse productObjectResponse) {
                Toast.makeText(ProductManagerActivity.this, ""+productObjectResponse.getMess(), Toast.LENGTH_SHORT).show();
                listproduct.add(product);
                adminAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
    }


}