package org.changken.careapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.changken.careapp.contract.IntentData;

public class ForgetPassword2Activity extends AppCompatActivity {

    private TextView checkContactHint;
    private TextView showContactWay;
    private Button confirmBtn;
    private Button denyBtn;
    private boolean isPhone;
    private String contactWay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password2);

        initView();
        initActivity();
        initListeners();
    }

    private void initView() {
        checkContactHint = (TextView) findViewById(R.id.check_contact_hint);
        showContactWay = (TextView) findViewById(R.id.show_contact_way);
        confirmBtn = (Button) findViewById(R.id.confirm_button);
        denyBtn = (Button) findViewById(R.id.deny_button);
    }

    private void initActivity() {
        Intent intent = getIntent();

        isPhone = intent.getBooleanExtra("isPhone", false);
        contactWay = intent.getStringExtra("contactWay");

        if (isPhone) {
            checkContactHint.setText("您的手機號碼是否為?");
        }

        showContactWay.setText(contactWay);
    }

    private void initListeners() {
        confirmBtn.setOnClickListener((v) -> {
            showConfirmAlertDialog();
        });

        denyBtn.setOnClickListener((v) -> {
            goToPage(ForgetPasswordActivity.class);
        });
    }

    void showConfirmAlertDialog() {
        new AlertDialog.Builder(ForgetPassword2Activity.this)
                .setTitle("請去您的信箱or簡訊查看驗證碼!")
                .setMessage("Go ahead!")
                .setPositiveButton("去吧", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();//關掉alert視窗
                        
                        goToPage(ForgetPassword3Activity.class, (intent) -> {
                            intent.putExtra("isPhone", isPhone);
                            intent.putExtra("contactWay", contactWay);
                        });
                    }
                })
                .show();
    }

    private void goToPage(Class<?> cls) {
        goToPage(cls, null);
    }

    private void goToPage(Class<?> cls, IntentData intentData) {
        Intent intent = new Intent(ForgetPassword2Activity.this, cls);
        if (intentData != null) {
            intentData.putData(intent);
        }
        startActivity(intent);
        finish();
    }
}
