package org.changken.careapp.tools;

import com.google.gson.reflect.TypeToken;

import org.changken.careapp.datamodels.AirTableResponse;
import org.changken.careapp.datamodels.User;
import org.changken.careapp.datamodels.AirTableListResponse;

import java.lang.reflect.Type;

/**
 * @deprecated
 * */
public class UserConvert extends DataConvert<User> {
    public AirTableResponse<User> getResponse(String data){
        Type type = new TypeToken<AirTableResponse<User>>(){}.getType();
        return gson.fromJson(data, type);
    }

    public AirTableListResponse<User> getListResponse(String data){
        Type type = new TypeToken<AirTableListResponse<User>>(){}.getType();
        return gson.fromJson(convertListResponse(data), type);
    }
}
