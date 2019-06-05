package org.changken.careapp;

import android.content.Intent;
import android.os.Bundle;

import org.changken.careapp.tools.Helper;
import org.changken.careapp.tools.Nav;

public class RegistrationRecordActivity extends BaseNavActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }



    @Override
    protected Nav.MenuClickAction getMenuClickAction() {
        return new Nav.MenuClickAction() {
            @Override
            public void goMemberCenter() {
                startActivity(new Intent(RegistrationRecordActivity.this, MemberCenterActivity.class));
                finish();
            }

            @Override
            public void goPersonalInfo() {
                startActivity(new Intent(RegistrationRecordActivity.this, EditProfileActivity.class));
                finish();
            }

            @Override
            public void goReg() {
                startActivity(new Intent(RegistrationRecordActivity.this, ReservationActivity.class));
                finish();
            }

            @Override
            public void goQueryCancel() {

            }

            @Override
            public void goRegRecord() {
                startActivity(new Intent(RegistrationRecordActivity.this, RegistrationRecordActivity.class));
                finish();
            }

            @Override
            public void goTrafficGuide() {
                startActivity(new Intent(RegistrationRecordActivity.this, TrafficInfoActivity.class));
                finish();
            }

            @Override
            public void goCheckIn() {
                //non
            }

            @Override
            public void goLogout() {
                Helper.logoutProcess(RegistrationRecordActivity.this);
                startActivity(new Intent(RegistrationRecordActivity.this, MainActivity.class));
                finish();
            }
        };
    }

    @Override
    protected int getMyOwnContentView() {
        return R.layout.activity_registration_record;
    }

}
