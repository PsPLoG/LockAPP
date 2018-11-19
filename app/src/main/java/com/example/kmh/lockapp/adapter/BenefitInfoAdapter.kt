package com.example.kmh.lockapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.kmh.lockapp.R

class BenefitInfoAdapter(var context: Context, var list : ArrayList<BenefitInfoItem>) : RecyclerView.Adapter<BenefitInfoAdapter.Holder>()
{
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        var view: View
        view = LayoutInflater.from(context).inflate(R.layout.recycler_item_benefit_info, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: Holder, position: Int) {
        p0.icon
        p0.content.text=list[position].content
        p0.discount.text=list[position].discount
        p0.title.text=list[position].title
    }

    class Holder(var view: View) : RecyclerView.ViewHolder(view)
    {
        var icon= view.findViewById<ImageView>(R.id.iv_mycard_benefit_info_icon)
        var title= view.findViewById<TextView>(R.id.tv_mycard_benefit_info_title)
        var discount= view.findViewById<TextView>(R.id.tv_mycard_benefit_info_discount)
        var content= view.findViewById<TextView>(R.id.tv_mycard_benefit_info_content)
    }
}


