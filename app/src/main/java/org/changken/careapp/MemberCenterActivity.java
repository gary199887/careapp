package org.changken.careapp;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import org.changken.careapp.tools.Nav;

public class MemberCenterActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        //初始化元件
        initial();
    }

    private void initial() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);

        //設定側邊攔
        try {
            Nav nav = new Nav(mDrawerLayout, mNavigationView, mToolbar, this);
            nav.setNav(new Nav.MenuGoTo() {
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
            }, R.layout.content_member_center);
        } catch (Exception e) {
            Log.e("MemeberCenterActivity", e.getMessage());
        }
    }

}
