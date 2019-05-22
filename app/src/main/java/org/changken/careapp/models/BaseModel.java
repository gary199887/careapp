package org.changken.careapp.models;

import org.changken.careapp.datamodels.AirTableDeleteResponse;
import org.changken.careapp.datamodels.AirTableListResponse;
import org.changken.careapp.datamodels.AirTableResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public abstract class BaseModel<T> {
    //List
    abstract public void list(Map<String, String> queryMap, final ModelCallback<AirTableListResponse<T>> modelCallback);

    //Retrieve1
    abstract public void get(String recordId, final ModelCallback<AirTableResponse<T>> modelCallback);

    //Create
    abstract public void add(T data, final ModelCallback<AirTableResponse<T>> modelCallback);

    //Update
    abstract public void update(String recordId, T data, final ModelCallback<AirTableResponse<T>> modelCallback);

    //Delete
    abstract public void delete(String recordId, final ModelCallback<AirTableDeleteResponse> modelCallback);

    //執行請求
    protected <E> void executeRequest(Call<E> call, final ModelCallback<E> modelCallback) {
        //顯示進度
        modelCallback.onProgress();

        //執行請求
        call.enqueue(new Callback<E>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<E> call, Response<E> response) {
                //隱藏進度
                modelCallback.onProcessEnd();

                //判斷請求是否送成功!
                if (response.isSuccessful()) {
                    //執行請求成功的邏輯
                    modelCallback.onResponseSuccess(call, response);
                } else {
                    //執行請求失敗的邏輯
                    modelCallback.onResponseFailure(call, response);
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<E> call, Throwable t) {
                //隱藏進度
                modelCallback.onProcessEnd();

                //執行請求失敗的邏輯(如網路斷線，Json解析失敗等)
                modelCallback.onFailure(call, t);
            }
        });
    }
}
