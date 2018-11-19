package com.example.kmh.lockapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kmh.lockapp.R

class AccountHistoryAdapter(var context: Context, var list : ArrayList<AccountHistoryItem>) : RecyclerView.Adapter<AccountHistoryAdapter.Holder>()
{
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        var view: View
        view = LayoutInflater.from(context).inflate(R.layout.recycler_item_account_history, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: Holder, position: Int) {
        p0.date.text=list[position].date
        p0.content.text=list[position].content
        p0.pay.text=list[position].pay.toString()+"원"
        p0.balance.text=list[position].balance.toString()+"원"
    }

    class Holder(var view: View) : RecyclerView.ViewHolder(view)
    {
        var date= view.findViewById<TextView>(R.id.tv_account_history_date)
        var content= view.findViewById<TextView>(R.id.tv_account_history_content)
        var pay= view.findViewById<TextView>(R.id.tv_account_history_pay)
        var balance= view.findViewById<TextView>(R.id.tv_account_history_balance)
    }
}


