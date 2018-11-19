package com.example.kmh.lockapp.fragment

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.example.kmh.lockapp.R
import com.example.kmh.lockapp.circlelist.MatrixView
import com.example.kmh.lockapp.data.CardDataItem
import com.example.kmh.lockapp.dialog.CardAddDialog
import com.example.kmh.lockapp.http.HttpClient
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.menu_topmenubar.view.*
import kotlinx.android.synthetic.main.view_list_item.view.*
import java.util.ArrayList

class HomeFragment : Fragment() {

    private var listview: ListView? = null
    private val images = intArrayOf(R.drawable.p1, R.drawable.p2, R.drawable.p3)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_home,container,false)

        view.iv_home_add.setOnClickListener{
            val cdd = CardAddDialog(activity!!)
            cdd.getWindow().setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
            cdd.show()
        }

        class a : AsyncTask<String,Integer,String>(){
            var list=ArrayList<CardDataItem>()
            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                    Log.d("home",12.toString())
                    listview!!.adapter = MyAdapter(context!!,list)
                    listview!!.clipToPadding = false
                    listview!!.clipChildren = false
                    listview!!.setOnScrollListener(object : AbsListView.OnScrollListener {

                        override fun onScrollStateChanged(view: AbsListView, scrollState: Int) {

                        }

                        override fun onScroll(view: AbsListView, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
                            for (i in 0 until listview!!.childCount) {
                                listview!!.getChildAt(i).invalidate()
                            }
                            if(firstVisibleItem>7)
                                Toast.makeText(context,"posittion:"+firstVisibleItem,Toast.LENGTH_SHORT).show()
                        }
                    })
                    listview!!.setSelection(1)
                    return
            }

            override fun doInBackground(vararg p0: String?): String {
                listview = view.findViewById<View>(R.id.lv) as ListView
                list = HttpClient.getCardData()
                return ""
             }
        }


        a().execute()

        return view
    }


    internal inner class MyAdapter(var context: Context, var list: ArrayList<CardDataItem>) : BaseAdapter() {

        override fun getCount(): Int {
            return list.size+3;
        }

        override fun getItem(position: Int): Any? {
            return null
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var convertView = convertView

            if (convertView == null) {
                val m = LayoutInflater.from(context).inflate(R.layout.view_list_item, null) as MatrixView
                m.setParentHeight(listview!!.height)
                m.pid=position
                convertView = m
                if(position in 2..(list.size+1)) {
                    Glide.with(context).load(list.get(position-2).imageUrl).into(m.iv_home_card)
                }
            }
            return convertView
        }

    }

    @Deprecated("")
    @Throws(Exception::class)
    fun changeGroupFlag(obj: Any) {
        val f = obj.javaClass.superclass.superclass.superclass.declaredFields // 获得成员映射数组
        for (tem in f) {
            if (tem.name == "mGroupFlags") {
                tem.isAccessible = true
                val mGroupFlags = tem.get(obj) as Int
                val newGroupFlags = mGroupFlags and 0xfffff8
                tem.set(obj, newGroupFlags)
            }
        }
    }
}
