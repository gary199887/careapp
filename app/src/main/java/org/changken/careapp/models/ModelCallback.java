package org.changken.careapp.models;

import retrofit2.Call;
import retrofit2.Response;

public abstract class ModelCallback<T> {

    /**
     * 顯示進度欄
     *
     * 如果使用者需要的話，請使用者自行覆寫
     * */
    public void onProgress(){
        //不做任何事
    }

    /**
     * 關閉進度欄
     *
     * 如果使用者需要的話，請使用者自行覆寫
     * */
    public void onProcessEnd(){
        //不做任何事
    }

    abstract public void onResponseSuccess(Call<T> call, Response<T> response);
    abstract public void onResponseFailure(Call<T> call, Response<T> response);
    abstract public void onFailure(Call<T> call, Throwable t);
}
