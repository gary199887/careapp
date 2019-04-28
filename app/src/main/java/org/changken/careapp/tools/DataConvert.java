package org.changken.careapp.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;

public abstract class DataConvert<T> {

    protected Gson gson;

    public DataConvert(){
        gson = new GsonBuilder().setDateFormat(DateFormat.LONG).create();
    }

    /**
     *  CU
     * */
    public String setRequest(T data){
        Request<T> request = new Request<>(data);
        return gson.toJson(request);
    }

    /**
     * RCU
     * */
    abstract public Response<T> getResponse(String data);

    /**
     * List
     * */
    abstract public ListResponse<T> getListResponse(String data);

    public DeleteResponse getDeleteResponse(String data){
        return gson.fromJson(data, DeleteResponse.class);
    }

    protected String convertListResponse(String data){
        if(!data.contains("offset"))
            data = data.substring(0, data.lastIndexOf('}')) + ",\"offset\":\"None\"}";
        return data;
    }
}
