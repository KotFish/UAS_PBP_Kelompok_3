package com.kelompok3.uas_pbp_kelompok_3.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WisataResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("datas")
    private List<Wisata> wisataList;

    @SerializedName("data")
    private Wisata wisata;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Wisata> getWisataList() {
        return wisataList;
    }

    public void setWisataList(List<Wisata> wisataList) {
        this.wisataList = wisataList;
    }

    public Wisata getWisata() {
        return wisata;
    }

    public void setWisata(Wisata wisata) {
        this.wisata = wisata;
    }
}
