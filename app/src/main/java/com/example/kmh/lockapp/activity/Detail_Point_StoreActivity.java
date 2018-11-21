package com.example.kmh.lockapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.example.kmh.lockapp.R;
import com.example.kmh.lockapp.adapter.DetailPointStoreProductAdapter;
import com.example.kmh.lockapp.adapter.DetailPointStoreProductData;

import java.util.ArrayList;

public class Detail_Point_StoreActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    EditText et_detail_pointstore_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_point_store);
        et_detail_pointstore_search = findViewById(R.id.et_detail_pointstore_search);
        mRecyclerView = findViewById(R.id.recycler_detail_point_store);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<DetailPointStoreProductData> detailPointStoreProductDataArrayList = new ArrayList<>();

        detailPointStoreProductDataArrayList.add(new DetailPointStoreProductData( "180","2400 포인트","[CU_롯데] 초코빼빼로","34"));// URL 넣어야함
        detailPointStoreProductDataArrayList.add(new DetailPointStoreProductData( "180","2400 포인트","[CU_롯데] 초코빼빼로","34"));// URL 넣어야함
        detailPointStoreProductDataArrayList.add(new DetailPointStoreProductData( "180","2400 포인트","[CU_롯데] 초코빼빼로","34"));// URL 넣어야함
        detailPointStoreProductDataArrayList.add(new DetailPointStoreProductData( "180","2400 포인트","[CU_롯데] 초코빼빼로","34"));// URL 넣어야함
        detailPointStoreProductDataArrayList.add(new DetailPointStoreProductData( "180","2400 포인트","[CU_롯데] 초코빼빼로","34"));// URL 넣어야함
        detailPointStoreProductDataArrayList.add(new DetailPointStoreProductData( "180","2400 포인트","[CU_롯데] 초코빼빼로","34"));// URL 넣어야함
        detailPointStoreProductDataArrayList.add(new DetailPointStoreProductData( "180","2400 포인트","[CU_롯데] 초코빼빼로","34"));// URL 넣어야함
        detailPointStoreProductDataArrayList.add(new DetailPointStoreProductData( "180","2400 포인트","[CU_롯데] 초코빼빼로","34"));// URL 넣어야함
        detailPointStoreProductDataArrayList.add(new DetailPointStoreProductData( "180","2400 포인트","[CU_롯데] 초코빼빼로","34"));// URL 넣어야함
        detailPointStoreProductDataArrayList.add(new DetailPointStoreProductData( "180","2400 포인트","[CU_롯데] 초코빼빼로","34"));// URL 넣어야함
        detailPointStoreProductDataArrayList.add(new DetailPointStoreProductData( "180","2400 포인트","[CU_롯데] 초코빼빼로","34"));// URL 넣어야함

        DetailPointStoreProductAdapter mydetailPointStoreProductAdapter = new DetailPointStoreProductAdapter(detailPointStoreProductDataArrayList,getApplicationContext());
        mRecyclerView.setAdapter(mydetailPointStoreProductAdapter);
    }
}
