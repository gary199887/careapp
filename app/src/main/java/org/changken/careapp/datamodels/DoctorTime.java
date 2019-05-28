package org.changken.careapp.datamodels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DoctorTime {

    @SerializedName("docTime_room")
    private String docTime_room;

    @SerializedName("docTime_id")
    private int docTime_id;

    @SerializedName("docTime_date")
    private String docTime_date;

    @SerializedName("docTime_dayparts")
    private int docTime_dayparts;

    @SerializedName("docTime_maxAmount")
    private int docTime_maxAmount;

    @SerializedName("docTime_currentNum")
    private int docTime_currentNum;

    @SerializedName("docTime_resCount")
    private int docTime_resCount;

    @SerializedName("res_id")
    private List<String> res_id;

    @SerializedName("doc_id")
    private List<String> doc_id;

    @SerializedName("doc_name")
    private List<String> doc_name;

    @SerializedName("subDiv_id")
    private List<String> subDiv_id;

    public DoctorTime(String docTime_room, String docTime_date, int docTime_dayparts, int docTime_maxAmount, int docTime_currentNum, List<String> res_id, List<String> doc_id) {
        this.docTime_room = docTime_room;
        this.docTime_date = docTime_date;
        this.docTime_dayparts = docTime_dayparts;
        this.docTime_maxAmount = docTime_maxAmount;
        this.docTime_currentNum = docTime_currentNum;
        this.res_id = res_id;
        this.doc_id = doc_id;
    }

    public DoctorTime() {
        //不做事
    }

    public String getDocTime_room() {
        return docTime_room;
    }

    public void setDocTime_room(String docTime_room) {
        this.docTime_room = docTime_room;
    }

    public int getDocTime_id() {
        return docTime_id;
    }

    public String getDocTime_date() {
        return docTime_date;
    }

    public void setDocTime_date(String docTime_date) {
        this.docTime_date = docTime_date;
    }

    public int getDocTime_dayparts() {
        return docTime_dayparts;
    }

    public void setDocTime_dayparts(int docTime_dayparts) {
        this.docTime_dayparts = docTime_dayparts;
    }

    public int getDocTime_maxAmount() {
        return docTime_maxAmount;
    }

    public void setDocTime_maxAmount(int docTime_maxAmount) {
        this.docTime_maxAmount = docTime_maxAmount;
    }

    public int getDocTime_currentNum() {
        return docTime_currentNum;
    }

    public void setDocTime_currentNum(int docTime_currentNum) {
        this.docTime_currentNum = docTime_currentNum;
    }

    public int getDocTime_resCount() {
        return docTime_resCount;
    }

    public List<String> getRes_id() {
        return res_id;
    }

    public void setRes_id(List<String> res_id) {
        this.res_id = res_id;
    }

    public List<String> getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(List<String> doc_id) {
        this.doc_id = doc_id;
    }

    public List<String> getDoc_name() {
        return doc_name;
    }

    public List<String> getSubDiv_id() {
        return subDiv_id;
    }
}
