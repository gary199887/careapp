package org.changken.careapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.changken.careapp.tools.Helper;
import org.changken.careapp.tools.Nav;

public class PaymentSettingActivity extends BaseNavActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected Nav.MenuClickAction getMenuClickAction() {
        return new Nav.MenuClickAction() {
            @Override
            public void goMemberCenter() {
                startActivity(new Intent(PaymentSettingActivity.this, MemberCenterActivity.class));
                finish();
            }

            @Override
            public void goPersonalInfo() {
                startActivity(new Intent(PaymentSettingActivity.this, EditProfileActivity.class));
                finish();
            }

            @Override
            public void goReg() {
                startActivity(new Intent(PaymentSettingActivity.this, ReservationActivity.class));
                finish();
            }

            @Override
            public void goQueryCancel() {
                startActivity(new Intent(PaymentSettingActivity.this, ConfirmReservationActivity.class));
                finish();
            }

            @Override
            public void goRegRecord() {
                startActivity(new Intent(PaymentSettingActivity.this, RegistrationRecordActivity.class));
                finish();
            }

            @Override
            public void goTrafficGuide() {
                startActivity(new Intent(PaymentSettingActivity.this, TrafficInfoActivity.class));
                finish();
            }

            @Override
            public void goCheckIn() {
                startActivity(new Intent(PaymentSettingActivity.this, QRActivity.class));
                finish();
            }

            @Override
            public void goLogout() {
                Helper.logoutProcess(PaymentSettingActivity.this);
                startActivity(new Intent(PaymentSettingActivity.this, MainActivity.class));
                finish();
            }
        };
    }

    @Override
    protected int getMyOwnContentView() {
        return R.layout.activity_payment_setting;
    }
}
