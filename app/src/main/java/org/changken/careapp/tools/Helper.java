package org.changken.careapp.tools;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import org.changken.careapp.R;

public class Helper {
    /**
     * 產生一個進度對話視窗
     *
     * @param context Context
     * @param  title String
     * @return AlertDialog
     * */
    public static AlertDialog progressDialog(Context context, String title) {
        return new AlertDialog.Builder(context, R.style.AlertDialogTheme)
                .setTitle(title)
                .setView(R.layout.progress_bar)
                .create();
    }
}
