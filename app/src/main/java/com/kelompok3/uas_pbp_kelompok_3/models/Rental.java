package com.kelompok3.uas_pbp_kelompok_3.models;

import com.google.gson.annotations.SerializedName;

public class Rental {

    private Long id;

    @SerializedName("no_plat")
    private String no_plat;

    @SerializedName("nama_kendaraan")
    private String nama_kendaraan;

    @SerializedName("jenis_kendaraan")
    private String jenis_kendaraan;

    @SerializedName("biaya_penyewaan")
    private String biaya_penyewaan;

    @SerializedName("status")
    private Integer status;

    public Rental(String no_plat, String nama_kendaraan, String jenis_kendaraan, String biaya_penyewaan, Integer status) {
        this.no_plat = no_plat;
        this.nama_kendaraan = nama_kendaraan;
        this.jenis_kendaraan = jenis_kendaraan;
        this.biaya_penyewaan = biaya_penyewaan;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNo_plat() {
        return no_plat;
    }

    public void setNo_plat(String no_plat) {
        this.no_plat = no_plat;
    }

    public String getNama_kendaraan() {
        return nama_kendaraan;
    }

    public void setNama_kendaraan(String nama_kendaraan) {
        this.nama_kendaraan = nama_kendaraan;
    }

    public String getJenis_kendaraan() {
        return jenis_kendaraan;
    }

    public void setJenis_kendaraan(String jenis_kendaraan) {
        this.jenis_kendaraan = jenis_kendaraan;
    }

    public String getBiaya_penyewaan() {
        return biaya_penyewaan;
    }

    public void setBiaya_penyewaan(String biaya_penyewaan) {
        this.biaya_penyewaan = biaya_penyewaan;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
