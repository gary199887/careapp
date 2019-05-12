package org.changken.careapp.datamodels;

import com.google.gson.annotations.SerializedName;

/**
 * For CRU
 * */
public class AirTableResponse<T> {
    @SerializedName("id")
    private String id;

    @SerializedName("fields")
    private T fields;

    @SerializedName("createdTime")
    private String createdTime;

    public String getId() {
        return id;
    }

    public T getFields() {
        return fields;
    }

    public String getCreatedTime() {
        return createdTime;
    }
}
