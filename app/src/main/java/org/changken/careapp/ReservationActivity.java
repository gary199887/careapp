package org.changken.careapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.changken.careapp.tools.Helper;
import org.changken.careapp.tools.Nav;

public class ReservationActivity extends BaseNavActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final TextView textView = (TextView) findViewById(R.id.appointment_by_division);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReservationActivity.this, ReservationByDivisionActivity.class));
                finish();
            }
        });

        final TextView reservationByDoctor = (TextView) findViewById(R.id.appointment_by_doctor);

        reservationByDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReservationActivity.this, DoctorReservationActivity.class));
                finish();
            }
        });

        final TextView textView3 = (TextView) findViewById(R.id.query_division_cancel);

        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReservationActivity.this, ReservationSearchActivity.class));
                finish();
            }
        });
        final TextView textView4 = (TextView) findViewById(R.id.check_in);

        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReservationActivity.this, QRActivity.class));
                finish();
            }
        });
    }

    @Override
    protected Nav.MenuClickAction getMenuClickAction() {
        return new Nav.MenuClickAction() {
            @Override
            public void goMemberCenter() {
                startActivity(new Intent(ReservationActivity.this, MemberCenterActivity.class));
                finish();
            }

            @Override
            public void goPersonalInfo() {
                startActivity(new Intent(ReservationActivity.this, EditProfileActivity.class));
                finish();
            }

            @Override
            public void goReg() {
                //non
            }

            @Override
            public void goQueryCancel() {
                startActivity(new Intent(ReservationActivity.this, ReservationSearchActivity.class));
                finish();
            }

            @Override
            public void goRegRecord() {
                startActivity(new Intent(ReservationActivity.this, RegistrationRecordActivity.class));
                finish();
            }

            @Override
            public void goTrafficGuide() {
                startActivity(new Intent(ReservationActivity.this, TrafficInfoActivity.class));
                finish();
            }

            @Override
            public void goCheckIn() {
                startActivity(new Intent(ReservationActivity.this, QRActivity.class));
                finish();
            }

            @Override
            public void goLogout() {
                Helper.logoutProcess(ReservationActivity.this);
                startActivity(new Intent(ReservationActivity.this, MainActivity.class));
                finish();
            }
        };
    }

    @Override
    protected int getMyOwnContentView() {
        return R.layout.content_reservation;
    }
}
