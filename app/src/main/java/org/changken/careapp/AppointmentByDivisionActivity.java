package org.changken.careapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import org.changken.careapp.tools.Helper;
import org.changken.careapp.tools.Nav;

public class AppointmentByDivisionActivity extends BaseNavActivity {

    private Button reservationDivisionButton;

    private void initial(){
        reservationDivisionButton = (Button)findViewById(R.id.reservation_division_button);
    }

    private void setListeners() {
        reservationDivisionButton.setOnClickListener((v) -> {
            startActivity(new Intent(AppointmentByDivisionActivity.this, ViewDivisionActivity.class));
            finish();
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initial();
        setListeners();
    }

    @Override
    protected Nav.MenuClickAction getMenuClickAction() {
        return new Nav.MenuClickAction() {
            @Override
            public void goMemberCenter() {
                startActivity(new Intent(AppointmentByDivisionActivity.this, MemberCenterActivity.class));
                finish();
            }

            @Override
            public void goPersonalInfo() {
                startActivity(new Intent(AppointmentByDivisionActivity.this, EditProfileActivity.class));
                finish();
            }

            @Override
            public void goReg() {
                startActivity(new Intent(AppointmentByDivisionActivity.this, ReservationActivity.class));
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
                startActivity(new Intent(AppointmentByDivisionActivity.this, TrafficInfoActivity.class));
                finish();
            }

            @Override
            public void goCheckIn() {
                startActivity(new Intent(AppointmentByDivisionActivity.this, QRActivity.class));
                finish();
            }

            @Override
            public void goLogout() {
                Helper.logoutProcess(AppointmentByDivisionActivity.this);
                startActivity(new Intent(AppointmentByDivisionActivity.this, MainActivity.class));
                finish();
            }
        };
    }

    @Override
    protected int getMyOwnContentView() {
        return R.layout.content_appointment_by_division;
    }
}
