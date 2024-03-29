package org.changken.careapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.changken.careapp.tools.Helper;
import org.changken.careapp.tools.Nav;

public class MemberCenterActivity extends BaseNavActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View reservationLL = findViewById(R.id.reservation);
        View profileLL = findViewById(R.id.personal_info);
        View trafficLL = findViewById(R.id.traffic_guide);
        View registerLL = findViewById(R.id.register_record);
        View surgery_queryLL = findViewById(R.id.surgery_query);
        View mobile_payLL = findViewById(R.id.mobile_pay);

        reservationLL.setOnClickListener((View v) -> {
            startActivity(new Intent(MemberCenterActivity.this, ReservationActivity.class));
            finish();
        });

        profileLL.setOnClickListener((View v) -> {
            startActivity(new Intent(MemberCenterActivity.this, EditProfileActivity.class));
            finish();
        });

        trafficLL.setOnClickListener((View v) -> {
            startActivity(new Intent(MemberCenterActivity.this, TrafficInfoActivity.class));
            finish();
        });

        registerLL.setOnClickListener((View v) -> {
            startActivity(new Intent(MemberCenterActivity.this, RegistrationRecordActivity.class));
            finish();
        });

        surgery_queryLL.setOnClickListener((View v) -> {
            startActivity(new Intent(MemberCenterActivity.this, SurgicalInquiryActivity.class));
            finish();
        });

        mobile_payLL.setOnClickListener((View v) -> {
            startActivity(new Intent(MemberCenterActivity.this, MobilePayActivity.class));
            finish();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.member_center, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_view_notice) {
            startActivity(new Intent(MemberCenterActivity.this, NotificationActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected Nav.MenuClickAction getMenuClickAction() {
        return new Nav.MenuClickAction() {
            @Override
            public void goMemberCenter() {
                //non
            }

            @Override
            public void goPersonalInfo() {
                startActivity(new Intent(MemberCenterActivity.this, EditProfileActivity.class));
                finish();
            }

            @Override
            public void goReg() {
                startActivity(new Intent(MemberCenterActivity.this, ReservationActivity.class));
                finish();
            }

            @Override
            public void goQueryCancel() {
                startActivity(new Intent(MemberCenterActivity.this, ReservationSearchActivity.class));
                finish();
            }

            @Override
            public void goRegRecord() {
                startActivity(new Intent(MemberCenterActivity.this, RegistrationRecordActivity.class));
                finish();
            }

            @Override
            public void goTrafficGuide() {
                startActivity(new Intent(MemberCenterActivity.this, TrafficInfoActivity.class));
                finish();
            }

            @Override
            public void goCheckIn() {
                startActivity(new Intent(MemberCenterActivity.this, QRActivity.class));
                finish();
            }

            @Override
            public void goLogout() {
                Helper.logoutProcess(MemberCenterActivity.this);
                startActivity(new Intent(MemberCenterActivity.this, MainActivity.class));
                finish();
            }
        };
    }

    @Override
    protected int getMyOwnContentView() {
        return R.layout.content_member_center;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(this)
                    .setTitle("離開?")
                    .setTitle("您確定要離開嗎")
                    .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MemberCenterActivity.this, "慢走不送!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })
                    .setNegativeButton("算了", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MemberCenterActivity.this, "也是!這是個最好的選擇!", Toast.LENGTH_SHORT).show();
                            //不做事!
                        }
                    })
                    .show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
