package com.example.kmh.lockapp.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.util.Base64

import com.example.kmh.lockapp.R
import com.example.kmh.lockapp.ScreenService
import com.example.kmh.lockapp.data.DataControl
import kotlinx.android.synthetic.main.activity_lock_screen_setting.*
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.IOException

class LockScreenSettingActivity : AppCompatActivity() {

    internal val REQ_CODE_SELECT_IMAGE = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock_screen_setting)
        btn_lockscreen_on.setOnClickListener {
            DataControl.setLockSetting(this, !DataControl.getLockSetting(this))
            if (DataControl.getLockSetting(this)) {
                btn_lockscreen_on.background = getDrawable(R.drawable.app_settings_lockscreen_off)
                val intent = Intent(this, ScreenService::class.java)
                startService(intent)
            } else {
                val intent = Intent(this, ScreenService::class.java)
                stopService(intent)
                btn_lockscreen_on.background = getDrawable(R.drawable.app_settings_lockscreen_on)
            }

        }

        if (DataControl.getLockSetting(this))
            btn_lockscreen_on.background = getDrawable(R.drawable.app_settings_lockscreen_off)
        else
            btn_lockscreen_on.background = getDrawable(R.drawable.app_settings_lockscreen_on)



        btn_lockscreen_background_setting.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = MediaStore.Images.Media.CONTENT_TYPE
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            startActivityForResult(intent, REQ_CODE_SELECT_IMAGE)
        }

        btn_lockscreen_shape.setOnClickListener {
            var intent = Intent(this,CardUsingShapeActivity::class.java)
            startActivity(intent)
        }




    }

    fun BitMapToString(bitmap: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {


        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    //Uri에서 이미지 이름을 얻어온다.
                    //String name_Str = getImageNameToUri(data.getData());
                    //이미지 데이터를 비트맵으로 받아온다.
                    val image_bitmap = MediaStore.Images.Media.getBitmap(contentResolver, data!!.data)

                    val image2 = BitMapToString(image_bitmap)
                    val pref = getSharedPreferences("image", Context.MODE_PRIVATE)
                    val editor = pref.edit()
                    editor.putString("imagestrings", image2)
                    editor.commit()
                    //배치해놓은 ImageView에 set
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }
}
