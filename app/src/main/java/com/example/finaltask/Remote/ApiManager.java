package com.example.finaltask.Remote;

import com.example.finaltask.StaticValues;
import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private Retrofit retrofit;
    private static ApiManager apiManager;

    private ApiManager(){
        retrofit = new Retrofit.Builder().
                baseUrl(StaticValues.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static ApiManager getInstance(){
        if(apiManager == null){
            apiManager = new ApiManager();
        }
        return apiManager;
    }

    public MovieRemoteService getMovies(){
        return retrofit.create(MovieRemoteService.class);
    }
}
