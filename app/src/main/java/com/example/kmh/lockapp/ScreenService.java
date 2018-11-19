package com.example.kmh.lockapp;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class ScreenService extends Service {

    private ScreenReceiver mReceiver = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mReceiver = new ScreenReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mReceiver, filter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Notification notification = new Notification(R.drawable.ic_launcher_foreground, "서비스 실행됨", System.currentTimeMillis());
        startForeground(1, new Notification());
        if (intent != null) {
            if (intent.getAction() == null) {
                if (mReceiver == null) {
                    mReceiver = new ScreenReceiver();
                    IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
                    registerReceiver(mReceiver, filter);
                }
            }
        }
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mReceiver.reenableKeyguard();
    }
}
//. 요렇게 하면 ScreenReceiver가 등록되서 화면이 꺼질 때 시스템이 보내주는
// ACTION_SCREEN_OFF 를 받아오게 되고 그럼 잠금화면이 짠 나타납니다.
// 아 !! 그럼 이 서비스는 어디서 실행시켜주느냐
// ~ 설정화면 등을 만들어서 켜기버튼 누르면 실행하고 끄기버튼 누르면 취소하고 하면됩니다