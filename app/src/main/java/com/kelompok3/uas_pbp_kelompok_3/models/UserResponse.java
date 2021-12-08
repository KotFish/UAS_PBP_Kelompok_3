package com.kelompok3.uas_pbp_kelompok_3.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {
    private String message;

    @SerializedName("user")
    private List<User> userList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<User> getRentalList() {
        return userList;
    }

    public void setUserList(List<Rental> rentalList) {
        this.userList = userList;
    }
}
