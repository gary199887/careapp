package org.changken.careapp;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import org.changken.careapp.tools.Nav;

public class ReservationActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        initial();

        try {

            Nav nav = new Nav(drawerLayout, navigationView, toolbar, this);
            nav.setNav(new Nav.MenuGoTo() {
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
            }, R.layout.content_reservation);
        } catch (Exception e) {
            Log.e("ReservationActivity", e.getMessage());
        }
    }

    private void initial() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }
}
