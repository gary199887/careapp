package org.changken.careapp.tools;

import android.content.Context;

import org.changken.careapp.contract.ResourceService;

public class ResourceServiceImp implements ResourceService {
    private Context mContext;

    public ResourceServiceImp(Context context) {
        mContext = context;
    }

    @Override
    public String getString(int resId) {
        return mContext.getString(resId);
    }
}
