package com.example.datn_10116101.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.datn_10116101.model.FeedbackUser;
import com.example.datn_10116101.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class QuanlysanphamdadanhgiaAdapter extends BaseAdapter {
    ArrayList<FeedbackUser> list;
    Context context;

    public QuanlysanphamdadanhgiaAdapter(ArrayList<FeedbackUser> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       convertView= inflater.inflate(R.layout.item_quanlysanphamdadanhgia,null);
        CircleImageView  cricleimageview_hinhanhsanpham =  convertView.findViewById(R.id.cricleimageview_hinhanhsanpham);
        TextView  textview_nguoidungphanhoi =  convertView.findViewById(R.id.textview_nguoidungphanhoi);
        TextView  textview_tensanphamdanhgia =  convertView.findViewById(R.id.textview_tensanphamdanhgia);
        TextView  textview_noidungdanhgia =  convertView.findViewById(R.id.textview_noidungdanhgia);
        TextView  textview_ngaydanhgia =  convertView.findViewById(R.id.textview_ngaydanhgia);
        RatingBar ratingBarproduct_votedanhgia =  convertView.findViewById(R.id.ratingBarproduct_votedanhgia);

        FeedbackUser currentpro=list.get(position);
        Picasso.get()
                .load(""+currentpro.getImage())
                .placeholder(R.mipmap.logo)
                .error(R.drawable.cast_album_art_placeholder_large)
                .into(cricleimageview_hinhanhsanpham);

        textview_nguoidungphanhoi.setText(currentpro.getName());
        textview_tensanphamdanhgia.setText(""+currentpro.getIdProduct());
        textview_noidungdanhgia.setText(currentpro.getContent());
        textview_ngaydanhgia.setText(""+currentpro.getFeedDate());
        ratingBarproduct_votedanhgia.setRating(currentpro.getRate());
        return convertView;
    }


}
