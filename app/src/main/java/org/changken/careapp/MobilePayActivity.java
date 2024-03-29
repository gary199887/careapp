package org.changken.careapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.changken.careapp.tools.Helper;
import org.changken.careapp.tools.Nav;

public class MobilePayActivity extends BaseNavActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button button = findViewById(R.id.paySetButton);
        button.setOnClickListener((v) -> {
            startActivity(new Intent(MobilePayActivity.this, PaymentSettingActivity.class));
            finish();
        });
        TextView textView3 = findViewById(R.id.textView24);

        textView3.setOnClickListener((v)-> {

            startActivity(new Intent(MobilePayActivity.this, PaymentRecordActivity.class));
            finish();
        });
        TextView textView4 = findViewById(R.id.textView7);

        textView4.setOnClickListener((v)-> {

            startActivity(new Intent(MobilePayActivity.this, IWantPayActivity.class));
            finish();
        });
        View imageReordLL = findViewById(R.id.imageRecord);

        imageReordLL.setOnClickListener((View v) -> {
            startActivity(new Intent(MobilePayActivity.this, PaymentRecordActivity.class));
            finish();
        });
        View imagePayLL = findViewById(R.id.imagePay);

        imagePayLL.setOnClickListener((View v) -> {
            startActivity(new Intent(MobilePayActivity.this, IWantPayActivity.class));
            finish();
        });

    }

    @Override
    protected Nav.MenuClickAction getMenuClickAction() {
        return new Nav.MenuClickAction() {
            @Override
            public void goMemberCenter() {
                startActivity(new Intent(MobilePayActivity.this, MemberCenterActivity.class));
                finish();
            }

            @Override
            public void goPersonalInfo() {
                startActivity(new Intent(MobilePayActivity.this, EditProfileActivity.class));
                finish();
            }

            @Override
            public void goReg() {
                startActivity(new Intent(MobilePayActivity.this, ReservationActivity.class));
                finish();
            }

            @Override
            public void goQueryCancel() {
                startActivity(new Intent(MobilePayActivity.this, ReservationSearchActivity.class));
                finish();
            }

            @Override
            public void goRegRecord() {
                startActivity(new Intent(MobilePayActivity.this, RegistrationRecordActivity.class));
                finish();
            }

            @Override
            public void goTrafficGuide() {
                startActivity(new Intent(MobilePayActivity.this, TrafficInfoActivity.class));
                finish();
            }

            @Override
            public void goCheckIn() {
                startActivity(new Intent(MobilePayActivity.this, QRActivity.class));
                finish();
            }

            @Override
            public void goLogout() {
                Helper.logoutProcess(MobilePayActivity.this);
                startActivity(new Intent(MobilePayActivity.this, MainActivity.class));
                finish();
            }
        };
    }

    @Override
    protected int getMyOwnContentView() {
        return R.layout.activity_mobile_pay;
    }
}
