package com.example.kmh.lockapp.fragment

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kmh.lockapp.R
import com.example.kmh.lockapp.adapter.AccountHistoryAdapter
import com.example.kmh.lockapp.adapter.AccountHistoryItem
import com.example.kmh.lockapp.adapter.BenefitInfoAdapter
import com.example.kmh.lockapp.adapter.BenefitInfoItem
import com.example.kmh.lockapp.data.DataControl
import com.example.kmh.lockapp.http.HttpClient
import kotlinx.android.synthetic.main.fragment_select_card.*
import kotlinx.android.synthetic.main.fragment_select_card.view.*

class SelectCardFragment : Fragment()
{
    class PagerAdapter(manager: FragmentManager, context: Context) : FragmentStatePagerAdapter(manager) {
        var frags = java.util.ArrayList<Fragment>()
        var context: Context? = null

        init {
            this.context = context
            var i=0
            for (i in 0..(HttpClient.mlist.size-1)) {
                var t = CardFragment()
                var b =Bundle()
                b.putInt("id",i)
                t.arguments=b
                frags.add(t)
            }
        }


        override fun getItem(i: Int): Fragment {
            return frags[i]
        }

        override fun getCount(): Int {
            return frags.size
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_select_card, container, false)
        var list = ArrayList<AccountHistoryItem>()
        var currentPage=0



        class a : AsyncTask<String,Int,String>(){
            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                view.vp_mycard_image.adapter=PagerAdapter(fragmentManager!!, context!!)
                view.pb_selectcard_limit.progress= ((HttpClient.mlist.get(0).cardLimit/DataControl.getLimit(context,0))*100.0).toInt()
                view.findViewById<TextView>(R.id.tv_select_card_limit).text="한도 "+DataControl.getLimit(context,0)+"원"
                view.findViewById<TextView>(R.id.tv_select_card_pay).text="잔액 "+HttpClient.mlist.get(0).cardLimit+"원"

                view.vp_mycard_image.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                    override fun onPageScrollStateChanged(state: Int) {
                    }
                    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                    }
                    override fun onPageSelected(position: Int) {
                        currentPage=position
                        Log.d("viewp",HttpClient.mlist.get(position).cardLimit.toString()+" "+(HttpClient.mlist.get(position).cardLimit/DataControl.getLimit(context,position))*100)
                        view.pb_selectcard_limit.progress= ((HttpClient.mlist.get(position).cardLimit/DataControl.getLimit(context,position))*100.0).toInt()
                        view.findViewById<TextView>(R.id.tv_select_card_limit).text="한도 "+DataControl.getLimit(context,position)+"원"
                        view.findViewById<TextView>(R.id.tv_select_card_pay).text="잔액 "+HttpClient.mlist.get(position).cardLimit+"원"
                    }
                })
                view.findViewById<RecyclerView>(R.id.rv_select_card_list).adapter=AccountHistoryAdapter(context!!,list)
                view.findViewById<RecyclerView>(R.id.rv_select_card_list).layoutManager=LinearLayoutManager(context)!!


            }
            override fun doInBackground(vararg p0: String?): String {
                list = HttpClient.getCardHistory(1)
                return ""
            }
        }
        a().execute()

        var list1 = ArrayList<BenefitInfoItem>()
        list1.add(BenefitInfoItem("","[food] 음식업종","건당 2천원 청구할인","음식/주점"))
        list1.add(BenefitInfoItem("","[food] 배달App","건당 2천원 청구할인","배달의 민족, 배민찬, 마켓컬리"))
        list1.add(BenefitInfoItem("","[food] 커피 업종","건당 1천원 청구할인","커피전문점/제과점/아이스크림점"))
        list1.add(BenefitInfoItem("","알파원","5%할인","알파원 오토체인지 해시태그 대상 가맹점"))

        view.btn_select_card_benefit_info.setOnClickListener{
            btn_select_card_benefit_info.background=context!!.getDrawable(R.drawable.app_mycard_benefit_info_btn_click)
            btn_select_card_account_history.background=context!!.getDrawable(R.drawable.app_mycard_account_history_btn_unclick)
            view.findViewById<RecyclerView>(R.id.rv_select_card_list).adapter= BenefitInfoAdapter(context!!,list1)
        }
        view.btn_select_card_account_history.setOnClickListener{
            btn_select_card_benefit_info.background=context!!.getDrawable(R.drawable.app_mycard_account_history_btn_unclick)
            btn_select_card_account_history.background=context!!.getDrawable(R.drawable.app_mycard_benefit_info_btn_click)
            view.findViewById<RecyclerView>(R.id.rv_select_card_list).adapter=AccountHistoryAdapter(context!!,list)
        }
        return view
    }
}