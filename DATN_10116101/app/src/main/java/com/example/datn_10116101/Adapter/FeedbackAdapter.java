package com.example.datn_10116101.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.datn_10116101.model.feedback_products;
import com.example.datn_10116101.R;
import com.example.datn_10116101.Service.RetrofitService;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.ViewHolder>{
    public List<feedback_products> mItemList;

    public FeedbackAdapter(List<feedback_products> mItemList) {
        this.mItemList = mItemList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_feedback, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        feedback_products fe=mItemList.get(position);
    holder.textview_nguoidungphanhoi.setText(""+fe.getName());
    holder.textview_noidung.setText(""+fe.getContent());
    holder.textview_ngay.setText(""+fe.getFeedDate());
        Picasso.get()
                .load(""+ RetrofitService.basePath+fe.getImagefb())
                //.resize(80, 80)
                // .centerCrop()
                .into(holder.imgprofile);
        holder.rateingbar.setRating(fe.getRate());
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textview_nguoidungphanhoi,textview_noidung,textview_ngay;
        CircleImageView imgprofile;
        RatingBar rateingbar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textview_nguoidungphanhoi = itemView.findViewById(R.id.textview_nguoidungphanhoi);
            textview_noidung = itemView.findViewById(R.id.textview_noidung);
            textview_ngay = itemView.findViewById(R.id.textview_ngay);
            imgprofile = itemView.findViewById(R.id.profile_image);
            rateingbar=itemView.findViewById(R.id.ratingBarproduct);
        }
    }

}
