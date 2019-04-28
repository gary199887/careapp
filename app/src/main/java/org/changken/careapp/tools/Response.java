package org.changken.careapp.tools;

import com.google.gson.annotations.SerializedName;

/**
 * For CRU
 * */
public class Response<T> {
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
