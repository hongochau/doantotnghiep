package com.example.datn_10116101.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.datn_10116101.model.product_types;
import com.example.datn_10116101.R;
import com.example.datn_10116101.Service.RetrofitService;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductTypeAdapter extends RecyclerView.Adapter<ProductTypeAdapter.ViewHolder> {
    private List<product_types> notes;
    private Context context;

    public ProductTypeAdapter(List<product_types> notes, Context context) {
        this.notes = notes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product_type, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        product_types currentpro = notes.get(position);
        holder.textview_tenloaisanpham.setText(currentpro.getName());
        Picasso.get()
                .load(""+ RetrofitService.basePath+currentpro.getImage())
                .placeholder(R.mipmap.logo)
                .error(R.drawable.cast_album_art_placeholder_large)
                .into(holder.imageview_hinhloaisanpham);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textview_tenloaisanpham;
        ImageView imageview_hinhloaisanpham;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textview_tenloaisanpham = itemView.findViewById(R.id.textview_tenloaisanpham);
            imageview_hinhloaisanpham = itemView.findViewById(R.id.imageview_hinhloaisanpham);
        }
    }

}