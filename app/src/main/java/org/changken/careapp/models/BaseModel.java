package org.changken.careapp.models;

import org.changken.careapp.datamodels.AirTableDeleteResponse;
import org.changken.careapp.datamodels.AirTableListResponse;
import org.changken.careapp.datamodels.AirTableResponse;

import java.util.Map;

import retrofit2.Call;

public interface BaseModel<T> {
    //List
    Call<AirTableListResponse<T>> list(Map<String, String> queryMap);

    //Retrieve1
    Call<AirTableResponse<T>> get(String recordId);

    //Create
    Call<AirTableResponse<T>> add(T data);

    //Update
    Call<AirTableResponse<T>> update(String recordId, T data);

    //Delete
    Call<AirTableDeleteResponse> delete(String recordId);
}
