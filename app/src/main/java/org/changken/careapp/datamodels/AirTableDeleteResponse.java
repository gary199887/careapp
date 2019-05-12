package org.changken.careapp.datamodels;

import com.google.gson.annotations.SerializedName;

/**
 * For D
 * */
public class AirTableDeleteResponse {
    @SerializedName("id")
    private String id;

    @SerializedName("deleted")
    private boolean deleted;

    public String getId() {
        return id;
    }

    public boolean isDeleted() {
        return deleted;
    }
}
