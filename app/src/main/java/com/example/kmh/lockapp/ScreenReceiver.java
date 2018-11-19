package com.example.kmh.lockapp;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class ScreenReceiver extends BroadcastReceiver {
    private KeyguardManager km = null;
    private KeyguardManager.KeyguardLock keyLock = null;
    private TelephonyManager telephonyManager = null;
    private boolean isPhoneIdle = true;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            if (km == null)
                km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
            if (keyLock == null)
                keyLock = km.newKeyguardLock(Context.KEYGUARD_SERVICE);
            if(telephonyManager == null){

                telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);

                telephonyManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);
            }
            if(isPhoneIdle) {
                disableKeyguard();
                Intent i = new Intent(context, LockScreen.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        }
    }
  public  void  reenableKeyguard(){
        //keyLock.reenableKeyguard();
  }
  public  void disableKeyguard(){
        keyLock.disableKeyguard();
  }
    private PhoneStateListener phoneListener = new PhoneStateListener(){ // 전화 왔을때 잠금 안하게하기
        @Override
        public void onCallStateChanged(int state, String incomingNumber){
            switch(state){
                case TelephonyManager.CALL_STATE_IDLE :
                    isPhoneIdle = true;
                    break;
                case TelephonyManager.CALL_STATE_RINGING :
                    isPhoneIdle = false;
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK :
                    isPhoneIdle = false;
                    break;
            }
        }
    };

}
