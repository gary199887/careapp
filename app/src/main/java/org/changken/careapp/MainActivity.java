package org.changken.careapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.changken.careapp.models.User;
import org.changken.careapp.models.UserConvert;
import org.changken.careapp.tools.Http;
import org.changken.careapp.tools.ListResponse;
import org.changken.careapp.tools.Response;

public class MainActivity extends AppCompatActivity {

    TextView dataTextView;
    Button getDataButton, addDataButton;
    StringBuffer dataTextViewOutput = new StringBuffer();
    Http http;
    UserConvert userConvert;

    /**
     * 初始化相關View元件
     */
    protected void initial() {
        dataTextView = (TextView) findViewById(R.id.data_text_view);
        getDataButton = (Button) findViewById(R.id.get_data_button);
        addDataButton = (Button) findViewById(R.id.add_data_button);
        http = new Http(BuildConfig.AIRTABLE_API_KEY);
        userConvert = new UserConvert();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initial();

        getDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //從網路撈資料一定只能用thread
                new GetDataTask().execute("https://api.airtable.com/v0/" + BuildConfig.AIRTABLE_BASE_ID + "/user?pageSize=10&view=Grid%20view");
            }
        });

        addDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
                startActivity(intent);
            }
        });
    }

    private class GetDataTask extends AsyncTask<String, Void, StringBuffer> {

        @Override
        protected StringBuffer doInBackground(String... urlAddress) {
            return http.doGet(urlAddress[0]);
        }

        @Override
        protected void onPostExecute(StringBuffer sb) {
            //debug
            Log.i("MainActivity", "[Response] " + sb.toString());
            //取得ListResponse
            ListResponse<User> listResponse = new ListResponse<>();
            try {
                listResponse = userConvert.getListResponse(sb.toString());
                //Log.i("MainActivity", "[Response] " + listResponse.getRecords().get(0) + "");
                //Log.i("MainActivity", "[Response] " + listResponse.getRecords().get(0).getFields().getId() + "");
            } catch (Exception e) {
                Log.e("MainActivity", "[Error Response] " + e.getMessage());
            }

            //取出每一筆的使用者資訊 姓名、電郵
            for(Response<User> response  : listResponse.getRecords()){
                dataTextViewOutput.append(response.getFields().getId());
                dataTextViewOutput.append('\n');
                dataTextViewOutput.append(response.getFields().getName());
                dataTextViewOutput.append('\n');
                dataTextViewOutput.append(response.getFields().getEmail());
                dataTextViewOutput.append('\n');
            }

            //顯示在TextView上
            dataTextView.setText(dataTextViewOutput.toString());
        }
    }
}
