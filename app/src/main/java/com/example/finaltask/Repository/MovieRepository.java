package com.example.finaltask.Repository;

import android.util.Log;

import com.example.finaltask.Model.Movie;
import com.example.finaltask.Remote.ApiManager;
import com.example.finaltask.StaticValues;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private ApiManager apiManager;
    private MutableLiveData<List<Movie>> movies;
    private static MovieRepository newRepository;

    public static MovieRepository getInstance(){
        if(newRepository == null){
            newRepository = new MovieRepository();
        }
        return newRepository;
    }

    private MovieRepository(){
        apiManager = ApiManager.getInstance();
        movies = new MutableLiveData<>();
        Call<List<Movie>> call = apiManager.getMovies().getMovies();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>>call, Response<List<Movie>> response) {
                if(response.isSuccessful()){
                    movies.setValue(response.body());
                    Log.d(StaticValues.TAG, "successful");
                } else {
                    Log.d(StaticValues.TAG, "not successful");
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Log.d(StaticValues.TAG, "failed " + t.getMessage());
            }
        });
    }

    public MutableLiveData<List<Movie>> getMovies() {
        return movies;
    }
}
