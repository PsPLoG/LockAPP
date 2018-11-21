package com.example.kmh.lockapp.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.kmh.lockapp.http.HttpClient;

public class DataControl {
    public static Boolean getLockSetting(Context context) {
        SharedPreferences pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        return pref.getBoolean("locksetting",false);
    }

    // 값 저장하기
    public static void setLockSetting(Context context, Boolean offset ) {
        SharedPreferences pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
         editor.putBoolean("locksetting", offset);
        editor.commit();
    }

    public static int getLockShape(Context context ) {
        SharedPreferences pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        return pref.getInt("lockshape", 0);
    }

    // 값 저장하기
    public static void setLockShape(Context context, int offset ) {
        SharedPreferences pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("lockshape", offset);
        editor.commit();
    }

    public static boolean getCard_1(Context context )  {
        SharedPreferences pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        return pref.getBoolean("lockcard1", false);
    }

    // 값 저장하기
    public static void setCard_1(Context context, boolean offset) {
        SharedPreferences pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("lockcard1",offset);
        editor.commit();
    }
    public static boolean getCard_2(Context context )  {
        SharedPreferences pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        return pref.getBoolean("lockcard2", false);
    }

    // 값 저장하기
    public static void setCard_2(Context context, boolean offset) {
        SharedPreferences pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("lockcard2",offset);
        editor.commit();
    }
    public static boolean getCard_3(Context context )  {
        SharedPreferences pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        return pref.getBoolean("lockcard3", false);
    }

    // 값 저장하기
    public static void setCard_3(Context context, boolean offset) {
        SharedPreferences pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("lockcard3",offset);
        editor.commit();
    }

    public static int getLimit_1(Context context )  {
        SharedPreferences pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        return pref.getInt("locklimit1", 500000);
    }
    public static int getLimit(Context context ,int id)  {
        SharedPreferences pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        return pref.getInt("locklimit"+id+1, 500000);
    }
    public static void setLimit(Context context, double offset,int id) {
        SharedPreferences pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("locklimit"+id,(int)((HttpClient.mlist.get(0).balance/offset)*100));
        editor.commit();
    }
    // 값 저장하기
    public static void setLimit_1(Context context, double offset) {
        SharedPreferences pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("locklimit1",(int)((HttpClient.mlist.get(0).balance/offset)*100));
        editor.commit();
    }
    public static int getLimit_2(Context context ) {
        SharedPreferences pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        return pref.getInt("locklimit2", 500000);
    }

    // 값 저장하기
    public static void setLimit_2(Context context, double offset) {
        SharedPreferences pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (HttpClient.mlist.size()>1)
        editor.putInt("locklimit2", (int)((HttpClient.mlist.get(1).balance/offset)*100));
        Log.d("locklimit",(int)((HttpClient.mlist.get(1).balance/offset)*100)+"");
        editor.commit();
    }
    public static int getLimit_3(Context context )  {
        SharedPreferences pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        return pref.getInt("locklimit3", 500000);
    }

    // 값 저장하기
    public static void setLimit_3(Context context, double offset) {
        SharedPreferences pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (HttpClient.mlist.size()>2)
        editor.putInt("locklimit3", (int)((HttpClient.mlist.get(2).balance/offset)*100));
        editor.commit();
    }
}
