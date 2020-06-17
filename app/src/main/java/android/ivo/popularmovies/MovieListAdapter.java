package android.ivo.popularmovies;

import android.content.Context;
import android.ivo.popularmovies.uri.MovieUriCreator;
import android.ivo.popularmovies.uri.MovieDbUriImage;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
    private static final String TAG = "MovieListAdapter";
    private int mTotalItems;
    private ArrayList<Movie> mMovies;
    private final MovieAdapterOnClickListener mClickListener;

    MovieListAdapter(ArrayList<Movie> movies, Context context) {
        mMovies = movies;
        mTotalItems = mMovies.size();
        mClickListener = (MovieAdapterOnClickListener) context;
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
        Movie movie = mMovies.get(position);
        holder.mMovieTitle.setText(movie.getTitle() == null ? "No Movie Title" : movie.getTitle());

        String imageUrl = new MovieUriCreator()
                .createImageQuery()
                .imageSize(MovieDbUriImage.W185)
                .fileName(movie.getPosterPath())
                .fetch();

        Picasso.get().load(imageUrl).into(holder.mMoviePoster);
    }

    @Override
    public int getItemCount() {
        return mTotalItems;
    }

    public interface MovieAdapterOnClickListener {
        void OnClick(int position);
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mMoviePoster;
        TextView mMovieTitle;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            mMoviePoster = itemView.findViewById(R.id.img_item_poster);
            mMovieTitle = itemView.findViewById(R.id.tv_item_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mClickListener.OnClick(clickedPosition);
        }
    }
}
