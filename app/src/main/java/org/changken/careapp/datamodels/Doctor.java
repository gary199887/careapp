package org.changken.careapp.datamodels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Doctor {

    @SerializedName("doc_id")
    private int doc_id;

    @SerializedName("doc_name")
    private String doc_name;

    @SerializedName("docTime_id")
    private List<String> docTime_id;

    @SerializedName("subDiv_id")
    private List<String> subDiv_id;

    @SerializedName("subDiv_name")
    private List<String> subDiv_name;

    public Doctor(String doc_name, List<String> subDiv_id) {
        this.doc_name = doc_name;
        this.subDiv_id = subDiv_id;
    }

    public Doctor() {
        //不做事
    }

    public int getDoc_id() {
        return doc_id;
    }

    public String getDoc_name() {
        return doc_name;
    }

    public void setDoc_name(String doc_name) {
        this.doc_name = doc_name;
    }

    public List<String> getDocTime_id() {
        return docTime_id;
    }

    public List<String> getSubDiv_id() {
        return subDiv_id;
    }

    public void setSubDiv_id(List<String> subDiv_id) {
        this.subDiv_id = subDiv_id;
    }

    public List<String> getSubDiv_name() {
        return subDiv_name;
    }
}
