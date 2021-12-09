package com.kelompok3.uas_pbp_kelompok_3.models;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;


public class UserResponse2 {
    private String message;

    @SerializedName("user")
    private User user;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
