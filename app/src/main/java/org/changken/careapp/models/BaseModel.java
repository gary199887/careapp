package org.changken.careapp.models;

import org.changken.careapp.datamodels.AirTableDeleteResponse;
import org.changken.careapp.datamodels.AirTableListResponse;
import org.changken.careapp.datamodels.AirTableResponse;

import java.util.Map;

abstract public class BaseModel<T> {
    //List
    abstract public AirTableListResponse<T> list(Map<String, String> queryMap);

    //Retrieve1
    abstract public AirTableResponse<T> get(String recordId);

    //Create
    abstract public AirTableResponse<T> add(T data);

    //Update
    abstract public AirTableResponse<T> update(String recordId, T data);

    //Delete
    abstract public AirTableDeleteResponse delete(String recordId);
}
