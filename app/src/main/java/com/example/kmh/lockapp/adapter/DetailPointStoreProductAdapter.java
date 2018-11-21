package com.example.kmh.lockapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kmh.lockapp.R;

import java.util.ArrayList;

public class DetailPointStoreProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_detail_pointstore_product;
        TextView rv_tv_detail_store_price;
        TextView tv_rv_detail_product_name;
        MyViewHolder(View view){
            super(view);
            iv_detail_pointstore_product = view.findViewById(R.id.iv_detail_pointstore_product);
            tv_rv_detail_product_name = view.findViewById(R.id.tv_rv_detail_product_name);
            rv_tv_detail_store_price = view.findViewById(R.id.rv_tv_detail_store_price);

        }
    }

    private ArrayList<DetailPointStoreProductData> DetailPointStoreProductDataArrayList;
    private Context context;
    public DetailPointStoreProductAdapter(ArrayList<DetailPointStoreProductData> DetailPointStoreProductDataArrayList, Context context){
        this.DetailPointStoreProductDataArrayList = DetailPointStoreProductDataArrayList;
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_detail_product_product, parent, false);

        return new DetailPointStoreProductAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        DetailPointStoreProductAdapter.MyViewHolder myViewHolder = (  DetailPointStoreProductAdapter.MyViewHolder) holder;
        myViewHolder.rv_tv_detail_store_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        myViewHolder.rv_tv_detail_store_price.setText(DetailPointStoreProductDataArrayList.get(position).Detail_price);
        myViewHolder.tv_rv_detail_product_name.setText(DetailPointStoreProductDataArrayList.get(position).Detail_name);
       Glide.with(context).load(DetailPointStoreProductDataArrayList.get(position).Detail_product_image).into(myViewHolder.iv_detail_pointstore_product);
    }

    @Override
    public int getItemCount() {
        return DetailPointStoreProductDataArrayList.size();
    }
}


