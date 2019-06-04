package org.changken.careapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.changken.careapp.datamodels.Reservation;
import org.changken.careapp.tools.Helper;
import org.changken.careapp.tools.Nav;
import org.changken.careapp.R;

public class ConfirmReservationActivity extends BaseNavActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        //String date=String.valueOf(intent.getIntExtra(""))


        super.onCreate(savedInstanceState);
        final TextView date = findViewById(R.id.textView3);
        date.setText(date.getText() + "abvfd");
        final TextView subject = findViewById(R.id.textView4);
        subject.setText(subject.getText() + "abvf");
        final TextView doctor = findViewById(R.id.textView5);
        doctor.setText(doctor.getText() + "abvfd");
        final TextView note = findViewById(R.id.textView6);
        note.setText(note.getText() + "abvfd");
        final Button confirm = findViewById(R.id.Button02);

        confirm.setOnClickListener((View v) -> {
            Reservation reservation = new Reservation();
            reservation.getDocTime_date();
            reservation.getDocTime_dayparts();
            reservation.getDoc_name();
        });
    }

    @Override
    protected Nav.MenuClickAction getMenuClickAction() {
        return new Nav.MenuClickAction() {
            @Override
            public void goMemberCenter() {
                startActivity(new Intent(ConfirmReservationActivity.this, MemberCenterActivity.class));
                finish();
            }

            @Override
            public void goPersonalInfo() {
                startActivity(new Intent(ConfirmReservationActivity.this, EditProfileActivity.class));
                finish();
            }

            @Override
            public void goReg() {
                startActivity(new Intent(ConfirmReservationActivity.this, ReservationActivity.class));
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
                startActivity(new Intent(ConfirmReservationActivity.this, TrafficInfoActivity.class));
                finish();
            }

            @Override
            public void goCheckIn() {
                //non
            }

            @Override
            public void goLogout() {
                Helper.logoutProcess(ConfirmReservationActivity.this);
                startActivity(new Intent(ConfirmReservationActivity.this, MainActivity.class));
                finish();
            }
        };
    }

    @Override
    protected int getMyOwnContentView() {
        return R.layout.activity_confirm_reservation;
    }
}
