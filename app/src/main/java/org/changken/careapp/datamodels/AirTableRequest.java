package org.changken.careapp.datamodels;

import com.google.gson.annotations.SerializedName;

/**
 * For CU
 * */
public class AirTableRequest<T> {
    @SerializedName("fields")
    private T fields;

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
