package org.changken.careapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.changken.careapp.datamodels.AirTableListResponse;
import org.changken.careapp.datamodels.User;
import org.changken.careapp.models.UserModel;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView regTextView, forgetPwTextView;
    private Button loginButton;
    private EditText idNumberEditText, passwordEditText;
    private UserModel userModel;

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

        //登入按鈕的邏輯
        loginButton.setOnClickListener((View v) -> {

            //取得輸入資料
            String idNumber = idNumberEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if(idNumber.length() > 0 && password.length() > 0){
                //建立登入請求
                Map<String, String> query = new HashMap<>();
                query.put("view", "Grid%20view");
                query.put("filterByFormula", String.format("AND({user_id} = '%s', {user_password} = '%s')", idNumber, password));

                Call<AirTableListResponse<User>> listResponseCall = userModel.list(query);

                listResponseCall.enqueue(new Callback<AirTableListResponse<User>>() {
                    @Override
                    public void onResponse(Call<AirTableListResponse<User>> call, Response<AirTableListResponse<User>> response) {
                        //檢查http回應是否為200 ok!
                        if(response.isSuccessful()){
                            //如果找到該筆資料
                            if(response.body().getRecords().size() > 0){
                                Intent intent = new Intent(MainActivity.this, MemberCenterActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this, "登入失敗!身分證or密碼輸入錯誤!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this, "登入失敗!http回應有誤!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AirTableListResponse<User>> call, Throwable t) {
                        //如果是網路沒通 or Json解析失敗!
                        Toast.makeText(MainActivity.this, "網路問題!", Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
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
}
