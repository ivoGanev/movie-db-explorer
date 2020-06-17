package android.ivo.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.ivo.popularmovies.databinding.ActivityMovieDetailsBinding;
import android.ivo.popularmovies.movieDbUri.MovieUriCreator;
import android.ivo.popularmovies.movieDbUri.MovieDbUriImage;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {
    ActivityMovieDetailsBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        View v = mBinding.getRoot();
        setContentView(v);

        Intent i = getIntent();
        Movie movie = i.getParcelableExtra("movie");

        String imageUrl = new MovieUriCreator()
                .createImageQuery()
                .imageSize(MovieDbUriImage.W342)
                .fileName(movie.getPosterPath())
                .fetch();

        Picasso.get().load(imageUrl).into(mBinding.imgMovieDetail);
        mBinding.tvDetailsTitle.setText(movie.getTitle());
        mBinding.tvPlotSynopsis.setText(movie.getPlotSynopsis());
        mBinding.tvRating.setText(movie.getVoteAverage());
        mBinding.tvReleaseDate.setText(movie.getReleaseDate());

        GradientDrawable ratingCircle = (GradientDrawable)mBinding.tvRating.getBackground();
        int color = movie.getRatingColor(this);
        ratingCircle.setColor(color);
    }
}
