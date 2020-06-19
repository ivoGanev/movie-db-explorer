package android.ivo.popularmovies.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.ivo.popularmovies.BundleKeys;
import android.ivo.popularmovies.component.Movie;
import android.ivo.popularmovies.databinding.ActivityMovieDetailsBinding;
import android.ivo.popularmovies.adapters.DetailsPagerAdapter;

import android.ivo.popularmovies.network.uri.DatabaseUriImage;
import android.ivo.popularmovies.network.uri.DatabaseUriCreator;
import android.os.Bundle;
import android.view.View;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    ActivityMovieDetailsBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();

        setContentView(view);

        Intent i = getIntent();
        Movie movie = i.getParcelableExtra(BundleKeys.MOVIE_BUNDLE_KEY);
        Bundle bundle = new Bundle();
        bundle.putParcelable(BundleKeys.MOVIE_BUNDLE_KEY, movie);

        ViewPager viewPager = mBinding.vpMovieDetails;
        viewPager.setAdapter(new DetailsPagerAdapter(getSupportFragmentManager(), this, bundle));

        mBinding.tlMovieDetails.setupWithViewPager(viewPager);

        String imageUrl = new DatabaseUriCreator()
                .createImageQuery()
                .imageSize(DatabaseUriImage.W342)
                .fileName(movie.getPosterPath())
                .fetch();

        Picasso.get().load(imageUrl).into(mBinding.imgMovieDetail);
    }
}
