package com.kelompok3.uas_pbp_kelompok_3.api;

import com.kelompok3.uas_pbp_kelompok_3.models.Rental;
import com.kelompok3.uas_pbp_kelompok_3.models.RentalResponse;
import com.kelompok3.uas_pbp_kelompok_3.models.User;
import com.kelompok3.uas_pbp_kelompok_3.models.UserResponse;
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
    @Headers({
            "Accept: application/json",
            "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiMjE3OGI3YjJkNmZhMzZmNjlmN2ZmODY2YzI0MjcyYmY3MjRiNjc2YTI5ZmU3OTdiNDg4ZGMxODE1MDQxYmExYmYwMWIyMWNjNzlhMmYzNGMiLCJpYXQiOjE2MzkwNTY1NTMuMjg0MzI5LCJuYmYiOjE2MzkwNTY1NTMuMjg0MzMyLCJleHAiOjE2NzA1OTI1NTMuMjcyNjg5LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.Yqs3gBGAK762Jk9_YTvS9YGbPPngvvzA2gJneNm8B1wTVtfoeXlBH3zMQLexNRGERh6EgHYCrFkaELZ2wo3Fz2BxjfT_M24S9Vf8TwnQtm4H7jYFvC9Irh1_9WwXBngCRJR0Vb4ipSSCi2uhdiwJVg_58GBMMCOSUL-kPPKvaMd5aobPPdHPysdvAe3xDipxQSIikfXdPP68t5Ru1w7h75bEpufBnHs1uP6cQC_Z2a7G7eIW9uYwKUfAIKC8xhm2bG8j9W1ksNnwWW4WI4-6rq5fg19cM87qJ52GFKQUHzmEU9q5vF2ti5q3lt1SjwIa-ykWoFDas-lMUZHTq3eqXC34keMY5Gx2477ajk0m4KBK4tF6RAQjm4FbeUg-9f52LOXqjjm_gfbIVrl-HA-8T5NpcJGq_-4N0yNME2QC-WiZlCQbink8Yt5fAQC4N86dIo17olA1JXeuXqBxlTujxvM1-4_8SD4OIbR3CQKu1Gk-p0evP5QC_Zx3vfqiE2TJ4iUC2ulkZvUGrcMSCeTuXkTUwg9IQmxG2qfdNE1egdeu0qabscWSpSjAB833Ij5zamr6fUikDy89wZpE_vjg4eHeKhhyp839UtScymb-X84z5gf3dLWJQ4zYieqB6ue_zXMRX28-5Y-KLrPKX4FX0FIrwL7I_x0TqVBz1PM9JsA"
    })
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

    //Login
    @Headers({"Accept: application/json"})
    @POST("login")
    Call<UserResponse> login(@Body User user);

    //Register
    @Headers({"Accept: application/json"})
    @POST("register")
    Call<UserResponse> register(@Body User user);
}
