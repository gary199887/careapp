package org.changken.careapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.changken.careapp.tools.Nav;

public class MemberCenterActivity extends BaseNavActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View reservationLL = (LinearLayout) findViewById(R.id.reservation);

        reservationLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MemberCenterActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });
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

            }

            @Override
            public void goReg() {
                startActivity(new Intent(MemberCenterActivity.this, ReservationActivity.class));
            }

            @Override
            public void goQueryCancel() {

            }

            @Override
            public void goRegRecord() {

            }

            @Override
            public void goTrafficGuide() {

            }

            @Override
            public void goCheckIn() {

            }

            @Override
            public void goLogout() {
                startActivity(new Intent(MemberCenterActivity.this, MainActivity.class));
            }
        };
    }

    @Override
    protected int getMyOwnContentView() {
        return R.layout.content_member_center;
    }
}
