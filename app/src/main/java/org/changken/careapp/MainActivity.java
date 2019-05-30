package org.changken.careapp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.changken.careapp.contract.presenters.IMainPresenter;
import org.changken.careapp.contract.views.MainView;
import org.changken.careapp.models.UserModel;
import org.changken.careapp.presenter.MainPresenter;
import org.changken.careapp.tools.Helper;
import org.changken.careapp.tools.ResourceServiceImp;

public class MainActivity extends AppCompatActivity implements MainView {

    private TextView regTextView, forgetPwTextView;
    private Button loginButton;
    private EditText idNumberEditText, passwordEditText;
    private IMainPresenter mMainPresenter;
    private AlertDialog progressDialog;

    /**
     * 初始化相關View元件
     */
    protected void initial() {
        regTextView = (TextView) findViewById(R.id.reg_text_view);
        forgetPwTextView = (TextView) findViewById(R.id.forget_pw_text_view);
        idNumberEditText = (EditText) findViewById(R.id.idNumber_edit_text);
        passwordEditText = (EditText) findViewById(R.id.password_edit_text);
        loginButton = (Button) findViewById(R.id.login_button);

        mMainPresenter = new MainPresenter(this, new UserModel(), new ResourceServiceImp(this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化元件
        initial();

        setListeners();

        mMainPresenter.onCreate();
    }

    private void setListeners() {
        //登入按鈕的邏輯
        loginButton.setOnClickListener((View v) -> {
            //取得輸入資料
            String idNumber = idNumberEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            //登入邏輯
            mMainPresenter.loginProcess(idNumber, password);
        });

        //註冊文字的邏輯
        regTextView.setOnClickListener((View v) -> {
            mMainPresenter.goToReg();
        });

        //忘記密碼文字的邏輯
        forgetPwTextView.setOnClickListener((View v) -> {
            mMainPresenter.goToForgetPassword();
        });
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void createProgressDialog() {
        progressDialog = Helper.createProgressDialog(this, "登入中...");
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public boolean isProgressDialogCreated() {
        return progressDialog != null;
    }

    @Override
    public void goToPage(Class<?> cls) {
        Intent intent = new Intent(MainActivity.this, cls);
        startActivity(intent);
    }

    @Override
    public void goToPageNotBack(Class<?> cls) {
        goToPage(cls);
        finish();
    }

    @Override
    public boolean checkIfLogin() {
        return Helper.isLogin(this);
    }

    @Override
    public void storeUserInfo(String user_id, String user_record_id) {
        Helper.loginProcess(this, user_id, user_record_id);
    }
}
