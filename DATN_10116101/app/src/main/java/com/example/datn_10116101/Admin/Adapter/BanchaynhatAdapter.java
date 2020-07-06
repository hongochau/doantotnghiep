package com.example.datn_10116101.Admin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datn_10116101.databinding.ItemProductBinding;
import com.example.datn_10116101.model.HoaDon;
import com.example.datn_10116101.databinding.ItemBillsBinding;
import com.example.datn_10116101.model.products;
import com.google.android.gms.analytics.ecommerce.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BanchaynhatAdapter extends RecyclerView.Adapter<BanchaynhatAdapter.ViewHolder>{
    Context context;
    List<products> list;
    OnClickItemBills onClickItemBills;

    public BanchaynhatAdapter(Context context, List<products> list, OnClickItemBills onClickItemBills) {
        this.context = context;
        this.list = list;
        this.onClickItemBills = onClickItemBills;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(ItemProductBinding.inflate(layoutInflater,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get()
                .load(list.get(position).getImage())
                .fit()
                .centerCrop()
                .into( holder.binding.imgageviewHinhanhsanpham);
        holder.binding.textviewTitle.setText(""+list.get(position).getName());
        holder.binding.textviewDescription.setText(""+list.get(position).getDescribe());
        holder.binding.textviewGiachinh.setText(""+list.get(position).getPrice());
        holder.binding.txtdaban.setText("Đã bán: "+list.get(position).getDaban());
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
            binding=itemProductBinding;
        }
        @Override
        public void onClick(View v) {
            onClickItemBills.onCLick(getItemViewType(),list.get(getAdapterPosition()));
        }
    }

    public interface OnClickItemBills
    {
        void onCLick(int type, products products);
    }
}
