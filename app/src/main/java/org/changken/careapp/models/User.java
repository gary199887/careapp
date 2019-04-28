package org.changken.careapp.models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("Id")
    private Integer id;

    @SerializedName("Name")
    private String name;

    @SerializedName("IdNumber")
    private String idNumber;

    @SerializedName("Password")
    private String password;

    @SerializedName("Email")
    private String email;

    @SerializedName("Phone")
    private String phone;

    @SerializedName("Address")
    private String address;

    @SerializedName("Birthday")
    private String birthday;

    public User(String name, String idNumber, String password, String email, String phone, String address, String birthday) {
        this.id = null;
        this.name = name;
        this.idNumber = idNumber;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.birthday = birthday;
    }

    public int getId(){
        return id;
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
}
