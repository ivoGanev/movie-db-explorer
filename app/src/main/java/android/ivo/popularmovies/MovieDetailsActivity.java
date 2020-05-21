package android.ivo.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.ivo.popularmovies.databinding.ActivityMovieDetailsBinding;
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

        String imageUrl = new MovieUriCreator(this)
                .createImageQuery()
                .imageSize(MovieUrlQueryImage.W342)
                .fileName(movie.getPosterPath())
                .create();

        Picasso.get().load(imageUrl).into(mBinding.imgMovieDetail);
        mBinding.tvDetailsTitle.setText(movie.getTitle());
        mBinding.tvPlotSynopsis.setText(movie.getPlotSynopsis());
        mBinding.tvRating.setText(movie.getVoteAverage());
        mBinding.tvReleaseDate.setText(movie.getReleaseDate());

        GradientDrawable ratingCircle = (GradientDrawable)mBinding.tvRating.getBackground();
        int color = getRatingColor(Double.parseDouble(movie.getVoteAverage()));
        ratingCircle.setColor(color);
    }

    private int getRatingColor(double rating) {
        Log.d("TAG", "getRatingColor: " + rating);
        if(rating>= 0 && rating <= 2)
            return ContextCompat.getColor(this, R.color.rating_a);
        else if(rating> 2 && rating <= 4)
            return ContextCompat.getColor(this, R.color.rating_b);
        else if(rating> 4 && rating <= 6)
            return ContextCompat.getColor(this, R.color.rating_c);
        else if(rating> 6 && rating <= 8)
            return ContextCompat.getColor(this, R.color.rating_d);
        else
            return ContextCompat.getColor(this, R.color.rating_e);
    }
}
