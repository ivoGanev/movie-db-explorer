package android.ivo.popularmovies.adapters;

import android.content.Context;
import android.ivo.popularmovies.network.models.Movie;
import android.ivo.popularmovies.R;
import android.ivo.popularmovies.network.ApiClient;
import android.ivo.popularmovies.network.models.MovieInfo;
import android.ivo.popularmovies.network.uri.MdbImage;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieRvAdapter extends RecyclerView.Adapter<MovieRvAdapter.MovieViewHolder> {
    private static final String TAG = "MovieListAdapter";
    private int mTotalItems;
    private ArrayList<Movie> mMovies;
    private final MovieAdapterOnClickListener mClickListener;

    public MovieRvAdapter(ArrayList<Movie> movies, Context context) {
        if(movies ==null)
            mMovies = new ArrayList<>();
        else
            mMovies = movies;

        mTotalItems = mMovies.size();
        mClickListener = (MovieAdapterOnClickListener) context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.activity_movie_rv_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieInfo movieInfo = mMovies.get(position).getMovieInfo();
        holder.mMovieTitle.setText(movieInfo.getTitle() == null ? "No Movie Title" : movieInfo.getTitle());

        String imageUrl = ApiClient.UrlAddressBook
                .queryImageAddress()
                .imageSize(MdbImage.W185)
                .fileName(movieInfo.getPosterPath())
                .get();

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
