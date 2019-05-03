package org.changken.careapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.changken.careapp.adapter.MyListAdapter;
import org.changken.careapp.models.User;
import org.changken.careapp.models.UserConvert;
import org.changken.careapp.tools.Http;
import org.changken.careapp.tools.ListResponse;
import org.changken.careapp.tools.Response;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MyListAdapter myListAdapter;

    List<Response<User>> data;
    UserConvert userConvert;
    Http http;

    /**
     * 初始化元件
     */
    protected void initial() {
        userConvert = new UserConvert();
        http = new Http(BuildConfig.AIRTABLE_API_KEY);
        data = new ArrayList<Response<User>>();

        //從網路撈資料一定只能用thread
        new GetDataTask().execute("https://api.airtable.com/v0/" + BuildConfig.AIRTABLE_BASE_ID + "/user?pageSize=10&view=Grid%20view");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //得到intent資料
        Intent intent = getIntent();
        //finish();

        //初始化元件
        initial();

        //產生列表
        recyclerView = (RecyclerView) findViewById(R.id.list_view);
        recyclerView.setHasFixedSize(true);

        //產生LinearLayoutManager
        layoutManager = new LinearLayoutManager(this);
        //設定LayoutManager
        recyclerView.setLayoutManager(layoutManager);

        //設定Adapter
        myListAdapter = new MyListAdapter(this, data);
        //設定分隔線
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //綁定Adapter
        recyclerView.setAdapter(myListAdapter);
    }

    private class GetDataTask extends AsyncTask<String, Void, StringBuffer> {

        @Override
        protected StringBuffer doInBackground(String... urlAddress) {
            //執行Http get動作
            return http.doGet(urlAddress[0]);
        }

        @Override
        protected void onPostExecute(StringBuffer sb) {
            //debug用
            //Log.i("ListActivity", "[listResponse] " + sb.toString());

            //轉換Json字串
            ListResponse<User> userListResponse = userConvert.getListResponse(sb.toString());

            //取得每筆使用者的資料
            data = userListResponse.getRecords();

            //看看List<Response<User>>的廬山真面目
            //Log.i("ListActivity", "[List<Response<User>>] " + data.getClass().getTypeName());

            //更新串列資料
            myListAdapter.updateData(data);
        }
    }
}
