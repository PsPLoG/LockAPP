package com.example.kmh.lockapp

import android.content.Context
import android.content.Intent
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout

import com.example.kmh.lockapp.R
import com.example.kmh.lockapp.data.DataControl
import com.example.kmh.lockapp.fragment.*
import kotlinx.android.synthetic.main.activity_kb_main.*
import java.util.ArrayList

class KbMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kb_main)
        val pager = findViewById<ViewPager>(R.id.vp_kb_main)
        pager.adapter = KbMainActivity.PagerAdapter(supportFragmentManager, this)
        pager.offscreenPageLimit=3
        tl_main_top.setupWithViewPager(pager)

        val bottomNaviLayout : View = this.layoutInflater.inflate(R.layout.menu_topmenubar, null, false)
        tl_main_top.getTabAt(0)!!.customView = bottomNaviLayout.findViewById(R.id.btn_top_home) as RelativeLayout
        tl_main_top.getTabAt(1)!!.customView = bottomNaviLayout.findViewById(R.id.btn_top_select_card) as RelativeLayout
        tl_main_top.getTabAt(2)!!.customView = bottomNaviLayout.findViewById(R.id.btn_top_point) as RelativeLayout
        tl_main_top.getTabAt(3)!!.customView = bottomNaviLayout.findViewById(R.id.btn_top_setting) as RelativeLayout
        if (DataControl.getLockSetting(this)) {
            val intent = Intent(this, ScreenService::class.java)
            startService(intent)
        }
    }

    class PagerAdapter(manager: FragmentManager, context: Context) : FragmentStatePagerAdapter(manager) {
        var frags = ArrayList<Fragment>()
        var context: Context? = null

        init {
            this.context = context
            frags.add(HomeFragment())
            frags.add(SelectCardFragment())
            frags.add(MyPointFragment())
            frags.add(SettingFragment())
        }


        override fun getItem(i: Int): Fragment {
            return frags[i]
        }

        override fun getCount(): Int {
            return frags.size
        }
    }
}
