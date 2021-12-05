package com.kelompok3.uas_pbp_kelompok_3.models;

import com.google.gson.annotations.SerializedName;

public class Wisata {

    @SerializedName("nama_wisata")
    private String nama_wisata;

    @SerializedName("lokasi")
    private String lokasi;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("url_gambar")
    private String url_gambar;

    @SerializedName("harga")
    private int harga;

    public Wisata(String nama_wisata, String lokasi, String deskripsi, String url_gambar, int harga) {
        this.nama_wisata = nama_wisata;
        this.lokasi = lokasi;
        this.deskripsi = deskripsi;
        this.url_gambar = url_gambar;
        this.harga = harga;
    }

    public String getNama_wisata() {
        return nama_wisata;
    }

    public void setNama_wisata(String nama_wisata) {
        this.nama_wisata = nama_wisata;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getUrl_gambar() {
        return url_gambar;
    }

    public void setUrl_gambar(String url_gambar) {
        this.url_gambar = url_gambar;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
}
