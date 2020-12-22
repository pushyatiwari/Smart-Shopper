package com.example.madproject4.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.madproject4.Model.CommonFood;
import com.example.madproject4.Model.Food;
import com.example.madproject4.R;

import java.util.List;

public class RecyclerView_forcommonfoods extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext ;
    private List<CommonFood> mData ;
    RequestOptions option;
    private RecyclerViewAdapter.onFoodListener monfoodlistener;

    public RecyclerView_forcommonfoods(Context mContext, List<CommonFood> mData, RecyclerViewAdapter.onFoodListener monfoodlistener) {
        this.mContext = mContext;
        this.mData = mData;
        this.monfoodlistener = monfoodlistener;
        // Request option for Glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.common_full_open_on_phone).error(R.drawable.common_google_signin_btn_icon_dark);

    }

    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.food_row_item,parent,false) ;
        final RecyclerViewAdapter.MyViewHolder viewHolder = new RecyclerViewAdapter.MyViewHolder(view , monfoodlistener) ;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_brand.setText("_");

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

        onFoodListener_cf on_FoodListener;
        public MyViewHolder(View itemView ,onFoodListener_cf on_FoodListener) {
            super(itemView);

            view_container = itemView.findViewById(R.id.container);
            tv_name = itemView.findViewById(R.id.food_name);
            tv_brand = itemView.findViewById(R.id.brand_name);
            img_thumbnail = itemView.findViewById(R.id.thumbnail);
            this.on_FoodListener = on_FoodListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            on_FoodListener.onFoodClick_2(getAdapterPosition());

        }
    }
    public interface onFoodListener_cf{
        void onFoodClick_2(int position);
    }
}
