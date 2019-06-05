package org.changken.careapp;

import android.content.Intent;
import android.os.Bundle;

import org.changken.careapp.tools.Helper;
import org.changken.careapp.tools.Nav;

public class ReservationSearchActivity extends BaseNavActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                //non
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
