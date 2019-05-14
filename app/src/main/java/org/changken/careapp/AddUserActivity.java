package org.changken.careapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import org.changken.careapp.datamodels.AirTableResponse;
import org.changken.careapp.datamodels.User;
import org.changken.careapp.models.UserModel;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class AddUserActivity extends AppCompatActivity {

    EditText nameTextView, idNumberTextView, pwTextView,
            emailTextView, phoneTextView, addressTextView, birthdayTextView;
    Button submitButton, datePickerButton;
    UserModel userModel;

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
        birthdayTextView = (EditText) findViewById(R.id.birthday_text_view);

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
            //建立新增資料的請求
            Call<AirTableResponse<User>> responseCall = userModel.add(new User(nameTextView.getText().toString(),
                    idNumberTextView.getText().toString(),
                    pwTextView.getText().toString(),
                    emailTextView.getText().toString(),
                    phoneTextView.getText().toString(),
                    addressTextView.getText().toString(),
                    birthdayTextView.getText().toString()
            ));

            //執行新增資料
            responseCall.enqueue(new Callback<AirTableResponse<User>>() {
                @EverythingIsNonNull
                @Override
                public void onResponse(Call<AirTableResponse<User>> call, Response<AirTableResponse<User>> response) {
                    if (response.isSuccessful()) { //200 ok!
                        Toast.makeText(AddUserActivity.this, "新增成功!:)", Toast.LENGTH_SHORT).show();
                    } else { //500 server error or 404
                        Toast.makeText(AddUserActivity.this, "新增失敗!伺服器回應有些怪怪的~~", Toast.LENGTH_SHORT).show();
                    }
                }

                @EverythingIsNonNull
                @Override
                public void onFailure(Call<AirTableResponse<User>> call, Throwable t) {
                    Toast.makeText(AddUserActivity.this, "新增失敗!可能是網路沒有通唷~", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    /**
     * 更新生日欄位
     *
     * @param year int
     * @param  month int
     * @param day int
     * */
    public void setBirthdayTextView(int year, int month, int day){
        String date = year + "-" + month + "-" + day;
        birthdayTextView.setText(date);
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
        private AddUserActivity mAddUserActivity;

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            mAddUserActivity = (AddUserActivity) context;
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mAddUserActivity.setBirthdayTextView(year, month, dayOfMonth);
        }
    }
}
