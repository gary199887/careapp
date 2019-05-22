package org.changken.careapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.changken.careapp.tools.Helper;
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

            }

            @Override
            public void goCheckIn() {

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
