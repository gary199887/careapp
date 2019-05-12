package org.changken.careapp.datamodels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * For List
 * */
public class AirTableListResponse<T> {
    @SerializedName("records")
    protected List<AirTableResponse<T>> records;

    @SerializedName("offset")
    private String offset;

    public List<AirTableResponse<T>> getRecords() {
        return records;
    }

    public String getOffset() {
        return offset;
    }
}
