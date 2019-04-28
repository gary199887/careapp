package org.changken.careapp.tools;

import com.google.gson.annotations.SerializedName;

/**
 * For CU
 * */
public class Request<T> {
    @SerializedName("fields")
    private T fields;

    public Request(T fields) {
        this.fields = fields;
    }

    public T getFields() {
        return fields;
    }

    public void setFields(T fields) {
        this.fields = fields;
    }
}
