package org.changken.careapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.changken.careapp.tools.Helper;
import org.changken.careapp.tools.Nav;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegistrationRecordActivity extends BaseNavActivity {
    private Calendar calendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Spinner spinner = findViewById(R.id.spinner2);
        String[] c = new String[28];
        c[0] = df.format(calendar.getTime());
        for(int i = 1; i < 28; i++){
            calendar.add(Calendar.DATE, 1);
            c[i] = df.format(calendar.getTime());
        }
        String[] items = {c[0]+" ~ "+c[6],
                c[7]+" ~ "+c[13], c[14]+" ~ "+c[20], c[21]+" ~ "+c[27]};

        ArrayAdapter<String> catchDate = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        spinner.setAdapter(catchDate);


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
