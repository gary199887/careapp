package org.changken.careapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
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

import org.changken.careapp.datamodels.AirTableResponse;
import org.changken.careapp.datamodels.User;
import org.changken.careapp.models.ModelCallback;
import org.changken.careapp.models.UserModel;
import org.changken.careapp.tools.Helper;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Response;

public class AddUserActivity extends AppCompatActivity {

    private EditText nameTextView, idNumberTextView, pwTextView,
            emailTextView, phoneTextView, addressTextView, birthdayEditText;
    private Button submitButton, datePickerButton;
    private UserModel userModel;

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

                //設定警告視窗
                final AlertDialog progressDialog = Helper.progressDialog(this, "註冊中...");

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
                        Toast.makeText(AddUserActivity.this, "新增成功!:)", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponseFailure(Call<AirTableResponse<User>> call, Response<AirTableResponse<User>> response) {
                        Toast.makeText(AddUserActivity.this, "新增失敗!伺服器回應有些怪怪的~~", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<AirTableResponse<User>> call, Throwable t) {
                        Toast.makeText(AddUserActivity.this, "新增失敗!可能是網路沒有通唷~", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(AddUserActivity.this, "新增失敗!請填寫所有的欄位!", Toast.LENGTH_SHORT).show();
            }
        });
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
