package org.changken.careapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.changken.careapp.tools.Helper;
import org.changken.careapp.tools.Nav;

public class NotificationActivity extends BaseNavActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notice, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_view_notice_setting) {
            startActivity(new Intent(NotificationActivity.this, NotificationSettingActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected Nav.MenuClickAction getMenuClickAction() {
        return new Nav.MenuClickAction() {
            @Override
            public void goMemberCenter() {
                startActivity(new Intent(NotificationActivity.this, MemberCenterActivity.class));
                finish();
            }

            @Override
            public void goPersonalInfo() {
                startActivity(new Intent(NotificationActivity.this, EditProfileActivity.class));
                finish();
            }

            @Override
            public void goReg() {
                startActivity(new Intent(NotificationActivity.this, ReservationActivity.class));
                finish();
            }

            @Override
            public void goQueryCancel() {
                startActivity(new Intent(NotificationActivity.this, ReservationSearchActivity.class));
                finish();
            }

            @Override
            public void goRegRecord() {
                startActivity(new Intent(NotificationActivity.this, RegistrationRecordActivity.class));
                finish();
            }

            @Override
            public void goTrafficGuide() {
                startActivity(new Intent(NotificationActivity.this, TrafficInfoActivity.class));
                finish();
            }

            @Override
            public void goCheckIn() {
                startActivity(new Intent(NotificationActivity.this, QRActivity.class));
                finish();
            }

            @Override
            public void goLogout() {
                Helper.logoutProcess(NotificationActivity.this);
                startActivity(new Intent(NotificationActivity.this, MainActivity.class));
                finish();
            }
        };
    }

    @Override
    protected int getMyOwnContentView() {
        return R.layout.content_notification;
    }
}
