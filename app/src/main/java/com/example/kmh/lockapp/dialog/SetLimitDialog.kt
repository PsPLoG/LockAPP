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
import android.widget.Button
import com.example.kmh.lockapp.R
import com.example.kmh.lockapp.data.DataControl
import kotlinx.android.synthetic.main.dialog_addcard.*
import kotlinx.android.synthetic.main.dialog_setlimit.*

class SetLimitDialog(var activity : Context,var offset : Double,var id:Int) : Dialog(activity) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_setlimit)

        bt_setlimit_ok.setOnClickListener {
            DataControl.setLimit(activity,et_setlimit.text.toString().toDouble(),id+1)
            dismiss()
        }
        bt_setlimit_no.setOnClickListener {
            dismiss()
        }
    }

}
