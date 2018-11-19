package com.example.kmh.lockapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.kmh.lockapp.R;

public class AppSettingsMainActivity extends AppCompatActivity {
    ImageView btn_setting_moveto_account;
    ImageView btn_movetio_setting_limit_Setting;
    ImageView btn_setting_moveto_setting_lockscreen;
    ImageView btn_setting_moveto_notice;
    ImageView btn_setting_moveto_help;
    ImageView btn_setting_moveto_service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_settings_main);

        btn_setting_moveto_account = (ImageView)findViewById(R.id.btn_setting_moveto_account);
        btn_movetio_setting_limit_Setting = (ImageView)findViewById(R.id.btn_movetio_setting_limit_Setting);
        btn_setting_moveto_setting_lockscreen = (ImageView)findViewById(R.id.btn_setting_moveto_setting_lockscreen);
        btn_setting_moveto_notice = (ImageView)findViewById(R.id.btn_setting_moveto_notice);
        btn_setting_moveto_help = (ImageView)findViewById(R.id.btn_setting_moveto_help);
        btn_setting_moveto_service = (ImageView)findViewById(R.id.btn_setting_moveto_service);

        btn_movetio_setting_limit_Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(AppSettingsMainActivity.this,LimitSettingActivity.class);
               startActivity(intent);
            }
        });
        btn_setting_moveto_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(AppSettingsMainActivity.this,AccountSettingActivity.class);
                startActivity(intent);
           }
        });
        btn_setting_moveto_setting_lockscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppSettingsMainActivity.this , LockScreenSettingActivity.class);
                startActivity(intent);
            }
        });

    }
}
