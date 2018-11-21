package com.example.kmh.lockapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kmh.lockapp.activity.BuyPopActivity;
import com.example.kmh.lockapp.R;

import java.util.ArrayList;

public class MyPointProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView rv_tv_rv_tv_price;
        ImageView ivPicture;
        LinearLayout rv_produc;
        MyViewHolder(View view){
            super(view);

            rv_tv_rv_tv_price= view.findViewById(R.id.rv_tv_price);
            ivPicture = view.findViewById(R.id.rv_iv_product);
            rv_produc = view.findViewById(R.id.rv_product);
        }
    }

    private ArrayList<PointStoreProductData> pointStoreProductDataArrayList;
    private Context context;
    public MyPointProductAdapter(ArrayList<PointStoreProductData> pointStoreProductDataArrayList, Context context){
        this.pointStoreProductDataArrayList = pointStoreProductDataArrayList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_point_product_row, parent, false);

        return new MyPointProductAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.rv_produc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,BuyPopActivity.class);
                context.startActivity(intent);
            }
        });
        myViewHolder.rv_tv_rv_tv_price.setText(pointStoreProductDataArrayList.get(position).price);
      Glide.with(context).load(pointStoreProductDataArrayList.get(position).product_Image).into(myViewHolder.ivPicture);
    }

    @Override
    public int getItemCount() {
        return pointStoreProductDataArrayList.size();
    }
}

