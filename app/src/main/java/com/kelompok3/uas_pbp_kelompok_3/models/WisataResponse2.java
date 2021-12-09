package com.kelompok3.uas_pbp_kelompok_3.models;

import com.google.gson.annotations.SerializedName;

public class WisataResponse2 {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private Wisata wisata;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Wisata getWisata() {
        return wisata;
    }

    public void setWisata(Wisata wisata) {
        this.wisata = wisata;
    }
}
