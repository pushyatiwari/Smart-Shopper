package com.example.madproject4.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.madproject4.Model.Food;
import com.example.madproject4.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext ;
    private List<Food> mData ;
    RequestOptions option;
    private onFoodListener monfoodlistener;

    public RecyclerViewAdapter(Context mContext, List<Food> mData, onFoodListener monfoodlistener) {
        this.mContext = mContext;
        this.mData = mData;
        this.monfoodlistener = monfoodlistener;
        // Request option for Glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.common_full_open_on_phone).error(R.drawable.common_google_signin_btn_icon_dark);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.food_row_item,parent,false) ;
        final MyViewHolder viewHolder = new MyViewHolder(view , monfoodlistener) ;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_brand.setText(mData.get(position).getBrand_name());

        // Load Image from the internet and set it into Imageview using Glide

        Glide.with(mContext).load(mData.get(position).getThumb()).apply(option).into(holder.img_thumbnail);



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_name ;
        TextView tv_brand ;
        ImageView img_thumbnail;
        LinearLayout view_container;

        onFoodListener onfoodlistener;
        public MyViewHolder(View itemView , onFoodListener onfoodlistener) {
            super(itemView);

            view_container = itemView.findViewById(R.id.container);
            tv_name = itemView.findViewById(R.id.food_name);
            tv_brand = itemView.findViewById(R.id.brand_name);
            img_thumbnail = itemView.findViewById(R.id.thumbnail);
            this.onfoodlistener = onfoodlistener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            onfoodlistener.onFoodClick(getAdapterPosition());

        }
    }
    public interface onFoodListener{
        void onFoodClick(int position);
    }
}
