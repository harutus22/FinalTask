package com.example.finaltask.ViewModel;

import com.example.finaltask.Model.Movie;
import com.example.finaltask.Repository.MovieRepository;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MovieViewModel extends ViewModel {
    private MutableLiveData<List<Movie>> movies;
    private MovieRepository movieRepository;

    public MovieViewModel(){
        movieRepository = MovieRepository.getInstance();
        movies = movieRepository.getMovies();
    }

    public MutableLiveData<List<Movie>> getLiveDataMovies(){
        return movies;
    }
}
