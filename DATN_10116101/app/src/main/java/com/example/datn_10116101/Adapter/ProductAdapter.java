package com.example.datn_10116101.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.datn_10116101.model.products;
import com.example.datn_10116101.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

        private List<products> notes;
        private Context context;

        public ProductAdapter(List<products> notes, Context context) {
            this.notes = notes;
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_product, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            products currentpro = notes.get(position);
            holder.textview_title.setText(currentpro.getName());
            holder.textview_description.setText(currentpro.getDescribe());
            holder.textview_giachinh.setText(format(currentpro.getPrice()));
            holder.txtdaban.setText("Đã bán: "+currentpro.getDaban());
            Picasso.get()
                    .load(""+currentpro.getImage())
                   .resize(150, 150)
                   .centerCrop()
                    .into(holder.imgageview_hinhanhsanpham);

        }

        @Override
        public int getItemCount() {
            return notes.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView textview_description;
            TextView textview_title;
            TextView textview_giachinh;
            TextView txtdaban;
            ImageView imgageview_hinhanhsanpham;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                textview_title = itemView.findViewById(R.id.textview_title);
                textview_description = itemView.findViewById(R.id.textview_description);
                textview_giachinh = itemView.findViewById(R.id.textview_giachinh);
                imgageview_hinhanhsanpham = itemView.findViewById(R.id.imgageview_hinhanhsanpham);
                txtdaban=itemView.findViewById(R.id.txtdaban);

            }
        }


        public String format(double number){
            NumberFormat formatter = new DecimalFormat("#,###,###");
            return formatter.format(number);
        }
    }