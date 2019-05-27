package org.changken.careapp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import org.changken.careapp.tools.Helper;
import org.changken.careapp.tools.Nav;
import org.changken.careapp.datamodels.*;
import org.changken.careapp.models.*;

import retrofit2.Call;
import retrofit2.Response;

public class EditProfileActivity extends BaseNavActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseModel<User> userModel = new UserModel();
        final EditText name_field = findViewById(R.id.name);
        final EditText pw_field = findViewById(R.id.pw);
        final EditText email_field = findViewById(R.id.email);
        final EditText phone_field = findViewById(R.id.phone);
        final EditText address_field = findViewById(R.id.address);
        final Button submitButton= findViewById(R.id.submit_button);
        final AlertDialog progressDialog = Helper.createProgressDialog(this, "修改中...");

        String recordId = Helper.getUserRecordId(EditProfileActivity.this);  //傳入airtable唯一ID
        userModel.get(recordId, new ModelCallback<AirTableResponse<User>>() {
            @Override
            public void onResponseSuccess(Call<AirTableResponse<User>> call, Response<AirTableResponse<User>> response) {
                //Toast.makeText(EditProfileActivity.this, "", Toast.LENGTH_SHORT).show();
                name_field.setText(response.body().getFields().getName(), TextView.BufferType.EDITABLE);
                pw_field.setText(response.body().getFields().getPassword(), TextView.BufferType.NORMAL);
                email_field.setText(response.body().getFields().getEmail(), TextView.BufferType.EDITABLE);
                phone_field.setText(response.body().getFields().getPhone(), TextView.BufferType.EDITABLE);
                address_field.setText(response.body().getFields().getAddress(), TextView.BufferType.EDITABLE);

            }
            @Override
            public void onResponseFailure(Call<AirTableResponse<User>> call, Response<AirTableResponse<User>> response) {
                //Toast.makeText(EditProfileActivity.this, "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<AirTableResponse<User>> call, Throwable t) {
                //Toast.makeText(EditProfileActivity.this, "", Toast.LENGTH_SHORT).show();
            }


        } );

submitButton.setOnClickListener((View v)->{
    String name = name_field.getText().toString();
    String email = email_field.getText().toString();
    String phone = phone_field.getText().toString();
    String address = address_field.getText().toString();
    String pw = pw_field.getText().toString();
    if (name.length() > 0 && pw.length() > 0 &&
            email.length() > 0 && phone.length() > 0 && address.length() > 0 ) {

        //設定警告視窗

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(pw);

        //建立並執行新增資料的請求
        userModel.update(recordId,user,new ModelCallback<AirTableResponse<User>>() {
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
                        Toast.makeText(EditProfileActivity.this, "修改成功!" ,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponseFailure(Call<AirTableResponse<User>> call, Response<AirTableResponse<User>> response) {
                        Toast.makeText(EditProfileActivity.this, "新增失敗!伺服器回應有些怪怪的~~", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<AirTableResponse<User>> call, Throwable t) {
                        Toast.makeText(EditProfileActivity.this, "修改失敗!可能是網路沒有通唷~", Toast.LENGTH_SHORT).show();
                    }
                }

        ) ;}else {
        Toast.makeText(EditProfileActivity.this, "修改失敗!請填寫所有的欄位!", Toast.LENGTH_SHORT).show();
    }
});





    }
    protected Nav.MenuClickAction getMenuClickAction() {
        return new Nav.MenuClickAction() {
            @Override
            public void goMemberCenter() {
                startActivity(new Intent(EditProfileActivity.this, MemberCenterActivity.class));
                finish();
            }

            @Override
            public void goPersonalInfo() {

            }

            @Override
            public void goReg() {
                startActivity(new Intent(EditProfileActivity.this, ReservationActivity.class));
            }

            @Override
            public void goQueryCancel() {

            }

            @Override
            public void goRegRecord() {

            }

            @Override
            public void goTrafficGuide() {
                startActivity(new Intent(EditProfileActivity.this, TrafficInfoActivity.class));
            }

            @Override
            public void goCheckIn() {
                startActivity(new Intent(EditProfileActivity.this, QRActivity.class));
                finish();
            }

            @Override
            public void goLogout() {
                Helper.logoutProcess(EditProfileActivity.this);
                startActivity(new Intent(EditProfileActivity.this, MainActivity.class));
                finish();
            }
        };
    }

    @Override
    protected int getMyOwnContentView() {
        return R.layout.activity_edit_profile;
    }
}
