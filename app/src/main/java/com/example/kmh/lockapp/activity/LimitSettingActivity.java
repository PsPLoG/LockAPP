package com.example.kmh.lockapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.kmh.lockapp.R;
import com.example.kmh.lockapp.adapter.CardDataAdapter;
import com.example.kmh.lockapp.data.CardDataItem;
import com.example.kmh.lockapp.http.HttpClient;

import java.util.ArrayList;

public class LimitSettingActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limit_setting);
        mRecyclerView = findViewById(R.id.recycler_limit_setting);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        ArrayList<CardDataItem> cardDataArrayList= new ArrayList<>();
        cardDataArrayList=HttpClient.mlist;
        CardDataAdapter mycardDataAdapter = new CardDataAdapter(cardDataArrayList,this);
        mRecyclerView.setAdapter(mycardDataAdapter);
    }
}


