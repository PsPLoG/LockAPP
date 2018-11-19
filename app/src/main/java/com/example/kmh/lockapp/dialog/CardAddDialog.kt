package com.example.kmh.lockapp.dialog

import android.app.Activity
import android.app.Dialog
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import android.view.View
import android.view.Window
import com.example.kmh.lockapp.R
import kotlinx.android.synthetic.main.dialog_addcard.*

class CardAddDialog(var activity : Activity) : Dialog(activity) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_addcard)
        btn_addcard_signup.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://m.kbcard.com/CXHIACRC0016.cms?cooperationcode=04247&issueStateType=&mobileExusYN=&solicitorcode=&rcmdnNo=&cardKind=&trid=&jehuId=#addhistory"))
            context.startActivity(intent)
        }
        btn_addcard_register.setOnClickListener{
            callCamera()
        }

    }

    private fun callCamera() {
        val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            val pm = context.getPackageManager()
            val mInfo = pm.resolveActivity(i, 0)
            val intent = Intent()
            intent.component = ComponentName(mInfo.activityInfo.packageName, mInfo.activityInfo.name)
            intent.action = Intent.ACTION_MAIN
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            context.startActivity(intent)
        } catch (e: Exception) {
            Log.i("TAG", "Unable to launch camera: $e")
        }

    }
}
