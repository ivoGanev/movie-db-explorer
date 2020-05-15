package android.ivo.popularmovies;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
    private static final String TAG = "MovieListAdapter";
    private int mTotalItems;
    private ArrayList<Movie> mMovies;

    MovieListAdapter(ArrayList<Movie> movies) {
        mMovies = movies;
        mTotalItems = mMovies.size();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.activity_movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + position);
        String movieTitle = mMovies.get(position).getTitle();
        holder.mMovieTitle.setText(movieTitle == null ? "No Movie Title" : movieTitle);
        String moviePosterLink = mMovies.get(position).getPosterLink();
        // TODO: Fetch the image from the URL
        holder.mMoviePoster.setImageResource(R.drawable.ic_launcher_background);
    }

    @Override
    public int getItemCount() {
        return mTotalItems;
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView mMoviePoster;
        TextView mMovieTitle;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            mMoviePoster = itemView.findViewById(R.id.img_item_poster);
            mMovieTitle = itemView.findViewById(R.id.tv_item_title);
        }
    }
}
