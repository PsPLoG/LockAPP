package com.example.kmh.lockapp.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.example.kmh.lockapp.R
import com.example.kmh.lockapp.ScreenService
import com.example.kmh.lockapp.activity.AccountSettingActivity
import com.example.kmh.lockapp.activity.LimitSettingActivity
import com.example.kmh.lockapp.activity.LockScreenSettingActivity
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlinx.android.synthetic.main.fragment_setting.view.*

class SettingFragment : Fragment()
{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.activity_app_settings_main,container,false)


         var btn_setting_moveto_account: ImageView
         var btn_movetio_setting_limit_Setting: ImageView
         var btn_setting_moveto_setting_lockscreen: ImageView
         var btn_setting_moveto_notice: ImageView
         var btn_setting_moveto_help: ImageView
         var btn_setting_moveto_service: ImageView
        btn_setting_moveto_account = view.findViewById<View>(R.id.btn_setting_moveto_account) as ImageView
        btn_movetio_setting_limit_Setting = view.findViewById<View>(R.id.btn_movetio_setting_limit_Setting) as ImageView
        btn_setting_moveto_setting_lockscreen = view.findViewById<View>(R.id.btn_setting_moveto_setting_lockscreen) as ImageView
        btn_setting_moveto_notice = view.findViewById<View>(R.id.btn_setting_moveto_notice) as ImageView
        btn_setting_moveto_help = view.findViewById<View>(R.id.btn_setting_moveto_help) as ImageView
        btn_setting_moveto_service = view.findViewById<View>(R.id.btn_setting_moveto_service) as ImageView

        btn_movetio_setting_limit_Setting.setOnClickListener {
            val intent = Intent(context, LimitSettingActivity::class.java)
            startActivity(intent)
        }
        btn_setting_moveto_account.setOnClickListener {
            val intent = Intent(context, AccountSettingActivity::class.java)
            startActivity(intent)
        }
        btn_setting_moveto_setting_lockscreen.setOnClickListener {
            val intent = Intent(context, LockScreenSettingActivity::class.java)
            startActivity(intent)
        }
        return view;
    }

}