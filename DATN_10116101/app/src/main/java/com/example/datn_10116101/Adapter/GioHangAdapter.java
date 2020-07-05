package com.example.datn_10116101.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datn_10116101.Activity.GioHangActivity;
import com.example.datn_10116101.Activity.MainActivity;
import com.example.datn_10116101.Model.Cart;
import com.example.datn_10116101.Model.products;
import com.example.datn_10116101.R;
import com.example.datn_10116101.Service.RetrofitService;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {
    private List<Cart> notes;
    private Context context;
    int giatri = 0;

    public GioHangAdapter(List<Cart> notes, Context context) {
        this.notes = notes;
        this.context = context;
    }

    @NonNull
    @Override
    public GioHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final GioHangAdapter.ViewHolder holder, final int position) {
        final Cart fe = notes.get(position);
        holder.textview_tensanpham.setText("Tên sản phẩm " + fe.getTenproduct());
        holder.textview_soluong.setText("Số lượng: " + fe.getCout());
        holder.textview_price.setText("Giá: " + format(fe.getPrice()*fe.getCout()));
        Picasso.get()
                .load("" + fe.getHinhanhproduct())
                .resize(80, 80)
                .centerCrop()
                .into(holder.imageview_hinhanh);

        holder.button_values_giatri.setText("" + fe.getCout());
        int sl = Integer.parseInt(holder.button_values_giatri.getText().toString());
        if (sl >= 10) {
            holder.button_plus_cong.setVisibility(View.INVISIBLE); // khi đến giá trị 10 thì dấu + ẩn dấu - hiện
            holder.button_minus_tru.setVisibility(View.VISIBLE);
        } else if (sl <= 1) {
            holder.button_minus_tru.setVisibility(View.INVISIBLE);
        } else if (sl >= 1) {
            holder.button_minus_tru.setVisibility(View.VISIBLE);
            holder.button_plus_cong.setVisibility(View.VISIBLE);
        }
        holder.button_plus_cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giatri = Integer.parseInt(holder.button_values_giatri.getText().toString());
                if (giatri > 9) {
                    Toast.makeText(context, "Không được vượt quá 10 sản phẩm ", Toast.LENGTH_SHORT).show();
                } else {
                    giatri++;
                    holder.button_values_giatri.setText("" + giatri);
                    fe.setCout(giatri);
                    GioHangActivity.Themgiohang(fe, position);
                }
            }
        });
        holder.button_minus_tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giatri = Integer.parseInt(holder.button_values_giatri.getText().toString());
                    giatri--;
                    holder.button_values_giatri.setText("" + giatri);
                    fe.setCout(giatri);
                    GioHangActivity.Themgiohang(fe, position); // gọi phương thức để giảm số lượng

            }
        });

        holder.imageview_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận !");
                builder.setMessage("Bạn thực sự muốn xóa sản phẩm khỏi giỏ hàng?");
                builder.setIcon(R.drawable.ic_cancel_black_24dp);
                // Cài đặt button Cancel- Hiển thị Toast
                builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                // Cài đặt button Yes Dismiss ẩn Dialog
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // gọi hàm xóa sản phẩm khỏi giỏ hàng
                        GioHangActivity.xoagiohang(fe.getId(), position); // gọi hàm xóa item bên Giohang activity (xóa trong sqlite)
                        Toast.makeText(context, "Xóa thành công ", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textview_soluong, textview_tensanpham,  textview_price, textview_thanhtien;
        ImageView imageview_hinhanh, imageview_delete;
        Button button_minus_tru, button_values_giatri, button_plus_cong;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textview_tensanpham = itemView.findViewById(R.id.textview_tensanpham);
            textview_soluong = itemView.findViewById(R.id.textview_soluong);
            textview_price = itemView.findViewById(R.id.textview_price);
            textview_thanhtien = itemView.findViewById(R.id.textview_thanhtien);
            imageview_hinhanh = itemView.findViewById(R.id.imageview_hinhanh);
            imageview_delete = itemView.findViewById(R.id.imageview_delete);
            button_minus_tru = itemView.findViewById(R.id.button_minus_tru);
            button_values_giatri = itemView.findViewById(R.id.button_values_giatri);
            button_plus_cong = itemView.findViewById(R.id.button_plus_cong);
        }
    }

    public String format(double number) {
        NumberFormat formatter = new DecimalFormat("#,###,###");
        return formatter.format(number);
    }
}
