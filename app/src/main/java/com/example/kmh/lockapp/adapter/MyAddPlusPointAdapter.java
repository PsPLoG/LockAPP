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
import com.example.kmh.lockapp.activity.AppPointPointPlusActivity;
import com.example.kmh.lockapp.R;

import java.util.ArrayList;

public class MyAddPlusPointAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView rv_tv_pluspointinfo;
        ImageView rv_iv_advertisement;
        LinearLayout rv_advertisement;
        MyViewHolder(View view){
            super(view);

            rv_tv_pluspointinfo= view.findViewById(R.id.rv_tv_pluspointinfo);
            rv_iv_advertisement = view.findViewById(R.id.rv_iv_advertisement);
            rv_advertisement =view.findViewById(R.id.rv_advertisement);

        }
    }

    private ArrayList<Ad_For_Fill_PointData> ad_for_fill_pointDataArrayList;
    private Context context;
    public MyAddPlusPointAdapter(ArrayList<Ad_For_Fill_PointData> ad_for_fill_pointDataArrayList, Context context){
        this.ad_for_fill_pointDataArrayList = ad_for_fill_pointDataArrayList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_point_advertisement_fillpoint, parent, false);

        return new MyAddPlusPointAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

       final MyViewHolder myViewHolder = (MyViewHolder) holder;

        myViewHolder.rv_tv_pluspointinfo.setText(ad_for_fill_pointDataArrayList.get(position).point);
        myViewHolder.rv_advertisement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,AppPointPointPlusActivity.class);
                 context.startActivity(intent);
            }
        });
        Glide.with(context).load(ad_for_fill_pointDataArrayList.get(position).advertisement_image).into(myViewHolder.rv_iv_advertisement);

    }

    @Override
    public int getItemCount() {
        return ad_for_fill_pointDataArrayList.size();
    }
}

