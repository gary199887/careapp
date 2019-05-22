package org.changken.careapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.changken.careapp.adapter.MyListAdapter;
import org.changken.careapp.datamodels.User;
import org.changken.careapp.models.BaseModel;
import org.changken.careapp.models.ModelCallback;
import org.changken.careapp.models.UserModel;
import org.changken.careapp.datamodels.AirTableListResponse;
import org.changken.careapp.datamodels.AirTableResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MyListAdapter myListAdapter;

    BaseModel<User> userModel;

    /**
     * 初始化元件
     */
    protected void initial() {
        userModel = new UserModel();

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("pageSize", "10");
        queryMap.put("view", "Grid%20view");

        //從網路撈資料一定只能用thread
        userModel.list(queryMap, new ModelCallback<AirTableListResponse<User>>() {
            @Override
            public void onResponseSuccess(Call<AirTableListResponse<User>> call, Response<AirTableListResponse<User>> response) {
                Toast.makeText(ListActivity.this, "取得成功!", Toast.LENGTH_SHORT).show();
                //設定RecyclerView
                setRecyclerView(response.body().getRecords());
            }

            @Override
            public void onResponseFailure(Call<AirTableListResponse<User>> call, Response<AirTableListResponse<User>> response) {
                Toast.makeText(ListActivity.this, "新增失敗!伺服器好像怪怪的唷~", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<AirTableListResponse<User>> call, Throwable t) {
                Toast.makeText(ListActivity.this, "新增失敗!可能是網路沒有通唷~", Toast.LENGTH_SHORT).show();
            }
        });
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
    }

    /**
     * 設定RecyclerView
     *
     * @param data List<AirTableResponse<User>>
     */
    private void setRecyclerView(List<AirTableResponse<User>> data) {
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
}
