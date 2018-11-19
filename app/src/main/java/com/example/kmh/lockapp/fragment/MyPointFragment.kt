package com.example.kmh.lockapp.fragment

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import com.example.kmh.lockapp.R
import com.example.kmh.lockapp.activity.Detail_Point_StoreActivity
import com.example.kmh.lockapp.adapter.Ad_For_Fill_PointData
import com.example.kmh.lockapp.adapter.MyAddPlusPointAdapter
import com.example.kmh.lockapp.adapter.MyPointProductAdapter
import com.example.kmh.lockapp.adapter.PointStoreProductData
import com.example.kmh.lockapp.http.HttpClient
import kotlinx.android.synthetic.main.activity_app_point.*
import java.util.ArrayList

class MyPointFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.activity_app_point, container, false)
        var mRecyclerView: RecyclerView
        var mLayoutManager: RecyclerView.LayoutManager
        var mRecyclerView2: RecyclerView
        var mLayoutManager2: RecyclerView.LayoutManager
        var btn_app_point_moresee: Button
        mRecyclerView = view.findViewById<RecyclerView>(R.id.recycler_point_store)
        mRecyclerView.setHasFixedSize(true)
        btn_app_point_moresee = view.findViewById<Button>(R.id.btn_app_point_moresee)
        mLayoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        mRecyclerView.layoutManager = mLayoutManager

        mLayoutManager2 = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        mRecyclerView2 = view.findViewById<RecyclerView>(R.id.recycler_view_filpoint)
        mRecyclerView2.layoutManager = mLayoutManager2

        btn_app_point_moresee.setOnClickListener {
            val intent = Intent(context, Detail_Point_StoreActivity::class.java)
            startActivity(intent)  // 더보기 클릭시 갈 화면
        }

        var pointStoreProductDataArrayList = ArrayList<PointStoreProductData>()
        var Ad_For_Fill_PointDataArrayList = ArrayList<Ad_For_Fill_PointData>()
        var point=0
        class a : AsyncTask<String, Int, String>(){
            override fun onPostExecute(result: String?) {
                val myPointProductAdapter = MyPointProductAdapter(pointStoreProductDataArrayList, context)
                val myAddPlusPointAdapter = MyAddPlusPointAdapter(Ad_For_Fill_PointDataArrayList, context)
                mRecyclerView.adapter = myPointProductAdapter
                mRecyclerView2.adapter = myAddPlusPointAdapter
                tv_mypoint_point.text=point.toString();
                super.onPostExecute(result)
            }
            override fun doInBackground(vararg p0: String?): String {
                pointStoreProductDataArrayList= HttpClient.getPointProduct()
                Ad_For_Fill_PointDataArrayList=HttpClient.getPointAd()
                point=HttpClient.getPoint()
                return ""
            }
        }
        a().execute()

        return view
    }
}