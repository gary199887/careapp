package org.changken.careapp.tools;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.changken.careapp.BaseNavActivity;
import org.changken.careapp.R;

/**
 * 側邊攔模組
 */
public class Nav {
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;

    private Context mContext;
    private BaseNavActivity mActivity;

    public Nav(DrawerLayout drawerLayout, NavigationView navigationView, Toolbar toolbar, BaseNavActivity activity) {
        mDrawerLayout = drawerLayout;
        mNavigationView = navigationView;
        mToolbar = toolbar;

        //取得Activity
        mActivity = activity;
        //取得context
        mContext = (Context) activity;
    }

    /**
     * 設定自訂義內容
     *
     * @param resId int
     */
    private void setMyOwnContentView(int resId) {
        //解析CoordinatorLayout
        CoordinatorLayout coordinatorLayout = mDrawerLayout.findViewById(R.id.coordinatorLayout);
        //動態解析自己的外觀檔
        View contentView = mActivity.getLayoutInflater().inflate(resId, coordinatorLayout, false);
        //把它綁上去
        coordinatorLayout.addView(contentView);
    }

    /**
     * 設定自訂義工具列
     */
    private void setMyOwnToolbar() {
        //設置Toolbar
        mActivity.setSupportActionBar(mToolbar);
    }

    /**
     * 設定自訂義導航項目點擊事件
     *
     * @param menuClickAction MenuClickAction
     */
    private void setMyOwnNavItemSelectEvent(final MenuClickAction menuClickAction) {
        //為navigatin_view設置點擊事件
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // 點選時收起選單
                mDrawerLayout.closeDrawer(GravityCompat.START);
                // 取得選項id
                int id = item.getItemId();
                // 依照id判斷點了哪個項目並做相應事件
                switch (id) {
                    case R.id.member_center_item:
                        // 按下「會員中心」要做的事
                        menuClickAction.goMemberCenter();
                        Toast.makeText(mContext, "會員中心", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.personal_info_item:
                        // 按下「個人資料」要做的事
                        menuClickAction.goPersonalInfo();
                        Toast.makeText(mContext, "個人資料", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.reg_item:
                        menuClickAction.goReg();
                        Toast.makeText(mContext, "門診預約及掛號", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.query_cancel_item:
                        menuClickAction.goQueryCancel();
                        Toast.makeText(mContext, "查詢門診進度及取消預約", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.reg_record_item:
                        menuClickAction.goRegRecord();
                        Toast.makeText(mContext, "掛號紀錄", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.traffic_guide_item:
                        menuClickAction.goTrafficGuide();
                        Toast.makeText(mContext, "交通指引", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.check_in_item:
                        menuClickAction.goCheckIn();
                        Toast.makeText(mContext, "報到", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.logout_item:
                        //回到首頁
                        menuClickAction.goLogout();
                        Toast.makeText(mContext, "登出", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
    }

    /**
     * 將導航列與工具列整合
     */
    private void integrateNavWithToolbar() {
        //將drawerLayout和toolbar整合，會出現「三」按鈕
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                mActivity, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.addDrawerListener(toggle);

        toggle.syncState();
    }

    /**
     * 設定側邊攔
     *
     * @param menuClickAction MenuClickAction 按下側邊欄按鈕的邏輯(自訂義邏輯)
     * @param resId           int 自訂的模板(e.g. R.layout.content_自訂義名稱)
     */
    public void setNav(MenuClickAction menuClickAction, int resId) {
        //設定自訂義內容
        setMyOwnContentView(resId);

        //設定自訂義工具列
        setMyOwnToolbar();

        //設定自訂義導航項目點擊事件
        setMyOwnNavItemSelectEvent(menuClickAction);

        //將導航列和工具列整合起來
        integrateNavWithToolbar();
    }

    public interface MenuClickAction {
        /**
         * 按下會員中心項目的邏輯
         */
        void goMemberCenter();

        /**
         * 按下個人資料項目的邏輯
         */
        void goPersonalInfo();

        /**
         * 按下門診預約及掛號項目的邏輯
         */
        void goReg();

        /**
         * 按下查詢門診進度及取消預約項目的邏輯
         */
        void goQueryCancel();

        /**
         * 按下掛號紀錄項目的邏輯
         */
        void goRegRecord();

        /**
         * 按下交通指引項目的邏輯
         */
        void goTrafficGuide();

        /**
         * 按下報到項目的邏輯
         */
        void goCheckIn();

        /**
         * 按下登出項目的邏輯
         */
        void goLogout();
    }
}
