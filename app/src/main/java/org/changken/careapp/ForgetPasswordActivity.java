package org.changken.careapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgetPasswordActivity extends AppCompatActivity {

    private EditText forgetPwEditText;
    private Button confirmEmailPhoneBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        initView();
        initListeners();
    }

    private void initView() {
        forgetPwEditText = (EditText) findViewById(R.id.forget_pw_edit_text);
        confirmEmailPhoneBtn = (Button) findViewById(R.id.confirm_email_phone_button);
    }

    private void initListeners() {
        confirmEmailPhoneBtn.setOnClickListener((v) -> {
            boolean isPhone = false;
            String contactWay = forgetPwEditText.getText().toString();

            if (contactWay.length() == 0) {
                showToastMessage("請輸入所有的欄位!");
            } else if (!contactWay.matches("^09[0-9]{8}$") && !contactWay.matches("(.*)+@(.*)+\\.(.*)+")) {
                showToastMessage("格式不正確!");
            } else {
                if (contactWay.matches("^09[0-9]{8}$")) {
                    isPhone = true;
                }

                Intent intent = new Intent(ForgetPasswordActivity.this, ForgetPassword2Activity.class);
                intent.putExtra("isPhone", isPhone);
                intent.putExtra("contactWay", contactWay);
                startActivity(intent);
                finish();
            }
        });
    }

    private void showToastMessage(String message) {
        Toast.makeText(ForgetPasswordActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
