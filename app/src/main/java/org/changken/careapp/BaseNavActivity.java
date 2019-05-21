package org.changken.careapp;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.changken.careapp.tools.Nav;

public abstract class BaseNavActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        //初始化導航列元件
        initialNav();
    }

    /**
     * 初始化導航列元件
     */
    private void initialNav() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);

        //設定側邊攔
        Nav nav = new Nav(mDrawerLayout, mNavigationView, mToolbar, this);
        nav.setNav(getMenuClickAction(), getMyOwnContentView());
    }

    /**
     * 得到自訂義導航列點擊事件
     *
     * @return  Nav.MenuClickAction
     * */
    abstract protected Nav.MenuClickAction getMenuClickAction();

    /**
     * 得到自訂義內容樣式檔
     *
     * @return  int
     * */
    abstract protected int getMyOwnContentView();
}
