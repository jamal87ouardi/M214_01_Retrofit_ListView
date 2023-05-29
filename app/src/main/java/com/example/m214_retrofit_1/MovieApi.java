package com.example.m214_retrofit_1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MovieApi {

    @GET("RestAPi_Movie/readAll.php")
    Call<List<Movie>> getMovies();

    @FormUrlEncoded
    @POST("create.php")
    Call<Movie> createMovie(
            @Field("id") int id,
            @Field("name") String name,
            @Field("image") String image
    );

}
