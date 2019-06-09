package org.changken.careapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ForgetPassword3Activity extends AppCompatActivity {

    private TextView inputCheckCodeHint;
    private TextView showContactWay;
    private EditText confirmCodeEditText;
    private TextView hintText;
    private TextView resendText;
    private Button confirmCodeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password3);

        initView();
        initData();
        initListeners();
    }

    private void initView() {
        inputCheckCodeHint = (TextView) findViewById(R.id.input_check_code_hint);
        showContactWay = (TextView) findViewById(R.id.show_contact_way);
        confirmCodeEditText = (EditText) findViewById(R.id.confirm_code_edit_text);
        hintText = (TextView) findViewById(R.id.hint_text);
        resendText = (TextView) findViewById(R.id.resend_text);
        confirmCodeBtn = (Button) findViewById(R.id.confirm_code_button);
    }

    private void initData() {
        Intent intent = getIntent();

        boolean isPhone = intent.getBooleanExtra("isPhone", false);
        String contactWay = intent.getStringExtra("contactWay");

        if (isPhone) {
            inputCheckCodeHint.setText("請輸入傳送到手機的驗證碼");
        }

        showContactWay.setText(contactWay);
    }

    private void initListeners() {
        resendText.setOnClickListener((v) -> {
            hintText.setText("Please wait 48 seconds before tying to resend.");
            resendText.setVisibility(TextView.GONE);
        });

        confirmCodeBtn.setOnClickListener((v) -> {
            if (confirmCodeEditText.length() > 0) {
                startActivity(new Intent(ForgetPassword3Activity.this, ForgetPassword4Activity.class));
                finish();
            }
        });
    }
}
