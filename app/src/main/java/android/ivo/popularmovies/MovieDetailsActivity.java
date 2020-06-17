package android.ivo.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.ivo.popularmovies.databinding.ActivityMovieDetailsBinding;
import android.ivo.popularmovies.details.MovieDetailsPagerAdapter;

import android.ivo.popularmovies.uri.MovieDbUriImage;
import android.ivo.popularmovies.uri.MovieUriCreator;
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
        View view = mBinding.getRoot();

        setContentView(view);

        Intent i = getIntent();
        Movie movie = i.getParcelableExtra("movie");
        Bundle bundle = new Bundle();
        bundle.putParcelable("movie", movie);

        ViewPager viewPager = mBinding.vpMovieDetails;
        viewPager.setAdapter(new MovieDetailsPagerAdapter(getSupportFragmentManager(), this, bundle));

        mBinding.tlMovieDetails.setupWithViewPager(viewPager);

        String imageUrl = new MovieUriCreator()
                .createImageQuery()
                .imageSize(MovieDbUriImage.W342)
                .fileName(movie.getPosterPath())
                .fetch();

        Picasso.get().load(imageUrl).into(mBinding.imgMovieDetail);
    }
}
