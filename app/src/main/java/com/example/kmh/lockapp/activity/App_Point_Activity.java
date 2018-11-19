package com.example.kmh.lockapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.kmh.lockapp.R;
import com.example.kmh.lockapp.adapter.Ad_For_Fill_PointData;
import com.example.kmh.lockapp.adapter.MyAddPlusPointAdapter;
import com.example.kmh.lockapp.adapter.MyPointProductAdapter;
import com.example.kmh.lockapp.adapter.PointStoreProductData;

import java.util.ArrayList;

public class App_Point_Activity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView mRecyclerView2;
    RecyclerView.LayoutManager mLayoutManager2;
  Button btn_app_point_moresee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_point);

        mRecyclerView = findViewById(R.id.recycler_point_store);
        mRecyclerView.setHasFixedSize(true);
        btn_app_point_moresee = findViewById(R.id.btn_app_point_moresee);
        mLayoutManager = new LinearLayoutManager(this,LinearLayout.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mLayoutManager2 = new LinearLayoutManager(this,LinearLayout.HORIZONTAL,false);
        mRecyclerView2 = findViewById(R.id.recycler_view_filpoint);
        mRecyclerView2.setLayoutManager(mLayoutManager2);

        btn_app_point_moresee.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(App_Point_Activity.this,Detail_Point_StoreActivity.class);
                 startActivity(intent)  ;  // 더보기 클릭시 갈 화면
             }
         });

        ArrayList<PointStoreProductData> pointStoreProductDataArrayList = new ArrayList<>();

        pointStoreProductDataArrayList.add(new PointStoreProductData( "1800","14","1"));// URL 넣어야함

        MyPointProductAdapter myPointProductAdapter = new MyPointProductAdapter(pointStoreProductDataArrayList,getApplicationContext());


        ArrayList<Ad_For_Fill_PointData> Ad_For_Fill_PointDataArrayList = new ArrayList<>();

        Ad_For_Fill_PointDataArrayList.add(new Ad_For_Fill_PointData( "380","1","434"));// URL 넣어야함

        MyAddPlusPointAdapter myAddPlusPointAdapter = new MyAddPlusPointAdapter(Ad_For_Fill_PointDataArrayList,getApplicationContext());

        mRecyclerView.setAdapter(myPointProductAdapter);
        mRecyclerView2.setAdapter(myAddPlusPointAdapter) ;

    }
}
