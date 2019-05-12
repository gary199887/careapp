package org.changken.careapp.tools;

import org.changken.careapp.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private Retrofit mRetrofit;

    private RetrofitManager(){
        //使用Retrofit套件
        mRetrofit = new Retrofit.Builder()
                            .baseUrl(BuildConfig.AIRTABLE_API_URL + BuildConfig.AIRTABLE_BASE_ID + "/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
    }

    /**
     * getInstance
     * 獲取RetrofitManager物件，以singleton的方式
     *
     * @return RetrofitManager
     * */
    public static RetrofitManager getInstance(){
        return Holder.INSTANCE;
    }

    /**
     * getRetrofit
     * 獲取Retrofit物件
     *
     * @return Retrofit
     * */
    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    /**
     * 懶惰載入
     * */
    private static class Holder{
        private static final RetrofitManager INSTANCE = new RetrofitManager();
    }
}
