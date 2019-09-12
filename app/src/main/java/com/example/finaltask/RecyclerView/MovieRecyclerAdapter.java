package com.example.finaltask.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.finaltask.Model.Movie;
import com.example.finaltask.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class MovieRecyclerAdapter extends ListAdapter<Movie, MovieRecyclerAdapter.MovieViewHolder> {
    private OnMovieItemClicked onMovieItemClicked;

    public MovieRecyclerAdapter() {
        super(DIFFER);
    }

    private static DiffUtil.ItemCallback<Movie> DIFFER = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getRating() == newItem.getRating() &&
                    oldItem.getImageUrl().equals(newItem.getImageUrl()) &&
                    oldItem.getReleaseYear() == newItem.getReleaseYear();
        }
    };

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = getItem(position);
        Glide.with(holder.itemView.getContext()).load(getItem(position).getImageUrl())
                .centerCrop().fitCenter().into(holder.image);
        holder.title.setText(getItem(position).getTitle());
        holder.rating.setText(String.valueOf(getItem(position).getRating()));
        holder.releaseYear.setText(String.valueOf(getItem(position).getReleaseYear()));
    }


    class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title;
        private TextView rating;
        private TextView releaseYear;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.movieImage);
            title = itemView.findViewById(R.id.movieTitle);
            rating = itemView.findViewById(R.id.movieRating);
            releaseYear = itemView.findViewById(R.id.movieReleaseYear);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(onMovieItemClicked != null && position != RecyclerView.NO_POSITION){
                        onMovieItemClicked.onItemClicked(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnMovieItemClicked{
        void onItemClicked(Movie movie);
    }

    public void setOnMovieItemClicked(OnMovieItemClicked onMovieItemClicked) {
        this.onMovieItemClicked = onMovieItemClicked;
    }
}
