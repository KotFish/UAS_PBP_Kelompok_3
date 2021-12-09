package com.kelompok3.uas_pbp_kelompok_3.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RentalResponse2 {
    private String message;

    @SerializedName("data")
    private Rental rental;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }
}
