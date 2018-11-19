package com.example.kmh.lockapp.adapter

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView
import android.graphics.drawable.ColorDrawable
import com.bumptech.glide.Glide
import com.example.kmh.lockapp.R
import com.example.kmh.lockapp.data.CardDataItem
import com.example.kmh.lockapp.data.DataControl
import com.example.kmh.lockapp.dialog.SetLimitDialog
import com.example.kmh.lockapp.http.HttpClient

import java.util.ArrayList

class CardDataAdapter(private val CardDataArraylist: ArrayList<CardDataItem>, private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class MyViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal var iv_card_image: ImageView
        internal var tv_limitnum: TextView
        internal var cb_checkbox_card: CheckBox
        internal var bt_setlimit: Button

        init {
            iv_card_image = view.findViewById(R.id.iv_card_image)
            tv_limitnum = view.findViewById(R.id.tv_limitnum)
            cb_checkbox_card = view.findViewById(R.id.cb_checkbox_card)
            bt_setlimit = view.findViewById(R.id.bt_setlimit)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_recyclerview, parent, false)

        return CardDataAdapter.MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val myViewHolder = holder as CardDataAdapter.MyViewHolder
        if (position == 0)
            myViewHolder.cb_checkbox_card.isChecked = DataControl.getCard_1(context)
        else if (position == 1)
            myViewHolder.cb_checkbox_card.isChecked = DataControl.getCard_2(context)
        else if (position == 2)
            myViewHolder.cb_checkbox_card.isChecked = DataControl.getCard_3(context)
        myViewHolder.cb_checkbox_card.setOnCheckedChangeListener { compoundButton, b ->
            if (position == 0)
                DataControl.setCard_1(context, b)
            else if (position == 1)
                DataControl.setCard_2(context, b)
            else if (position == 2)
                DataControl.setCard_3(context, b)
        }
        Glide.with(context).load(HttpClient.mlist[position].imageUrl).into(myViewHolder.iv_card_image)
        myViewHolder.bt_setlimit.setOnClickListener {
            val cdd = SetLimitDialog(context, 0.0, position)
            cdd.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            cdd.show()
        }
    }

    override fun getItemCount(): Int {
        return CardDataArraylist.size
    }
}


