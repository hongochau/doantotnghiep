package com.example.datn_10116101.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datn_10116101.model.ChiTietHoaDon;
import com.example.datn_10116101.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class ChiTietHoaDonAdapter extends RecyclerView.Adapter<ChiTietHoaDonAdapter.ViewHolder> {

    private List<ChiTietHoaDon> notes;
    private Context context;

    public ChiTietHoaDonAdapter(List<ChiTietHoaDon> notes, Context context) {
        this.notes = notes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chitiethoadon, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { // đưa dữ liệu vào adapter
        ChiTietHoaDon currentpro = notes.get(position);
        holder.textview_id.setText("Mã hóa đơn: "+currentpro.getId());
        holder.textview_idhoadon.setText("Mã hóa đơn: "+currentpro.getIdhoadon());
        holder.textview_masanpham.setText("Mã sản phẩm: "+currentpro.getMasanpham());
        holder.textview_soluong.setText("Số lượng: "+currentpro.getSoluong());
        holder.textview_gia.setText("Giá: "+currentpro.getGia());
        holder.textview_tensanpham.setText("Tên sản phẩm: "+currentpro.getTensanpham());
        /*Picasso.get()
                .load(""+currentpro.getHinhanh())
                .resize(150, 150)
                .centerCrop()
                .into(holder.image_hinhanh);
        holder.textview_mota.setText(currentpro.getMota());*/

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textview_id;
        TextView textview_idhoadon;
        TextView textview_masanpham;
        TextView textview_soluong;
        TextView textview_gia;
        TextView textview_tensanpham;
        ImageView image_hinhanh;
        TextView textview_mota;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textview_id = itemView.findViewById(R.id.textview_id);
            textview_idhoadon = itemView.findViewById(R.id.textview_idhoadon);
            textview_masanpham = itemView.findViewById(R.id.textview_masanpham);
            textview_soluong = itemView.findViewById(R.id.textview_soluong);
            textview_gia = itemView.findViewById(R.id.textview_gia);
            textview_tensanpham = itemView.findViewById(R.id.textview_tensanpham);
            //image_hinhanh = itemView.findViewById(R.id.image_hinhanh);
            //textview_mota = itemView.findViewById(R.id.textview_mota);

        }
    }

    public String format(double number){ // format ttừ số thường thành số decimal
        NumberFormat formatter = new DecimalFormat("#,###,###");
        return formatter.format(number);
    }
}