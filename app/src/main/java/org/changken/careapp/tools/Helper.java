package org.changken.careapp.tools;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import org.changken.careapp.R;

public class Helper {
    /**
     * 產生一個進度對話視窗
     *
     * @param context Context
     * @param title   String
     * @return AlertDialog
     */
    public static AlertDialog progressDialog(Context context, String title) {
        return new AlertDialog.Builder(context, R.style.AlertDialogTheme)
                .setTitle(title)
                .setView(R.layout.progress_bar)
                .create();
    }

    /**
     * isLogin 檢查是否有登入
     *
     * @param  context Context
     * @return boolean
     * */
    public static boolean isLogin(Context context) {
        return context.getSharedPreferences("careapp", Context.MODE_PRIVATE)
                .getBoolean("isLogin", false);
    }

    /**
     * loginProcess 登入流程
     *
     * @param  context Context
     * @param  user_id String
     * @param user_record_is String
     * */
    public static void loginProcess(Context context, String user_id, String user_record_is) {
        context.getSharedPreferences("careapp", Context.MODE_PRIVATE)
                .edit()
                .putBoolean("isLogin", true)
                .putString("user_id", user_id)
                .putString("user_record_id", user_record_is)
                .apply();
    }

    /**
     * logoutProcess 登出流程
     *
     * @param  context Context
     * */
    public static void logoutProcess(Context context) {
        context.getSharedPreferences("careapp", Context.MODE_PRIVATE)
                .edit()
                .putBoolean("isLogin", false)
                .putString("user_id", "")
                .putString("user_record_id", "")
                .apply();
    }
}
