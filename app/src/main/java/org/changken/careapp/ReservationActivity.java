package org.changken.careapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.changken.careapp.tools.Nav;

public class ReservationActivity extends BaseNavActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final TextView textView = (TextView) findViewById(R.id.reservation_hello);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setTextSize(36);
            }
        });
    }

    @Override
    protected Nav.MenuClickAction getMenuClickAction() {
        return new Nav.MenuClickAction() {
            @Override
            public void goMemberCenter() {
                startActivity(new Intent(ReservationActivity.this, MemberCenterActivity.class));
            }

            @Override
            public void goPersonalInfo() {

            }

            @Override
            public void goReg() {
                //non
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
                startActivity(new Intent(ReservationActivity.this, MainActivity.class));
            }
        };
    }

    @Override
    protected int getMyOwnContentView() {
        return R.layout.content_reservation;
    }
}
