package com.example.datn_10116101.Admin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datn_10116101.databinding.ItemProductBinding;
import com.example.datn_10116101.model.products;
import com.google.android.gms.analytics.ecommerce.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdminAdapter extends RecyclerView.Adapter<ProductAdminAdapter.ViewHolder> {
    Context context;
    List<products> list;
    OnClickItemProduct onClickItemProduct;

    public ProductAdminAdapter(Context context, List<products> list, OnClickItemProduct onClickItemProduct) {
        this.context = context;
        this.list = list;
        this.onClickItemProduct = onClickItemProduct;
    }

//    @Override
//    public int getItemViewType(int position) {
//        return list.get(position).getTypeView();
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ;
        //   return new ViewHolder((ItemProductBinding) DataBindingUtil.inflate(layoutInflater, R.layout.item_product, parent, false));
        return new ViewHolder(ItemProductBinding.inflate(layoutInflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        products pro = list.get(position);
        Picasso.get()
                .load(list.get(position).getImage())
                .fit()
                .centerCrop()
                .into(holder.binding.imgageviewHinhanhsanpham);
        holder.binding.textviewTitle.setText("" + pro.getName());
        holder.binding.txtdaban.setText("Đã bán: " + pro.getDaban());
        holder.binding.textviewGiachinh.setText("" + pro.getPrice() + "đ");
        holder.binding.textviewDescription.setText("" + pro.getDescribe());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemProductBinding binding;

        public ViewHolder(@NonNull ItemProductBinding itemProductBinding) {
            super(itemProductBinding.getRoot());
            itemView.setOnClickListener(this);
            binding = itemProductBinding;
        }

        @Override
        public void onClick(View v) {
            onClickItemProduct.onCLickItemProduct(list.get(getAdapterPosition()), getAdapterPosition());
        }
    }

    public interface OnClickItemProduct {
        public void onCLickItemProduct(products product, int position);
    }
}