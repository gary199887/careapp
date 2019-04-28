package org.changken.careapp.tools;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * For List
 * */
public class ListResponse<T> {
    @SerializedName("records")
    protected List<Response<T>> records;

    @SerializedName("offset")
    private String offset;

    public List<Response<T>> getRecords() {
        return records;
    }

    public String getOffset() {
        return offset;
    }
}
