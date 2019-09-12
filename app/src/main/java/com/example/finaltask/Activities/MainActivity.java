package com.example.finaltask.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.example.finaltask.BroadcastReceiver.ConnectionStatusReceiver;
import com.example.finaltask.Model.Movie;
import com.example.finaltask.R;
import com.example.finaltask.RecyclerView.MovieRecyclerAdapter;
import com.example.finaltask.StaticValues;
import com.example.finaltask.Utils.NetworkUtil;
import com.example.finaltask.ViewModel.MovieViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieRecyclerAdapter.OnMovieItemClicked {
    private MovieRecyclerAdapter adapter;
    private ConnectionStatusReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!NetworkUtil.getConnectivityStatusString(this)){
            Toast.makeText(this,
                    StaticValues.ASK_CONNECTION, Toast.LENGTH_LONG).show();
            finish();
        }

        registerReceiver();

        createRecyclerView();

        createViewModel();
    }

    private void createRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new MovieRecyclerAdapter();
        adapter.setOnMovieItemClicked(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void createViewModel(){
        MovieViewModel movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getLiveDataMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                adapter.submitList(movies);
            }
        });
    }

    @Override
    public void onItemClicked(Movie movie) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(StaticValues.EXTRA_MOVIE, movie);
        startActivity(intent);
    }

    private void registerReceiver(){
        receiver = new ConnectionStatusReceiver();
        registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
