package com.example.kmh.lockapp.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.kmh.lockapp.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ThreeButtonActivity extends AppCompatActivity {
Button card_join;
Button open_camera;
Button open_picture;
ImageView mypicture;
final int REQ_CODE_SELECT_IMAGE=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_button);
        card_join = (Button)findViewById(R.id.card_join);
        open_camera=(Button)findViewById(R.id.open_camera);
        open_picture=(Button)findViewById(R.id.open_picture);
        mypicture= (ImageView)findViewById(R.id.mypicture);

        SharedPreferences pref1 = getSharedPreferences("image", MODE_PRIVATE);
        String image =  pref1.getString("imagestrings", "");
        Bitmap bitmap = StringToBitMap(image);
        mypicture.setImageBitmap(bitmap);

        card_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.kbcard.com/CXHIACRC0016.cms?cooperationcode=04247&issueStateType=&mobileExusYN=&solicitorcode=&rcmdnNo=&cardKind=&trid=&jehuId=#addhistory"));
          startActivity(intent);
            }
        });
        open_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);

            }
        });
        open_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callCamera();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if(requestCode == REQ_CODE_SELECT_IMAGE)
        {
            if(resultCode==Activity.RESULT_OK)
            {
                try {
                    //Uri에서 이미지 이름을 얻어온다.
                    //String name_Str = getImageNameToUri(data.getData());
                    //이미지 데이터를 비트맵으로 받아온다.
                    Bitmap image_bitmap 	= MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    ImageView image = (ImageView)findViewById(R.id.mypicture);

                    String image2 = BitMapToString(image_bitmap);
                    SharedPreferences pref = getSharedPreferences("image", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("imagestrings", image2);
                    editor.commit();
                    //배치해놓은 ImageView에 set
                    image.setImageBitmap(image_bitmap);
                    //Toast.makeText(getBaseContext(), "name_Str : "+name_Str , Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
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

    public String getImageNameToUri(Uri data)
    {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String imgPath = cursor.getString(column_index);
        String imgName = imgPath.substring(imgPath.lastIndexOf("/")+1);
        return imgName;
    }

    private void getPreferences(){
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        pref.getString("picture", "");
    }

    // 값 저장하기
    private void savePreferences(){
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("picture","picture");
        editor.commit();
    }

    // 값(Key Data) 삭제하기
    private void removePreferences(){
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove("picture");
        editor.commit();
    }
    public String BitMapToString(Bitmap bitmap){

        ByteArrayOutputStream baos=new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);

        byte [] b=baos.toByteArray();

        String temp=Base64.encodeToString(b, Base64.DEFAULT);

        return temp;

    }

    private void callCamera() {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            PackageManager pm = getPackageManager();
            final ResolveInfo mInfo = pm.resolveActivity(i, 0);
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(mInfo.activityInfo.packageName, mInfo.activityInfo.name));
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            startActivity(intent);
        } catch (Exception e){ Log.i("TAG", "Unable to launch camera: " + e); }    }
}