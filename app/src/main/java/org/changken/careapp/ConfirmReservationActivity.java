package org.changken.careapp;

import android.content.Intent;
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


import org.changken.careapp.datamodels.Reservation;
import org.changken.careapp.tools.Helper;
import org.changken.careapp.tools.Nav;
import org.changken.careapp.R;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfirmReservationActivity extends BaseNavActivity {
    private int resCount;
    private int docTime_id;
    private BaseModel<Reservation> reservationModel;
    private Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        //String date=String.valueOf(intent.getIntExtra(""))
        BaseModel<DoctorTime> docTimeModel = new DoctorTimeModel();
        reservationModel = new ReservationModel();

        super.onCreate(savedInstanceState);
        final TextView date = findViewById(R.id.textView3);
        //date.setText(date.getText() + "");
        final TextView subject = findViewById(R.id.textView4);
        //subject.setText(subject.getText() + "abvf");
        final TextView doctor = findViewById(R.id.textView5);
        //doctor.setText(doctor.getText() + "abvfd");
        final TextView note = findViewById(R.id.textView6);
        //note.setText(note.getText() + "abvfd");
        confirm = findViewById(R.id.Button02);

        String recordId = intent.getStringExtra("recordId");
        String userId = intent.getStringExtra("userId");

        docTimeModel.get(recordId, new ModelCallback<AirTableResponse<DoctorTime>>() {
            @Override
            public void onResponseSuccess(Call<AirTableResponse<DoctorTime>> call, Response<AirTableResponse<DoctorTime>> response) {
                date.setText(date.getText() + response.body().getFields().getDocTime_date(), TextView.BufferType.EDITABLE);
                subject.setText(subject.getText() + response.body().getFields().getSubDiv_name().get(0), TextView.BufferType.EDITABLE);
                doctor.setText(doctor.getText() + response.body().getFields().getDoc_name().get(0), TextView.BufferType.EDITABLE);
                resCount = response.body().getFields().getDocTime_resCount();
                docTime_id = response.body().getFields().getDocTime_id();
                checkIfReservationIsExisted(userId, docTime_id);
            }

            @Override
            public void onResponseFailure(Call<AirTableResponse<DoctorTime>> call, Response<AirTableResponse<DoctorTime>> response) {
                //Toast.makeText(EditProfileActivity.this, "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<AirTableResponse<DoctorTime>> call, Throwable t) {
                //Toast.makeText(EditProfileActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });

        confirm.setOnClickListener((View v) -> {
            List<String> userIdList = new ArrayList<>();
            userIdList.add(userId);

            List<String> doctorTimeId = new ArrayList<>();
            doctorTimeId.add(String.valueOf(docTime_id));

            Reservation reservation = new Reservation(resCount + 1, 0, userIdList, doctorTimeId);

            reservationModel.add(reservation, new ModelCallback<AirTableResponse<Reservation>>() {
                @Override
                public void onResponseSuccess(Call<AirTableResponse<Reservation>> call, Response<AirTableResponse<Reservation>> response) {
                    Toast.makeText(ConfirmReservationActivity.this, "success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ConfirmReservationActivity.this, MemberCenterActivity.class));
                    finish();
                }

                @Override
                public void onResponseFailure(Call<AirTableResponse<Reservation>> call, Response<AirTableResponse<Reservation>> response) {
                    Toast.makeText(ConfirmReservationActivity.this, "failed", Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < userIdList.size(); i++)
                        Toast.makeText(ConfirmReservationActivity.this, userIdList.get(i), Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<AirTableResponse<Reservation>> call, Throwable t) {
                    Toast.makeText(ConfirmReservationActivity.this, "failed2", Toast.LENGTH_SHORT).show();

                }
            });
        });
    }

    @Override
    protected Nav.MenuClickAction getMenuClickAction() {
        return new Nav.MenuClickAction() {
            @Override
            public void goMemberCenter() {
                startActivity(new Intent(ConfirmReservationActivity.this, MemberCenterActivity.class));
                finish();
            }

            @Override
            public void goPersonalInfo() {
                startActivity(new Intent(ConfirmReservationActivity.this, EditProfileActivity.class));
                finish();
            }

            @Override
            public void goReg() {
                startActivity(new Intent(ConfirmReservationActivity.this, ReservationActivity.class));
                finish();
            }

            @Override
            public void goQueryCancel() {
                startActivity(new Intent(ConfirmReservationActivity.this, ReservationSearchActivity.class));
                finish();
            }

            @Override
            public void goRegRecord() {
                startActivity(new Intent(ConfirmReservationActivity.this, RegistrationRecordActivity.class));
                finish();
            }

            @Override
            public void goTrafficGuide() {
                startActivity(new Intent(ConfirmReservationActivity.this, TrafficInfoActivity.class));
                finish();
            }

            @Override
            public void goCheckIn() {
                startActivity(new Intent(ConfirmReservationActivity.this, QRActivity.class));
                finish();
            }

            @Override
            public void goLogout() {
                Helper.logoutProcess(ConfirmReservationActivity.this);
                startActivity(new Intent(ConfirmReservationActivity.this, MainActivity.class));
                finish();
            }
        };
    }

    private void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int getMyOwnContentView() {
        return R.layout.activity_confirm_reservation;
    }

    private void checkIfReservationIsExisted(String idNumber, int doctimeid) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("view", "Grid%20view");
        queryMap.put("filterByFormula", "AND({user_id} = '" + idNumber + "', {docTime_id} = '" + doctimeid +"')");
        reservationModel.list(queryMap, new ModelCallback<AirTableListResponse<Reservation>>() {
            @Override
            public void onResponseSuccess(Call<AirTableListResponse<Reservation>> call, Response<AirTableListResponse<Reservation>> response) {
                if (response.body().getRecords().size() > 0) {
                    showToastMessage("已經預約了!");
                    confirm.setEnabled(false);
                    confirm.setClickable(false);
                }
            }

            @Override
            public void onResponseFailure(Call<AirTableListResponse<Reservation>> call, Response<AirTableListResponse<Reservation>> response) {
                showToastMessage("查詢失敗!伺服器回應有些怪怪的~~");
            }

            @Override
            public void onFailure(Call<AirTableListResponse<Reservation>> call, Throwable t) {
                showToastMessage("查詢失敗!可能是網路沒有通唷~");
            }
        });
    }
}
