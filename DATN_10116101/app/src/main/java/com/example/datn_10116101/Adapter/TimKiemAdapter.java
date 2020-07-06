package com.example.datn_10116101.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.datn_10116101.model.products;
import com.example.datn_10116101.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class TimKiemAdapter  extends BaseAdapter {
    ArrayList<products> listproduct;
    Context context;

    public TimKiemAdapter(ArrayList<products> listproduct, Context context) {
        this.listproduct = listproduct;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listproduct.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class viewholder{  // tạo 1 class viewholder đầy đủ các thuộc tính của file dongkhach.xml
        TextView txtdescription;
        TextView txttitle;
        TextView textview_giachinh;
        ImageView imgageview_hinhanhsanpham;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        viewholder holder;  // tạo 1 biến từ class viewholder

        if(itemView == null){
            holder=new viewholder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView=inflater.inflate(R.layout.item_product,null);
            holder.txttitle = itemView.findViewById(R.id.textview_title);
            holder.txtdescription = itemView.findViewById(R.id.textview_description);
            holder.textview_giachinh = itemView.findViewById(R.id.textview_giachinh);
            holder.imgageview_hinhanhsanpham = itemView.findViewById(R.id.imgageview_hinhanhsanpham);
            itemView.setTag(holder);
        }else{
            holder = (viewholder) itemView.getTag();
        }

        products currentpro=listproduct.get(position);
        holder.txttitle.setText(currentpro.getName());
        holder.txtdescription.setText(currentpro.getDescribe());
        holder.textview_giachinh.setText(format(currentpro.getPrice()));
        Picasso.get()
                .load(""+currentpro.getImage())
                //.resize(150, 150)
                // .centerCrop()
                .into(holder.imgageview_hinhanhsanpham);
        return itemView;
    }
    public String format(double number){
        NumberFormat formatter = new DecimalFormat("#,###,###");
        return formatter.format(number);
    }
}
