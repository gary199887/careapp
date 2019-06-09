package org.changken.careapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class ForgetPassword4Activity extends AppCompatActivity {

    private EditText pwEditText, pwAgainEditText;
    private AppCompatCheckBox showPasswordCheckBox;
    private Button resetPwBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password4);

        initView();
        initListeners();
    }

    private void initView() {
        pwEditText = (EditText) findViewById(R.id.pw_edit_text);
        pwAgainEditText = (EditText) findViewById(R.id.pw_again_edit_text);
        showPasswordCheckBox = (AppCompatCheckBox) findViewById(R.id.show_password_checkbox);
        resetPwBtn = (Button) findViewById(R.id.reset_wp_button);
    }

    private void initListeners() {
        showPasswordCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    pwEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    pwAgainEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    pwEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    pwAgainEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        resetPwBtn.setOnClickListener((v) -> {
            String pwText = pwEditText.getText().toString();
            String pwAgainText = pwAgainEditText.getText().toString();

            if (pwText.length() == 0 || pwAgainText.length() == 0) {
                showToastMessage("請輸入所有欄位");
            } else if (!pwText.equals(pwAgainText)) {
                showToastMessage("密碼輸入不一致!");
            } else {
                showToastMessage("設定成功!");
                startActivity(new Intent(ForgetPassword4Activity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void showToastMessage(String msg) {
        Toast.makeText(ForgetPassword4Activity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
