package com.example.kmh.lockapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kmh.lockapp.R;
import com.example.kmh.lockapp.ScreenService;

public class MainActivity extends Activity {

    private Button onBtn, offBtn;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onBtn = (Button) findViewById(R.id.OnBtn);
        offBtn = (Button) findViewById(R.id.offBtn);
        onBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), ScreenService.class);
                startService(intent);
            }
        });
        offBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), ScreenService.class);
                stopService(intent);
            }
        });
    }
}