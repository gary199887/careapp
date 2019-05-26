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

import org.changken.careapp.datamodels.AirTableListResponse;
import org.changken.careapp.datamodels.AirTableResponse;
import org.changken.careapp.datamodels.User;
import org.changken.careapp.models.BaseModel;
import org.changken.careapp.models.ModelCallback;
import org.changken.careapp.models.UserModel;
import org.changken.careapp.tools.Helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView regTextView, forgetPwTextView;
    private Button loginButton;
    private EditText idNumberEditText, passwordEditText;
    private BaseModel<User> userModel;

    /**
     * 初始化相關View元件
     */
    protected void initial() {
        regTextView = (TextView) findViewById(R.id.reg_text_view);
        forgetPwTextView = (TextView) findViewById(R.id.forget_pw_text_view);
        idNumberEditText = (EditText) findViewById(R.id.idNumber_edit_text);
        passwordEditText = (EditText) findViewById(R.id.password_edit_text);
        loginButton = (Button) findViewById(R.id.login_button);
        userModel = new UserModel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化元件
        initial();

        //確認是否有登入
        if (Helper.isLogin(this)) {
            Toast.makeText(this, "哈哈您已登入唷!", Toast.LENGTH_SHORT).show();
            goToMemberCenter();
        }

        //登入按鈕的邏輯
        loginButton.setOnClickListener((View v) -> {

            //取得輸入資料
            String idNumber = idNumberEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (idNumber.length() > 0 && password.length() > 0) {
                //建立登入請求
                Map<String, String> query = new HashMap<>();
                query.put("view", "Grid%20view");
                query.put("filterByFormula", String.format("AND({user_id} = '%s', {user_password} = '%s')", idNumber, password));

                //設定警告視窗
                final AlertDialog progressDialog = Helper.createProgressDialog(this, "登入中...");

                userModel.list(query, new ModelCallback<AirTableListResponse<User>>() {
                    @Override
                    public void onProgress() {
                        //顯示它!
                        progressDialog.show();
                    }

                    @Override
                    public void onProcessEnd() {
                        //關掉alert視窗
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onResponseSuccess(Call<AirTableListResponse<User>> call, Response<AirTableListResponse<User>> response) {
                        //抓取資料
                        List<AirTableResponse<User>> list = response.body().getRecords();
                        //如果找到該筆資料
                        if (list.size() > 0) {
                            //執行登入
                            Helper.loginProcess(MainActivity.this, list.get(0).getFields().getIdNumber(), list.get(0).getId());
                            goToMemberCenter();
                        } else {
                            Toast.makeText(MainActivity.this, "登入失敗!身分證or密碼輸入錯誤!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onResponseFailure(Call<AirTableListResponse<User>> call, Response<AirTableListResponse<User>> response) {
                        Toast.makeText(MainActivity.this, "登入失敗!http回應有誤!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<AirTableListResponse<User>> call, Throwable t) {
                        //如果是網路沒通 or Json解析失敗!
                        Toast.makeText(MainActivity.this, "網路問題!", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                //如果任一個欄位沒有輸入的話!
                Toast.makeText(MainActivity.this, "請輸入所有欄位!", Toast.LENGTH_SHORT).show();
            }
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

    private void goToMemberCenter(){
        //跳頁
        Intent intent = new Intent(MainActivity.this, MemberCenterActivity.class);
        startActivity(intent);
        finish();
    }
}
