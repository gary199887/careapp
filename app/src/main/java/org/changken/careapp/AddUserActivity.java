package org.changken.careapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import org.changken.careapp.datamodels.AirTableListResponse;
import org.changken.careapp.datamodels.AirTableResponse;
import org.changken.careapp.datamodels.User;
import org.changken.careapp.models.ModelCallback;
import org.changken.careapp.models.UserModel;
import org.changken.careapp.tools.Helper;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class AddUserActivity extends AppCompatActivity {

    private EditText nameTextView, idNumberTextView, pwTextView,
            emailTextView, phoneTextView, addressTextView, birthdayEditText;
    private Button submitButton, datePickerButton;
    private UserModel userModel;
    //設定警告視窗
    private AlertDialog progressDialog;

    /**
     * 初始化相關View元件
     */
    protected void initial() {
        nameTextView = (EditText) findViewById(R.id.name_text_view);
        idNumberTextView = (EditText) findViewById(R.id.idNumber_text_view);
        pwTextView = (EditText) findViewById(R.id.pw_text_view);
        emailTextView = (EditText) findViewById(R.id.email_text_view);
        phoneTextView = (EditText) findViewById(R.id.phone_text_view);
        addressTextView = (EditText) findViewById(R.id.address_text_view);
        birthdayEditText = (EditText) findViewById(R.id.birthday_text_view);

        submitButton = (Button) findViewById(R.id.submit_button);
        datePickerButton = (Button) findViewById(R.id.data_picker_button);
        userModel = new UserModel();
        progressDialog = Helper.createProgressDialog(this, "註冊中...");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        //初始化相關元件
        initial();

        datePickerButton.setOnClickListener((View v) -> {
            DialogFragment dialogFragment = new DatePickerFragment();
            dialogFragment.show(getSupportFragmentManager(), "datePicker");
        });

        submitButton.setOnClickListener((View v) -> {
            //取得輸入值
            String name = nameTextView.getText().toString();
            String idNumber = idNumberTextView.getText().toString();
            String pw = pwTextView.getText().toString();
            String email = emailTextView.getText().toString();
            String phone = phoneTextView.getText().toString();
            String address = addressTextView.getText().toString();
            String birthday = birthdayEditText.getText().toString();

            if (name.length() > 0 && idNumber.length() > 0 && pw.length() > 0 &&
                    email.length() > 0 && phone.length() > 0 && address.length() > 0 &&
                    birthday.length() > 0) {
                //檢查帳號是否有重複
                checkIfUserIsExisted(name, idNumber, pw, email, phone, address, birthday);
            } else {
                showToastMessage("新增失敗!請填寫所有的欄位!");
            }
        });
    }

    private void addUser(String name, String idNumber, String pw, String email, String phone, String address, String birthday) {
        //建立並執行新增資料的請求
        userModel.add(new User(
                name,
                idNumber,
                pw,
                email,
                phone,
                address,
                birthday
        ), new ModelCallback<AirTableResponse<User>>() {
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
            public void onResponseSuccess(Call<AirTableResponse<User>> call, Response<AirTableResponse<User>> response) {
                showSuccessfulAddUserDialog();
            }

            @Override
            public void onResponseFailure(Call<AirTableResponse<User>> call, Response<AirTableResponse<User>> response) {
                showToastMessage("新增失敗!伺服器回應有些怪怪的~~");
            }

            @Override
            public void onFailure(Call<AirTableResponse<User>> call, Throwable t) {
                showToastMessage("新增失敗!可能是網路沒有通唷~");
            }
        });
    }

    private void checkIfUserIsExisted(String name, String idNumber, String pw, String email, String phone, String address, String birthday) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("view", "Grid%20view");
        queryMap.put("filterByFormula", "{user_id} = '" + idNumber + "'");

        userModel.list(queryMap, new ModelCallback<AirTableListResponse<User>>() {

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
                //如果筆數大於0，就表示已有該名使用者
                if (response.body().getRecords().size() > 0) {
                    showToastMessage("已有該使用者了!");
                    goToLoginPage();
                } else {
                    addUser(name, idNumber, pw, email, phone, address, birthday);
                }
            }

            @Override
            public void onResponseFailure(Call<AirTableListResponse<User>> call, Response<AirTableListResponse<User>> response) {
                showToastMessage("查詢失敗!伺服器回應有些怪怪的~~");
            }

            @Override
            public void onFailure(Call<AirTableListResponse<User>> call, Throwable t) {
                showToastMessage("查詢失敗!可能是網路沒有通唷~");
            }
        });
    }

    private void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showSuccessfulAddUserDialog() {
        new AlertDialog.Builder(this)
                .setTitle("註冊成功!")
                .setMessage("註冊成功!")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        goToLoginPage();
                    }
                }).show();
    }

    private void goToLoginPage() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    /**
     * 更新生日欄位
     *
     * @param year  int
     * @param month int
     * @param day   int
     */
    public void setBirthdayEditText(int year, int month, int day) {
        String date = year + "-" + month + "-" + day;
        birthdayEditText.setText(date);
    }

    /**
     * 得到生日欄位
     *
     * @return String
     */
    public String getBirthdayEditText() {
        return birthdayEditText.getText().toString();
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        private AddUserActivity mAddUserActivity;

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            //獲取AddUserActivity物件
            mAddUserActivity = (AddUserActivity) context;
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int year, month, day;

            String birthday = mAddUserActivity.getBirthdayEditText();
            String[] birthdayPart = birthday.split("-");

            if (birthday.length() == 0) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
            } else {
                year = Integer.parseInt(birthdayPart[0]);
                month = Integer.parseInt(birthdayPart[1]) - 1;
                day = Integer.parseInt(birthdayPart[2]);
            }

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mAddUserActivity.setBirthdayEditText(year, month + 1, dayOfMonth);
        }
    }
}
