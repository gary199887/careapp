package org.changken.careapp.datamodels;

import com.google.gson.annotations.SerializedName;

/**
 * For CU
 * */
public class AirTableRequest<T> {
    @SerializedName("fields")
    private T fields;

    @SerializedName("typecast")
    private boolean typecast = true;

    public AirTableRequest(T fields) {
        this.fields = fields;
    }

    public T getFields() {
        return fields;
    }

    public void setFields(T fields) {
        this.fields = fields;
    }
}
