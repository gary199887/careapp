package org.changken.careapp.tools;

import com.google.gson.annotations.SerializedName;

/**
 * For D
 * */
public class DeleteResponse {
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
