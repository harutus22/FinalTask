package com.example.finaltask.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.finaltask.Model.Movie;
import com.example.finaltask.R;
import com.example.finaltask.RecyclerView.MovieRecyclerAdapter;
import com.example.finaltask.StaticValues;
import com.example.finaltask.ViewModel.MovieViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieRecyclerAdapter.OnMovieItemClicked {
    private RecyclerView recyclerView;
    private MovieRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createRecyclerView();

        MovieViewModel movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getLiveDataMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                adapter.submitList(movies);
            }
        });
    }

    private void createRecyclerView(){
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new MovieRecyclerAdapter();
        adapter.setOnMovieItemClicked(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(Movie movie) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(StaticValues.EXTRA_MOVIE, movie);
        startActivity(intent);
    }
}
