package com.example.datn_10116101.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datn_10116101.Model.HoaDon;
import com.example.datn_10116101.Model.products;
import com.example.datn_10116101.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder> {

    private List<HoaDon> notes;
    private Context context;

    public HoaDonAdapter(List<HoaDon> notes, Context context) {
        this.notes = notes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hoadon, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HoaDon currentpro = notes.get(position);
        holder.textview_iduser.setText("Mã người mua: "+currentpro.getId());
        holder.textview_Status.setText(currentpro.getStatus());
        holder.textview_tonggiatien.setText("Tổng tiền: "+format(currentpro.getTotalprice()));
        holder.textview_thoigiandat.setText(currentpro.getBookingtime());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textview_iduser;
        TextView textview_Status;
        TextView textview_tonggiatien;
        TextView textview_thoigiandat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textview_Status = itemView.findViewById(R.id.textview_Status);
            textview_iduser = itemView.findViewById(R.id.textview_iduser);
            textview_tonggiatien = itemView.findViewById(R.id.textview_tonggiatien);
            textview_thoigiandat = itemView.findViewById(R.id.textview_thoigiandat);

        }
    }

    public String format(double number){
        NumberFormat formatter = new DecimalFormat("#,###,###");
        return formatter.format(number);
    }
}