package org.changken.careapp;

import android.content.Intent;
import android.os.Bundle;

import org.changken.careapp.tools.Helper;
import org.changken.careapp.tools.Nav;

public class NotificationSettingActivity extends BaseNavActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Nav.MenuClickAction getMenuClickAction() {
        return new Nav.MenuClickAction() {
            @Override
            public void goMemberCenter() {
                startActivity(new Intent(NotificationSettingActivity.this, MemberCenterActivity.class));
                finish();
            }

            @Override
            public void goPersonalInfo() {
                startActivity(new Intent(NotificationSettingActivity.this, EditProfileActivity.class));
                finish();
            }

            @Override
            public void goReg() {
                startActivity(new Intent(NotificationSettingActivity.this, ReservationActivity.class));
                finish();
            }

            @Override
            public void goQueryCancel() {
                startActivity(new Intent(NotificationSettingActivity.this, ReservationSearchActivity.class));
                finish();
            }

            @Override
            public void goRegRecord() {
                startActivity(new Intent(NotificationSettingActivity.this, RegistrationRecordActivity.class));
                finish();
            }

            @Override
            public void goTrafficGuide() {
                startActivity(new Intent(NotificationSettingActivity.this, TrafficInfoActivity.class));
                finish();
            }

            @Override
            public void goCheckIn() {
                startActivity(new Intent(NotificationSettingActivity.this, QRActivity.class));
                finish();
            }

            @Override
            public void goLogout() {
                Helper.logoutProcess(NotificationSettingActivity.this);
                startActivity(new Intent(NotificationSettingActivity.this, MainActivity.class));
                finish();
            }
        };
    }

    @Override
    protected int getMyOwnContentView() {
        return R.layout.content_notification_settimg;
    }
}
