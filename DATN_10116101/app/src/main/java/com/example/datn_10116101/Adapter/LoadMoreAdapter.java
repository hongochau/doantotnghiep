package com.example.datn_10116101.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datn_10116101.model.products;
import com.example.datn_10116101.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class LoadMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    public List<products> mItemList;


    public LoadMoreAdapter(List<products> mItemList) {
        this.mItemList = mItemList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
            return new ItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof ItemViewHolder) {

            populateItemRows((ItemViewHolder) viewHolder, position);
        } else if (viewHolder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) viewHolder, position);
        }

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    /**
     * The following method decides the type of ViewHolder to display in the RecyclerView
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return mItemList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }


    private class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView txtdescription;
        TextView txttitle;
        TextView txtgiachinh;
        TextView txtgiagiam;
        ImageView imagesp;
        RatingBar rateing;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            txttitle = itemView.findViewById(R.id.textview_title);
            txtdescription = itemView.findViewById(R.id.textview_description);
            imagesp = itemView.findViewById(R.id.imgageview_hinhanhsanpham);
            txtgiachinh=itemView.findViewById(R.id.textview_giachinh);
        }
    }


    private class LoadingViewHolder extends RecyclerView.ViewHolder {


        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
        //ProgressBar would be displayed

    }

    private void populateItemRows(ItemViewHolder viewHolder, int position) {
        products currentpro = mItemList.get(position);
        viewHolder.txttitle.setText(currentpro.getName());
        viewHolder.txtdescription.setText(currentpro.getDescribe());
        viewHolder.txtgiachinh.setText(""+format(currentpro.getPrice()));
        Picasso.get()
                .load(""+currentpro.getImage())
                .resize(150, 150)
                 .centerCrop()
                .into(viewHolder.imagesp);

    }

    public String format(double number){
        NumberFormat formatter = new DecimalFormat("#,###,###");
        return formatter.format(number);
    }


}