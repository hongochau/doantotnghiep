package com.example.datn_10116101.Admin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datn_10116101.model.HoaDon;
import com.example.datn_10116101.databinding.ItemBillsBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BillsAdapter extends RecyclerView.Adapter<BillsAdapter.ViewHolder>{
    Context context;
    List<HoaDon> list;
    OnClickItemBills onClickItemBills;

    public BillsAdapter(Context context, List<HoaDon> list, OnClickItemBills onClickItemBills) {
        this.context = context;
        this.list = list;
        this.onClickItemBills = onClickItemBills;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(ItemBillsBinding.inflate(layoutInflater,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setBills(list.get(position));
        Picasso.get()
                .load(list.get(position).getImgfb())
                .fit()
                .centerCrop()
                .into( holder.binding.imguser);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemBillsBinding binding;

        public ViewHolder(@NonNull ItemBillsBinding itemBillsBinding) {
            super(itemBillsBinding.getRoot());
            itemView.setOnClickListener(this);
            binding=itemBillsBinding;
        }

        @Override
        public void onClick(View v) {
            onClickItemBills.onCLick(getItemViewType(),list.get(getAdapterPosition()));
        }
    }

    public interface OnClickItemBills
    {
         void onCLick(int type, HoaDon hoaDon);
    }
}
