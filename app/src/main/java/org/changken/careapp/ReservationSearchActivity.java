package org.changken.careapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import org.changken.careapp.adapter.ReservationSearchAdapter;
import org.changken.careapp.datamodels.AirTableListResponse;
import org.changken.careapp.datamodels.AirTableResponse;
import org.changken.careapp.datamodels.Reservation;
import org.changken.careapp.models.BaseModel;
import org.changken.careapp.models.ModelCallback;
import org.changken.careapp.models.ReservationModel;
import org.changken.careapp.tools.Helper;
import org.changken.careapp.tools.Nav;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class ReservationSearchActivity extends BaseNavActivity {
    private BaseModel<Reservation> reservationModel;
    private ReservationSearchAdapter reservationSearchAdapter;
    private List<AirTableResponse<Reservation>> reservationData;
    private String userId;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        reservationModel = new ReservationModel();
        reservationData = new ArrayList<>();
        listView = (ListView) findViewById(R.id.list_view);
        reservationSearchAdapter = new ReservationSearchAdapter(this, reservationData, reservationModel);
        listView.setAdapter(reservationSearchAdapter);
        userId = Helper.getUserId(this);

        updateReservationList("{user_id} = '" + userId + "'");
    }

    private void updateReservationList() {
        updateReservationList("");
    }

    //顯示toast訊息
    private void showToastMessage(String message) {
        Toast.makeText(ReservationSearchActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private void updateReservationList(String formula) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("view", "Grid%20view");
        queryMap.put("filterByFormula", formula);
        queryMap.put("sort%5B0%5D%5Bfield%5D", "res_status");
        queryMap.put("sort%5B0%5D%5Bdirection%5D", "asc");
        queryMap.put("sort%5B1%5D%5Bfield%5D", "docTime_date");
        queryMap.put("sort%5B1%5D%5Bdirection%5D", "asc");
        queryMap.put("sort%5B2%5D%5Bfield%5D", "docTime_dayparts");
        queryMap.put("sort%5B2%5D%5Bdirection%5D", "asc");

        reservationModel.list(queryMap, new ModelCallback<AirTableListResponse<Reservation>>() {
            @Override
            public void onResponseSuccess(Call<AirTableListResponse<Reservation>> call, Response<AirTableListResponse<Reservation>> response) {
                reservationData = response.body().getRecords();
                reservationSearchAdapter.updateData(reservationData);
            }

            @Override
            public void onResponseFailure(Call<AirTableListResponse<Reservation>> call, Response<AirTableListResponse<Reservation>> response) {
                showToastMessage("not found error");
            }

            @Override
            public void onFailure(Call<AirTableListResponse<Reservation>> call, Throwable t) {
                showToastMessage("no internet");
            }
        });
    }

    @Override
    protected Nav.MenuClickAction getMenuClickAction() {
        return new Nav.MenuClickAction() {
            @Override
            public void goMemberCenter() {
                startActivity(new Intent(ReservationSearchActivity.this, MemberCenterActivity.class));
                finish();
            }

            @Override
            public void goPersonalInfo() {
                startActivity(new Intent(ReservationSearchActivity.this, EditProfileActivity.class));
                finish();
            }

            @Override
            public void goReg() {
                startActivity(new Intent(ReservationSearchActivity.this, ReservationActivity.class));
                finish();
            }

            @Override
            public void goQueryCancel() {
                //non
            }

            @Override
            public void goRegRecord() {
                startActivity(new Intent(ReservationSearchActivity.this, RegistrationRecordActivity.class));
                finish();
            }

            @Override
            public void goTrafficGuide() {
                startActivity(new Intent(ReservationSearchActivity.this, TrafficInfoActivity.class));
                finish();
            }

            @Override
            public void goCheckIn() {
                startActivity(new Intent(ReservationSearchActivity.this, QRActivity.class));
                finish();
            }

            @Override
            public void goLogout() {
                Helper.logoutProcess(ReservationSearchActivity.this);
                startActivity(new Intent(ReservationSearchActivity.this, MainActivity.class));
                finish();
            }
        };
    }

    @Override
    protected int getMyOwnContentView() {
        return R.layout.content_reservation_searchandcancle;
    }
}
