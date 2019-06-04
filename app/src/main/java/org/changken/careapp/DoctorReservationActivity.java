package org.changken.careapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.changken.careapp.datamodels.AirTableListResponse;
import org.changken.careapp.datamodels.AirTableResponse;
import org.changken.careapp.datamodels.Doctor;
import org.changken.careapp.models.BaseModel;
import org.changken.careapp.models.DoctorModel;
import org.changken.careapp.models.ModelCallback;
import org.changken.careapp.tools.Nav;
import org.changken.careapp.adapter.ReservationByDoctorAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
// 引進側邊攔
public class DoctorReservationActivity extends BaseNavActivity {

    private ListView adrListView;
    private Button reservationButton;
    private ReservationByDoctorAdapter listAdapter;
    private BaseModel<Doctor> doctorModel;
    private List<AirTableResponse<Doctor>> doctorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initListView();
        initListeners();
    }

    //顯示ListView
    private void initListView() {
        getDoctorData();

        listAdapter = new ReservationByDoctorAdapter(this, doctorList);
        adrListView.setAdapter(listAdapter);
    }

    //抓airtable 資料
    private void getDoctorData() {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("view", "Grid%20view");

        doctorModel.list(queryMap, new ModelCallback<AirTableListResponse<Doctor>>() {
            @Override
            public void onResponseSuccess(Call<AirTableListResponse<Doctor>> call, Response<AirTableListResponse<Doctor>> response) {
                //更新adapter
                doctorList = response.body().getRecords();
                listAdapter.updateData(doctorList);
            }

            @Override
            public void onResponseFailure(Call<AirTableListResponse<Doctor>> call, Response<AirTableListResponse<Doctor>> response) {

            }

            @Override
            public void onFailure(Call<AirTableListResponse<Doctor>> call, Throwable t) {

            }
        });
    }

    //按鈕點擊邏輯事件
    private void initListeners() {
        reservationButton.setOnClickListener((v) -> {
            startActivity(new Intent(DoctorReservationActivity.this, ViewDivisionActivity.class));
            finish();
        });
    }

    private void initView() {
        adrListView = (ListView) findViewById(R.id.adr_listview);
        reservationButton = (Button)findViewById(R.id.adr_button);
        doctorModel = new DoctorModel();
        doctorList = new ArrayList<>();
    }

    @Override
    protected Nav.MenuClickAction getMenuClickAction() {
        return new Nav.MenuClickAction() {
            @Override
            public void goMemberCenter() {

            }

            @Override
            public void goPersonalInfo() {

            }

            @Override
            public void goReg() {

            }

            @Override
            public void goQueryCancel() {

            }

            @Override
            public void goRegRecord() {

            }

            @Override
            public void goTrafficGuide() {

            }

            @Override
            public void goCheckIn() {

            }

            @Override
            public void goLogout() {

            }
        };
    }

    @Override
    protected int getMyOwnContentView() {
        return R.layout.activity_doctor_reservation;
    }
}
