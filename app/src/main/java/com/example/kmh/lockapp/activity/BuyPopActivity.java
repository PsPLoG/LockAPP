package com.example.kmh.lockapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.kmh.lockapp.R;

public class BuyPopActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_buy_pop);
        Intent intent = getIntent();
    }
    public void mOnClose(View v){
        Toast.makeText(getApplicationContext(),"구매",Toast.LENGTH_SHORT).show();
        finish();
    }
    public void mOnCancel(View v){
        Toast.makeText(getApplicationContext(),"취소",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
}

