package org.changken.careapp.datamodels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {
    @SerializedName("user_name")
    private String name;

    @SerializedName("user_id")
    private String idNumber;

    @SerializedName("user_password")
    private String password;

    @SerializedName("user_email")
    private String email;

    @SerializedName("user_phone")
    private String phone;

    @SerializedName("user_address")
    private String address;

    @SerializedName("user_birthday")
    private String birthday;

    @SerializedName("res_id")
    private List<String> resId;

    public User(String name, String idNumber, String password, String email, String phone, String address, String birthday) {
        this.name = name;
        this.idNumber = idNumber;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.birthday = birthday;
        resId = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public List<String> getResId() {
        return resId;
    }

    public void setResId(List<String> resId) {
        this.resId = resId;
    }
}
