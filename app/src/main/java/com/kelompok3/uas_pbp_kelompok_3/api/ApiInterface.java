package com.kelompok3.uas_pbp_kelompok_3.api;

import com.kelompok3.uas_pbp_kelompok_3.models.Rental;
import com.kelompok3.uas_pbp_kelompok_3.models.RentalResponse;
import com.kelompok3.uas_pbp_kelompok_3.models.RentalResponse2;
import com.kelompok3.uas_pbp_kelompok_3.models.User;
import com.kelompok3.uas_pbp_kelompok_3.models.UserResponse;
import com.kelompok3.uas_pbp_kelompok_3.models.UserResponse2;
import com.kelompok3.uas_pbp_kelompok_3.models.Wisata;
import com.kelompok3.uas_pbp_kelompok_3.models.WisataResponse;
import com.kelompok3.uas_pbp_kelompok_3.models.WisataResponse2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    Call<WisataResponse2> createWisata(@Body Wisata wisata);

    @Headers({"Accept: application/json"})
    @PUT("wisata/{id}")
    Call<WisataResponse2> updateWisata(@Path("id") long id,
                                            @Body Wisata wisata);

    @Headers({"Accept: application/json"})
    @DELETE("wisata/{id}")
    Call<WisataResponse2> deleteWisata(@Path("id") long id);

    //Rental
    @Headers({
            "Accept: application/json"
    })
    @GET("rental")
    Call<RentalResponse> getAllRental(@Header("Authorization") String token);

    @Headers({"Accept: application/json"})
    @GET("rental/{id}")
    Call<RentalResponse> getRentalById(@Path("id") long id);

    @Headers({"Accept: application/json"})
    @POST("rental")
    Call<RentalResponse2> createRental(@Body Rental rental);

    @Headers({"Accept: application/json"})
    @PUT("rental/{id}")
    Call<RentalResponse2> updateRental(@Path("id") long id,
                                      @Body Rental rental);

    @Headers({"Accept: application/json"})
    @DELETE("rental/{id}")
    Call<RentalResponse2> deleteRental(@Path("id") long id);

    //Login
    @Headers({"Accept: application/json"})
    @POST("login")
    Call<UserResponse> login(@Body User user);

    //Register
    @Headers({"Accept: application/json"})
    @POST("register")
    Call<UserResponse2> register(@Body User user);
}
