package com.example.android.chucknorrisapp;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface GitHubClient {

    @GET("/jokes/random")
    Call<GitHubRepo> reposForUser(@Query("category") String category);

    @GET("/jokes/search")
    Call<List<GitHubRepo>> getJokesBySearchString(@Query("query") String search);
}