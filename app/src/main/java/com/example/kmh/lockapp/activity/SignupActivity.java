package com.example.kmh.lockapp.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kmh.lockapp.R;
import com.example.kmh.lockapp.http.HttpClient;

import org.json.JSONException;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean flag = false;
        setContentView(R.layout.activity_signup);
        final EditText name = findViewById(R.id.et_signup_name);
        final EditText id = findViewById(R.id.et_signup_id);
        final EditText pw = findViewById(R.id.et_signup_pw);
        final EditText phone = findViewById(R.id.et_signup_phone);
        final EditText email = findViewById(R.id.et_signup_email);
        final Button bt = findViewById(R.id.btn_signup_signupbutton);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncTask<String, Integer, String>() {
                    String msg;
                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        if(msg.equals("SUCCESS")) {
                            Toast.makeText(getApplicationContext(), "회원가입에 성공했습니다.", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                    @Override
                    protected String doInBackground(String... strings) {
                        try {
                            msg = HttpClient.signin(
                                    name.getText().toString(),
                                    id.getText().toString(),
                                    pw.getText().toString(),
                                    phone.getText().toString(),
                                    phone.getText().toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }.execute();
            }
        });

    }
}
