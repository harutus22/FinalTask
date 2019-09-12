package com.example.finaltask.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable {
    private String title;
    @SerializedName("image")
    private String imageUrl;
    private double rating;
    private int releaseYear;
    private String[] genre;
    private int id;
    private static int autoIncrement = 0;

    public Movie(String title, String imageUrl, double rating, int releaseYear, String[] genre) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.genre = genre;
        id = ++autoIncrement;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getRating() {
        return rating;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String[] getGenre() {
        return genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(imageUrl);
        parcel.writeDouble(rating);
        parcel.writeInt(releaseYear);
        parcel.writeStringArray(genre);
        parcel.writeInt(id);
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel parcel) {
            return createMovieFromParcel(parcel);
        }

        @Override
        public Movie[] newArray(int i) {
            return new Movie[i];
        }
    };

    private static Movie createMovieFromParcel(Parcel parcel){
        String title = parcel.readString();
        String imageUrl = parcel.readString();
        double rating = parcel.readDouble();
        int releaseYear = parcel.readInt();
        String[] genre = parcel.createStringArray();
        int id = parcel.readInt();
        Movie movie = new Movie(title,imageUrl,rating,releaseYear,genre);
        movie.setId(id);
        return movie;
    }
}
