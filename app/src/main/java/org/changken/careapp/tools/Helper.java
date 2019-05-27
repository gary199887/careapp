package org.changken.careapp.tools;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import org.changken.careapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {
    /**
     * createProgressDialog
     * 產生一個進度對話視窗
     *
     * @param context Context
     * @param title   String
     * @return AlertDialog
     */
    public static AlertDialog createProgressDialog(Context context, String title) {
        return new AlertDialog.Builder(context, R.style.AlertDialogTheme)
                .setTitle(title)
                .setView(R.layout.progress_bar)
                .create();
    }

    /**
     * isLogin
     * 檢查是否有登入
     *
     * @param context Context
     * @return boolean
     */
    public static boolean isLogin(Context context) {
        return context.getSharedPreferences("careapp", Context.MODE_PRIVATE)
                .getBoolean("isLogin", false);
    }

    /**
     * getUserId
     * 取得使用者身分證
     *
     * @param context Context
     * @return String
     */
    public static String getUserId(Context context) {
        return context.getSharedPreferences("careapp", Context.MODE_PRIVATE)
                .getString("user_id", "");
    }

    /**
     * getUserRecordId
     * 取得使用者在airtable的唯一識別碼
     *
     * @param context Context
     * @return String
     */
    public static String getUserRecordId(Context context) {
        return context.getSharedPreferences("careapp", Context.MODE_PRIVATE)
                .getString("user_record_id", "");
    }

    /**
     * loginProcess 登入流程
     *
     * @param context        Context
     * @param user_id        String
     * @param user_record_id String
     */
    public static void loginProcess(Context context, String user_id, String user_record_id) {
        context.getSharedPreferences("careapp", Context.MODE_PRIVATE)
                .edit()
                .putBoolean("isLogin", true)
                .putString("user_id", user_id)
                .putString("user_record_id", user_record_id)
                .apply();
    }

    /**
     * logoutProcess 登出流程
     *
     * @param context Context
     */
    public static void logoutProcess(Context context) {
        context.getSharedPreferences("careapp", Context.MODE_PRIVATE)
                .edit()
                .putBoolean("isLogin", false)
                .putString("user_id", "")
                .putString("user_record_id", "")
                .apply();
    }

    /**
     * parseDateToDay
     * 轉換西元至星期
     *
     * @param dateString String
     * @return String
     * @throws ParseException 無法將字串轉換成Date
     */
    public static String parseDateToDay(String dateString) throws ParseException {
        SimpleDateFormat dateStringFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = dateStringFormat.parse(dateString);

        SimpleDateFormat dateToDayFormat = new SimpleDateFormat("u");

        return dateToDayFormat.format(date);
    }
}
