package com.example.kmh.lockapp.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kmh.lockapp.KbMainActivity;
import com.example.kmh.lockapp.R;
import com.example.kmh.lockapp.http.HttpClient;

import org.json.JSONException;

public class App_LoginActivity extends AppCompatActivity {
TextView btn_applogin_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app__login);
        final EditText id = findViewById(R.id.inputId);
        final EditText pw = findViewById(R.id.inputPwd);
        findViewById(R.id.loginBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                class a extends AsyncTask<String,Integer,String>
                {
                    String ret="fail";

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        if(ret.equals("true"))
                        {
                            Intent intent=new Intent(App_LoginActivity.this,KbMainActivity.class);
                            startActivity(intent);
                        }else
                        {
                            Toast.makeText(getApplicationContext(),"id, pw를 확인해 주세요.",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    protected String doInBackground(String... strings) {
                        try {
                            ret = HttpClient.signup(id.getText().toString(),pw.getText().toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }
                new a().execute();

            }
        });
        btn_applogin_signup = (TextView)findViewById(R.id.btn_applogin_signup);
        btn_applogin_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(App_LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
