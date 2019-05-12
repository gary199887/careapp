package org.changken.careapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.changken.careapp.datamodels.AirTableResponse;
import org.changken.careapp.datamodels.User;
import org.changken.careapp.models.UserModel;
import org.changken.careapp.tools.UserConvert;
import org.changken.careapp.tools.Http;

import java.util.ArrayList;
import java.util.List;

public class AddUserActivity extends AppCompatActivity {

    EditText nameTextView, idNumberTextView, pwTextView,
            emailTextView, phoneTextView, addressTextView, birthdayTextView;
    Button submitButton;
    UserModel userModel;
    //Http http;
    //UserConvert userConvert;

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

        userModel = new UserModel();
        //http = new Http(BuildConfig.AIRTABLE_API_KEY);
        //userConvert = new UserConvert();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        initial();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String userJson = userConvert.setRequest(
                        new User(nameTextView.getText().toString(),
                                idNumberTextView.getText().toString(),
                                pwTextView.getText().toString(),
                                emailTextView.getText().toString(),
                                phoneTextView.getText().toString(),
                                addressTextView.getText().toString(),
                                birthdayTextView.getText().toString()
                        ));*/
                //發送新增資料的請求
                AirTableResponse<User> response = userModel.add(new User(nameTextView.getText().toString(),
                        idNumberTextView.getText().toString(),
                        pwTextView.getText().toString(),
                        emailTextView.getText().toString(),
                        phoneTextView.getText().toString(),
                        addressTextView.getText().toString(),
                        birthdayTextView.getText().toString()
                ));
                Log.i("AddUserActivity", "[response] " + (response == null));
                //new SetDataTask().execute("https://api.airtable.com/v0/" + BuildConfig.AIRTABLE_BASE_ID + "/user", userJson);
                Toast.makeText(AddUserActivity.this, "新增成功!:)", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*private class SetDataTask extends AsyncTask<String, Void, StringBuffer> {
        @Override
        protected StringBuffer doInBackground(String... params) {
            //debug
            Log.i("AddUserActivity", "[Data] " + params[1]);
            return http.doPost(params[0], params[1]);
        }

        @Override
        protected void onPostExecute(StringBuffer sb) {
            //debug
            Log.i("AddUserActivity", "[Response]" + sb.toString());
        }
    }*/
}
