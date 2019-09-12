package com.example.finaltask.Remote;

import com.example.finaltask.Model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieRemoteService {

    @GET("movies.json")
    Call<List<Movie>> getMovies();
}
