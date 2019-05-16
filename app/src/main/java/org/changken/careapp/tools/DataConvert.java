package org.changken.careapp.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.changken.careapp.datamodels.AirTableDeleteResponse;
import org.changken.careapp.datamodels.AirTableListResponse;
import org.changken.careapp.datamodels.AirTableRequest;
import org.changken.careapp.datamodels.AirTableResponse;

import java.text.DateFormat;

/**
 * @deprecated
 * */
public abstract class DataConvert<T> {

    protected Gson gson;

    public DataConvert(){
        gson = new GsonBuilder().setDateFormat(DateFormat.LONG).create();
    }

    /**
     *  CU
     * */
    public String setRequest(T data){
        AirTableRequest<T> request = new AirTableRequest<>(data);
        return gson.toJson(request);
    }

    /**
     * RCU
     * */
    abstract public AirTableResponse<T> getResponse(String data);

    /**
     * List
     * */
    abstract public AirTableListResponse<T> getListResponse(String data);

    public AirTableDeleteResponse getDeleteResponse(String data){
        return gson.fromJson(data, AirTableDeleteResponse.class);
    }

    protected String convertListResponse(String data){
        if(!data.contains("offset"))
            data = data.substring(0, data.lastIndexOf('}')) + ",\"offset\":\"None\"}";
        return data;
    }
}
