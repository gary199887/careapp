package org.changken.careapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import org.changken.careapp.adapter.DivisionListAdapter;
import org.changken.careapp.datamodels.AirTableListResponse;
import org.changken.careapp.datamodels.AirTableResponse;
import org.changken.careapp.datamodels.DoctorTime;
import org.changken.careapp.models.BaseModel;
import org.changken.careapp.models.DoctorTimeModel;
import org.changken.careapp.models.ModelCallback;
import org.changken.careapp.tools.Helper;
import org.changken.careapp.tools.Nav;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class ViewDivisionActivity extends BaseNavActivity {

    private BaseModel<DoctorTime> doctorTimeModel;
    private RecyclerView morningList, afternoonList, eveningList;
    private DivisionListAdapter morningAdapter, afternoonAdapter, eveningAdapter;
    private List<AirTableResponse<DoctorTime>> morningData, afternoonData, eveningData;
    private SwipeRefreshLayout swipeRefreshLayout;

    private void initial() {
        doctorTimeModel = new DoctorTimeModel();
        morningData = new ArrayList<>();
        afternoonData = new ArrayList<>();
        eveningData = new ArrayList<>();

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDivisionListData();
            }
        });
        //swipeRefreshLayout.setRefreshing(false);//取消刷新,因此刷新图标
        //swipeRefreshLayout.setRefreshing(true);//设置为刷新状态，显示刷新图标
        //swipeRefreshLayout.setEnabled(false);//设置为不能刷新
        //boolean refreshing = swipeRefreshLayout.isRefreshing();//是否正在刷新

        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);

        //產生列表
        morningList = (RecyclerView) findViewById(R.id.morning_list);
        afternoonList = (RecyclerView) findViewById(R.id.afternoon_list);
        eveningList = (RecyclerView) findViewById(R.id.evening_list);

        //設定Adapter
        morningAdapter = new DivisionListAdapter(this, morningData);
        afternoonAdapter = new DivisionListAdapter(this, afternoonData);
        eveningAdapter = new DivisionListAdapter(this, eveningData);

        //設定項目點擊事件
        morningAdapter.setDivisionListOnItemClickListener(new DivisionListAdapter.DivisionListOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(ViewDivisionActivity.this, morningData.get(position).getId() + "", Toast.LENGTH_SHORT).show();
            }
        });

        afternoonAdapter.setDivisionListOnItemClickListener(new DivisionListAdapter.DivisionListOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(ViewDivisionActivity.this, afternoonData.get(position).getId() + "", Toast.LENGTH_SHORT).show();
            }
        });

        eveningAdapter.setDivisionListOnItemClickListener(new DivisionListAdapter.DivisionListOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(ViewDivisionActivity.this, eveningData.get(position).getId() + "", Toast.LENGTH_SHORT).show();
            }
        });

        //設定RecyclerView
        setRecyclerView(morningList, morningAdapter);
        setRecyclerView(afternoonList, afternoonAdapter);
        setRecyclerView(eveningList, eveningAdapter);

        //從airtable上獲取醫師值班表
        getDivisionListData();
    }

    /**
     * 設定RecyclerView
     *
     * @param recyclerView RecyclerView
     * @param adapter      DivisionListAdapter
     */
    private void setRecyclerView(RecyclerView recyclerView, DivisionListAdapter adapter) {
        //設定固定大小
        recyclerView.setHasFixedSize(true);

        //設定LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //設定分隔線
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //綁定Adapter
        recyclerView.setAdapter(adapter);
    }

    private void getDivisionListData() {
        Map<String, String> queryMap = new HashMap<>();

        queryMap.put("view", "morning");
        queryMap.put("filterByFormula", "AND({docTime_date} = DATETIME_PARSE('2019-05-08', 'YYYY-MM-DD'), {subDiv_id} = '1')");
        //queryMap.put("sort%5B0%5D%5Bfield%5D", "docTime_dayparts");
        //queryMap.put("sort%5B0%5D%5Bdirection%5D", "asc");

        doctorTimeModel.list(queryMap, new ModelCallback<AirTableListResponse<DoctorTime>>() {
            @Override
            public void onProgress() {
                if (!swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(true);
                }
            }

            @Override
            public void onProcessEnd() {
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onResponseSuccess(Call<AirTableListResponse<DoctorTime>> call, Response<AirTableListResponse<DoctorTime>> response) {
                morningData = response.body().getRecords();
                morningAdapter.updateData(morningData);
            }

            @Override
            public void onResponseFailure(Call<AirTableListResponse<DoctorTime>> call, Response<AirTableListResponse<DoctorTime>> response) {

            }

            @Override
            public void onFailure(Call<AirTableListResponse<DoctorTime>> call, Throwable t) {

            }
        });

        queryMap.clear();
        queryMap.put("view", "afternoon");
        //SUBSTITUTE(ARRAYJOIN({subDiv_id}, ',') , ',', '') = '1'
        queryMap.put("filterByFormula", "AND({docTime_date} = DATETIME_PARSE('2019-05-08', 'YYYY-MM-DD'), {subDiv_id} = '1')");
        //queryMap.put("sort%5B0%5D%5Bfield%5D", "docTime_dayparts");
        //queryMap.put("sort%5B0%5D%5Bdirection%5D", "asc");

        doctorTimeModel.list(queryMap, new ModelCallback<AirTableListResponse<DoctorTime>>() {
            @Override
            public void onProgress() {
                if (!swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(true);
                }
            }

            @Override
            public void onProcessEnd() {
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onResponseSuccess(Call<AirTableListResponse<DoctorTime>> call, Response<AirTableListResponse<DoctorTime>> response) {
                afternoonData = response.body().getRecords();
                afternoonAdapter.updateData(afternoonData);
            }

            @Override
            public void onResponseFailure(Call<AirTableListResponse<DoctorTime>> call, Response<AirTableListResponse<DoctorTime>> response) {

            }

            @Override
            public void onFailure(Call<AirTableListResponse<DoctorTime>> call, Throwable t) {

            }
        });

        queryMap.clear();
        queryMap.put("view", "evening");
        queryMap.put("filterByFormula", "AND({docTime_date} = DATETIME_PARSE('2019-05-08', 'YYYY-MM-DD'), {subDiv_id} = '1')");
        //queryMap.put("sort%5B0%5D%5Bfield%5D", "docTime_dayparts");
        //queryMap.put("sort%5B0%5D%5Bdirection%5D", "asc");

        doctorTimeModel.list(queryMap, new ModelCallback<AirTableListResponse<DoctorTime>>() {
            @Override
            public void onProgress() {
                if (!swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(true);
                }
            }

            @Override
            public void onProcessEnd() {
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
            
            @Override
            public void onResponseSuccess(Call<AirTableListResponse<DoctorTime>> call, Response<AirTableListResponse<DoctorTime>> response) {
                eveningData = response.body().getRecords();
                eveningAdapter.updateData(eveningData);
            }

            @Override
            public void onResponseFailure(Call<AirTableListResponse<DoctorTime>> call, Response<AirTableListResponse<DoctorTime>> response) {

            }

            @Override
            public void onFailure(Call<AirTableListResponse<DoctorTime>> call, Throwable t) {

            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initial();
    }

    @Override
    protected Nav.MenuClickAction getMenuClickAction() {
        return new Nav.MenuClickAction() {
            @Override
            public void goMemberCenter() {
                startActivity(new Intent(ViewDivisionActivity.this, MemberCenterActivity.class));
                finish();
            }

            @Override
            public void goPersonalInfo() {
                startActivity(new Intent(ViewDivisionActivity.this, EditProfileActivity.class));
                finish();
            }

            @Override
            public void goReg() {
                startActivity(new Intent(ViewDivisionActivity.this, ReservationActivity.class));
                finish();
            }

            @Override
            public void goQueryCancel() {

            }

            @Override
            public void goRegRecord() {

            }

            @Override
            public void goTrafficGuide() {
                startActivity(new Intent(ViewDivisionActivity.this, TrafficInfoActivity.class));
                finish();
            }

            @Override
            public void goCheckIn() {
                startActivity(new Intent(ViewDivisionActivity.this, QRActivity.class));
                finish();
            }

            @Override
            public void goLogout() {
                Helper.logoutProcess(ViewDivisionActivity.this);
                startActivity(new Intent(ViewDivisionActivity.this, MainActivity.class));
                finish();
            }
        };
    }

    @Override
    protected int getMyOwnContentView() {
        return R.layout.content_view_division;
    }
}
