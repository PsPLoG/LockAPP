package com.example.kmh.lockapp.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.example.kmh.lockapp.R
import com.example.kmh.lockapp.data.DataControl
import kotlinx.android.synthetic.main.activity_card_using_shape.*

class CardUsingShapeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_using_shape)
        var i = DataControl.getLockShape(this)
        if(i==0)
            cb_shpae_bar.isChecked=true
        else if(i==1)
            cb_shpae_card.isChecked=true
        else
            cb_shpae_circle.isChecked=true

        cb_shpae_bar.setOnClickListener {
            setAllChecked()
            cb_shpae_bar.isChecked=true;
            DataControl.setLockShape(this,0)
        }
        cb_shpae_card.setOnClickListener {
            setAllChecked()
            cb_shpae_card.isChecked=true;
            DataControl.setLockShape(this,1)
        }
        cb_shpae_circle.setOnClickListener {
        setAllChecked()
            cb_shpae_circle.isChecked=true;
            DataControl.setLockShape(this,2)
        }
    }
    fun setAllChecked()
    {
        cb_shpae_bar.isChecked=false;
        cb_shpae_card.isChecked=false;
        cb_shpae_circle.isChecked=false;

    }
}
