package com.example.kmh.lockapp.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.kmh.lockapp.R
import com.example.kmh.lockapp.http.HttpClient
import kotlinx.android.synthetic.main.fragment_card.view.*
import kotlinx.android.synthetic.main.view_list_item.view.*

class CardFragment() : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_card, container, false)
        var id  = arguments!!.getInt("id")

        Glide.with(this).load(HttpClient.mlist.get(id).imageUrl).into(view.iv_select_card_pager)
        return view
    }
}