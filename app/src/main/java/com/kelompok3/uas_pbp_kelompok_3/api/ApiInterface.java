package com.kelompok3.uas_pbp_kelompok_3.api;

import com.kelompok3.uas_pbp_kelompok_3.models.Rental;
import com.kelompok3.uas_pbp_kelompok_3.models.RentalResponse;
import com.kelompok3.uas_pbp_kelompok_3.models.Wisata;
import com.kelompok3.uas_pbp_kelompok_3.models.WisataResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {
    //Wisata
    @Headers({"Accept: application/json"})
    @GET("wisata")
    Call<WisataResponse> getAllWisata();

    @Headers({"Accept: application/json"})
    @GET("wisata/{id}")
    Call<WisataResponse> getWisataById(@Path("id") long id);

    @Headers({"Accept: application/json"})
    @POST("wisata")
    Call<WisataResponse> createWisata(@Body Wisata wisata);

    @Headers({"Accept: application/json"})
    @PUT("wisata/{id}")
    Call<WisataResponse> updateWisata(@Path("id") long id,
                                            @Body Wisata wisata);

    @Headers({"Accept: application/json"})
    @DELETE("wisata/{id}")
    Call<WisataResponse> deleteWisata(@Path("id") long id);

    //Rental
    @Headers({"Accept: application/json"})
    @GET("rental")
    Call<RentalResponse> getAllRental();

    @Headers({"Accept: application/json"})
    @GET("rental/{id}")
    Call<RentalResponse> getRentalById(@Path("id") long id);

    @Headers({"Accept: application/json"})
    @POST("rental")
    Call<RentalResponse> createRental(@Body Rental rental);

    @Headers({"Accept: application/json"})
    @PUT("rental/{id}")
    Call<RentalResponse> updateRental(@Path("id") long id,
                                      @Body Rental rental);

    @Headers({"Accept: application/json"})
    @DELETE("rental/{id}")
    Call<RentalResponse> deleteRental(@Path("id") long id);
}
