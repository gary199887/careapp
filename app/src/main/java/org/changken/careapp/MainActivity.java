package org.changken.careapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView regTextView, forgetPwTextView;
    private Button loginButton;
    private EditText idNumberEditText, passwordEditText;

    /**
     * 初始化相關View元件
     */
    protected void initial() {
        regTextView = (TextView) findViewById(R.id.reg_text_view);
        forgetPwTextView = (TextView) findViewById(R.id.forget_pw_text_view);
        idNumberEditText = (EditText) findViewById(R.id.idNumber_edit_text);
        passwordEditText = (EditText) findViewById(R.id.password_edit_text);
        loginButton = (Button) findViewById(R.id.login_button);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化元件
        initial();

        //登入按鈕的邏輯
        loginButton.setOnClickListener((View v) -> {

            Intent intent = new Intent(MainActivity.this, MemberCenterActivity.class);
            startActivity(intent);
        });

        //註冊文字的邏輯
        regTextView.setOnClickListener((View v) -> {
            Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
            startActivity(intent);
        });

        //忘記密碼文字的邏輯
        forgetPwTextView.setOnClickListener((View v) -> {
            Intent intent = new Intent(MainActivity.this, ForgetPasswordActivity.class);
            startActivity(intent);
        });
    }
}
