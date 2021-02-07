package com.example.ezycommerce;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("book?nim=2201828373&nama=Benigi%20Garda%20Anvadin")
    Call<Post> getPosts();
}
