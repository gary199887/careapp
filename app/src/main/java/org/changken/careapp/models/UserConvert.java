package org.changken.careapp.models;

import com.google.gson.reflect.TypeToken;

import org.changken.careapp.tools.DataConvert;
import org.changken.careapp.tools.ListResponse;
import org.changken.careapp.tools.Response;

import java.lang.reflect.Type;

public class UserConvert extends DataConvert<User> {
    public Response<User> getResponse(String data){
        Type type = new TypeToken<Response<User>>(){}.getType();
        return gson.fromJson(data, type);
    }

    public ListResponse<User> getListResponse(String data){
        Type type = new TypeToken<ListResponse<User>>(){}.getType();
        return gson.fromJson(convertListResponse(data), type);
    }
}
