package com.example.finaltask.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.finaltask.Model.Movie;
import com.example.finaltask.R;
import com.example.finaltask.StaticValues;

public class DetailActivity extends AppCompatActivity {
    private ImageView image;
    private TextView title;
    private TextView rating;
    private TextView releaseYear;
    private TextView genre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        findViews();

        setValues();
    }

    private void setValues() {
        Movie movie = getIntent().getParcelableExtra(StaticValues.EXTRA_MOVIE);

        Glide.with(this).load(movie.getImageUrl()).centerCrop().into(image);
        title.append("Title: " + movie.getTitle());
        rating.append("Rating: " + movie.getRating());
        releaseYear.append("Release Year: " + movie.getReleaseYear());
        genre.append("Genres: " + movie.getGenre().toString());
    }

    private void findViews() {
        image = findViewById(R.id.detailImage);
        title = findViewById(R.id.detailTitle);
        rating = findViewById(R.id.detailRating);
        releaseYear = findViewById(R.id.detailReleaseYear);
        genre = findViewById(R.id.detailGenre);
    }
}
