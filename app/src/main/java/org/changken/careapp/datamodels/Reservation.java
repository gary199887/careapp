package org.changken.careapp.datamodels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Reservation {

    @SerializedName("res_num")
    private int res_num;

    @SerializedName("res_id")
    private Integer res_id = null;

    @SerializedName("res_status")
    private int res_status;

    @SerializedName("user_id")
    private List<String> user_id;

    @SerializedName("docTime_id")
    private List<String> docTime_id;

    @SerializedName("docTime_dayparts")
    private List<Integer> docTime_dayparts;

    @SerializedName("docTime_date")
    private List<String> docTime_date;

    @SerializedName("doc_id")
    private List<String> doc_id;

    @SerializedName("doc_name")
    private List<String> doc_name;

    @SerializedName("docTime_room")
    private List<String> docTime_room;

    @SerializedName("docTime_currentNum")
    private List<Integer> docTime_currentNum;

    @SerializedName("docTime_daypartAndDate")
    private String docTime_daypartAndDate;

    public Reservation(int res_num, int res_status, List<String> user_id, List<String> docTime_id) {
        this.res_num = res_num;
        this.res_status = res_status;
        this.user_id = user_id;
        this.docTime_id = docTime_id;
    }

    public Reservation() {
        //不做事
    }

    public int getRes_num() {
        return res_num;
    }

    public void setRes_num(int res_num) {
        this.res_num = res_num;
    }

    public int getRes_id() {
        return res_id;
    }

    public int getRes_status() {
        return res_status;
    }

    public void setRes_status(int res_status) {
        this.res_status = res_status;
    }

    public List<String> getUser_id() {
        return user_id;
    }

    public void setUser_id(List<String> user_id) {
        this.user_id = user_id;
    }

    public List<String> getDocTime_id() {
        return docTime_id;
    }

    public void setDocTime_id(List<String> docTime_id) {
        this.docTime_id = docTime_id;
    }

    public List<Integer> getDocTime_dayparts() {
        return docTime_dayparts;
    }

    public List<String> getDocTime_date() {
        return docTime_date;
    }

    public List<String> getDoc_id() {
        return doc_id;
    }

    public List<String> getDoc_name() {
        return doc_name;
    }

    public List<String> getDocTime_room() {
        return docTime_room;
    }

    public List<Integer> getDocTime_currentNum() {
        return docTime_currentNum;
    }

    public String getDocTime_daypartAndDate() {
        return docTime_daypartAndDate;
    }
}
