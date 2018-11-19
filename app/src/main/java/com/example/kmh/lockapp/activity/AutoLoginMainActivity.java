package com.example.kmh.lockapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kmh.lockapp.R;

public class AutoLoginMainActivity extends Activity {
    EditText id, pwd;
    Button btn;
    String loginId, loginPwd;
    CheckBox cb_auto_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_login_main);
        id = (EditText)findViewById(R.id.inputId);
        cb_auto_login = (CheckBox) findViewById(R.id.cb_auto_login);
        pwd = (EditText)findViewById(R.id.inputPwd);
        btn = (Button)findViewById(R.id.loginBtn);
        final SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        //처음에는 SharedPreferences에 아무런 정보도 없으므로 값을 저장할 키들을 생성한다.
        // getString의 첫 번째 인자는 저장될 키, 두 번쨰 인자는 값입니다.
        // 첨엔 값이 없으므로 키값은 원하는 것으로 하시고 값을 null을 줍니다.
        loginId = auto.getString("inputId",null);
        loginPwd = auto.getString("inputPwd",null);

        if(loginId !=null && loginPwd != null) {
            if(loginId.equals("kim") && loginPwd.equals("1111")) {
                Toast.makeText(AutoLoginMainActivity.this, loginId +"님 자동로그인 입니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AutoLoginMainActivity.this, SubAutoLoginActivity.class);
                startActivity(intent);
                finish();
            }

        }
        //id와 pwd가 null이면 Mainactivity가 보여짐.
        else if(loginId == null && loginPwd == null){
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (id.getText().toString().equals("kim") && pwd.getText().toString().equals("1111")&& cb_auto_login.isChecked()) {
                        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
                        //아이디가 '김무현'이고 비밀번호가 '인천대'일 경우 SharedPreferences.Editor를 통해
                        //auto의 loginId와 loginPwd에 값을 저장해 줍니다.
                        SharedPreferences.Editor autoLogin = auto.edit();
                        autoLogin.putString("inputId", id.getText().toString());
                        autoLogin.putString("inputPwd", pwd.getText().toString());
                        //꼭 commit()을 해줘야 값이 저장됩니다 ㅎㅎ
                        autoLogin.commit();
                        Toast.makeText(AutoLoginMainActivity.this, id.getText().toString()+"님 환영합니다.(자동 로그인)", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AutoLoginMainActivity.this, SubAutoLoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else if (id.getText().toString().equals("kim") && pwd.getText().toString().equals("1111")) {
                        Toast.makeText(AutoLoginMainActivity.this, id.getText().toString()+"님 환영합니다.(그냥 로그인)", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AutoLoginMainActivity.this, SubAutoLoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });

        }
    }
}

