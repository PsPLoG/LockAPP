package com.example.kmh.lockapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kmh.lockapp.data.DataControl;
import com.example.kmh.lockapp.http.HttpClient;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LockScreen extends AppCompatActivity {
    Button btn_LockScreen;
    TextView tv_lock_screen_time;
    TextView tv_lock_screen_date;
    TextView tv_lock_screen_day;
    long now;
    Date date;
    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);
        tv_lock_screen_time = (TextView) findViewById(R.id.tv_lock_screen_time);
        tv_lock_screen_date = (TextView) findViewById(R.id.tv_lock_screen_date);
        tv_lock_screen_day = (TextView) findViewById(R.id.tv_lock_screen_day);
        final LinearLayout ll_background= (LinearLayout)findViewById(R.id.ll_lock_background);
        LinearLayout ll_limit_pay= (LinearLayout)findViewById(R.id.ll_limit_pay);

        SharedPreferences pref1 = getSharedPreferences("image", MODE_PRIVATE);
        String image =  pref1.getString("imagestrings", "");
        if(image.equals("")==false) {
            Bitmap bitmap = StringToBitMap(image);
            Drawable drawable = new BitmapDrawable(getResources(), bitmap);
            ll_background.setBackground(drawable);
        }



        View widget;
        int tmp =  getSharedPreferences("pref", Context.MODE_PRIVATE).getInt("lockshape", 0);
        if(tmp == 0)
        {
            widget = getLayoutInflater().inflate(R.layout.widget_lock_limit_progressbar,null,false);
            ProgressBar pb1 = widget.findViewById(R.id.pb_lock_limit1);
            ProgressBar pb2 = widget.findViewById(R.id.pb_lock_limit2);
            ProgressBar pb3 = widget.findViewById(R.id.pb_lock_limit3);
            pb1.setVisibility(View.GONE);
            pb2.setVisibility(View.GONE);
            pb3.setVisibility(View.GONE);

            if(DataControl.getCard_1(this)) {
                pb1.setVisibility(View.VISIBLE);
                int limit=DataControl.getLimit_1(this);
                if(limit>=80)
                    pb1.setProgressDrawable(getDrawable(R.drawable.progressbar_selectcard_pay_red));
                else if(limit>=40)
                    pb1.setProgressDrawable(getDrawable(R.drawable.progressbar_selectcard_pay_yello));
                else
                    pb1.setProgressDrawable(getDrawable(R.drawable.progressbar_selectcard_pay));
                pb1.setProgress(limit);
            }
            if(DataControl.getCard_2(this)) {
                pb2.setVisibility(View.VISIBLE);
                int limit=DataControl.getLimit_2(this);
                if(limit>=80)
                    pb2.setProgressDrawable(getDrawable(R.drawable.progressbar_selectcard_pay_red));
                else if(limit>=40)
                    pb2.setProgressDrawable(getDrawable(R.drawable.progressbar_selectcard_pay_yello));
                else
                    pb2.setProgressDrawable(getDrawable(R.drawable.progressbar_selectcard_pay));
                pb2.setProgress(limit);
            }
            if(DataControl.getCard_3(this)) {
                pb3.setVisibility(View.VISIBLE);
                int limit=DataControl.getLimit_3(this);
                if(limit>=80)
                    pb3.setProgressDrawable(getDrawable(R.drawable.progressbar_selectcard_pay_red));
                else if(limit>=40)
                    pb3.setProgressDrawable(getDrawable(R.drawable.progressbar_selectcard_pay_yello));
                else
                    pb3.setProgressDrawable(getDrawable(R.drawable.progressbar_selectcard_pay));
                pb3.setProgress(limit);
            }
        }else if(tmp == 1)
        {
            widget = getLayoutInflater().inflate(R.layout.widget_lock_limit_card,null,false);
            ProgressBar pb1 = widget.findViewById(R.id.pb_lock_limit1);
            ProgressBar pb2 = widget.findViewById(R.id.pb_lock_limit2);
            ProgressBar pb3 = widget.findViewById(R.id.pb_lock_limit3);
            widget.findViewById(R.id.fl_lock_limit1).setVisibility(View.GONE);
            widget.findViewById(R.id.fl_lock_limit2).setVisibility(View.GONE);
            widget.findViewById(R.id.fl_lock_limit3).setVisibility(View.GONE);

            if(DataControl.getCard_1(this)) {
                widget.findViewById(R.id.fl_lock_limit1).setVisibility(View.VISIBLE);
                int limit=DataControl.getLimit_1(this);
                if(limit>=80)
                    pb1.setProgressDrawable(getDrawable(R.drawable.vertical_bar_red));
                else if(limit>=40)
                    pb1.setProgressDrawable(getDrawable(R.drawable.vertical_bar_yellow));
                else
                    pb1.setProgressDrawable(getDrawable(R.drawable.vertical_bar_green));
                pb1.setProgress(limit);
            }
            if(DataControl.getCard_2(this)) {
                widget.findViewById(R.id.fl_lock_limit2).setVisibility(View.VISIBLE);
                int limit=DataControl.getLimit_2(this);
                if(limit>=80)
                    pb2.setProgressDrawable(getDrawable(R.drawable.vertical_bar_red));
                else if(limit>=40)
                    pb2.setProgressDrawable(getDrawable(R.drawable.vertical_bar_yellow));
                else
                    pb2.setProgressDrawable(getDrawable(R.drawable.vertical_bar_green));
                pb2.setProgress(limit);
            }
            if(DataControl.getCard_3(this)) {
                widget.findViewById(R.id.fl_lock_limit3).setVisibility(View.VISIBLE);
                int limit=DataControl.getLimit_3(this);
                if(limit>=80)
                    pb3.setProgressDrawable(getDrawable(R.drawable.vertical_bar_red));
                else if(limit>=40)
                    pb3.setProgressDrawable(getDrawable(R.drawable.vertical_bar_yellow));
                else
                    pb3.setProgressDrawable(getDrawable(R.drawable.vertical_bar_green));
                pb3.setProgress(limit);
            }
        }else{
            widget = getLayoutInflater().inflate(R.layout.widget_lock_limit_circle,null,false);
            ProgressBar pb1 = widget.findViewById(R.id.pb_lock_limit1);
            ProgressBar pb2 = widget.findViewById(R.id.pb_lock_limit2);
            ProgressBar pb3 = widget.findViewById(R.id.pb_lock_limit3);
            widget.findViewById(R.id.fl_lock_limit1).setVisibility(View.GONE);
            widget.findViewById(R.id.fl_lock_limit2).setVisibility(View.GONE);
            widget.findViewById(R.id.fl_lock_limit3).setVisibility(View.GONE);

            if(DataControl.getCard_1(this)) {
                widget.findViewById(R.id.fl_lock_limit1).setVisibility(View.VISIBLE);
                int limit=DataControl.getLimit_1(this);
                if(limit>=80)
                    pb1.setProgressDrawable(getDrawable(R.drawable.vertical_circle_bar_red));
                else if(limit>=40)
                    pb1.setProgressDrawable(getDrawable(R.drawable.vertical_circle_bar_yellow));
                else
                    pb1.setProgressDrawable(getDrawable(R.drawable.vertical_circle_bar_green));
                pb1.setProgress(limit);
            }
            if(DataControl.getCard_2(this)) {
                widget.findViewById(R.id.fl_lock_limit2).setVisibility(View.VISIBLE);
                int limit=DataControl.getLimit_2(this);
                if(limit>=80)
                    pb2.setProgressDrawable(getDrawable(R.drawable.vertical_circle_bar_red));
                else if(limit>=40)
                    pb2.setProgressDrawable(getDrawable(R.drawable.vertical_circle_bar_yellow));
                else
                    pb2.setProgressDrawable(getDrawable(R.drawable.vertical_circle_bar_green));
                pb2.setProgress(limit);
            }
            if(DataControl.getCard_3(this)) {
                widget.findViewById(R.id.fl_lock_limit3).setVisibility(View.VISIBLE);
                int limit=DataControl.getLimit_3(this);
                if(limit>=80)
                    pb3.setProgressDrawable(getDrawable(R.drawable.vertical_circle_bar_red));
                else if(limit>=40)
                    pb3.setProgressDrawable(getDrawable(R.drawable.vertical_circle_bar_yellow));
                else
                    pb3.setProgressDrawable(getDrawable(R.drawable.vertical_circle_bar_green));
                pb3.setProgress(limit);
            }
        }
        ll_limit_pay.addView(widget);




        ll_background.setOnTouchListener(new View.OnTouchListener() {
            float x,y;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(view.getId()==R.id.ll_lock_background)
                {
                    if(motionEvent.getAction()== MotionEvent.ACTION_DOWN)
                    {
                        x=motionEvent.getX();
                        y=motionEvent.getY();
                        return true;
                    }
                    if(motionEvent.getAction()== MotionEvent.ACTION_MOVE){
                        //c ani = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_mini);

                       // ll_background.startAnimation(ani);
                        return true;
                    }
                    if(motionEvent.getAction()== MotionEvent.ACTION_UP){
                        if((Math.abs(x-motionEvent.getX())+Math.abs(y-motionEvent.getY()))>700) {
                            finish();
                        }
                        return false;
                    }
                    Log.d("Lock","락배경눌림");
                }
                return false;
            }
        });
        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateYOURthing();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();

        new AsyncTask<String,Integer,String>(){
            String url;
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                ImageView iv = findViewById(R.id.iv_lock_screen_advertisement);
                if(url.equals("fail"))
                    return;
                Glide.with(getApplicationContext()).load(url).into(iv);
            }
            @Override
            protected String doInBackground(String... strings) {
                url = HttpClient.getAdData();
                Log.d("getCalli","asd");
                return null;
            }
        }.execute();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED //FLAG_SHOW_WHEN_LOCKED 는 안드로이드 기본 잠금화면 보다 위에 이 Activity를 띄워라라고 시키는 것이고

                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD); //FLAG_DISMISS_KEYGUARD 는 안드로이드 기본 잠금화면을 없애라라고 시키는 것인데.. FLAG_DISMISS_KEYGUARD 이녀석은 말을 잘 듣지 않는다. 이것에 대한 해결책은 다음시간에 하는 걸로 하고 ~

    }
    private void updateYOURthing() {
        now = System.currentTimeMillis();
        date = new Date(now);
        SimpleDateFormat sdftime = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sdfday = new SimpleDateFormat("E");
        SimpleDateFormat sdfdate = new SimpleDateFormat("MM월 dd일");
        String strDate = sdfdate.format(date);
        String strTime = sdftime.format(date);
        String strDay = sdfday.format(date);
        tv_lock_screen_time.setText(strTime);
        tv_lock_screen_date.setText(strDate);
        tv_lock_screen_day.setText(strDay+"day");
    }
}
