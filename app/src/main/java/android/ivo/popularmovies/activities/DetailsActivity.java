package android.ivo.popularmovies.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.ivo.popularmovies.BundleKeys;
import android.ivo.popularmovies.activities.viewmodels.DetailsActivityViewModel;
import android.ivo.popularmovies.activities.viewmodels.DetailsActivityViewModelFactory;
import android.ivo.popularmovies.network.models.Movie;
import android.ivo.popularmovies.databinding.ActivityMovieDetailsBinding;
import android.ivo.popularmovies.adapters.DetailsPagerAdapter;

import android.ivo.popularmovies.network.ApiClient;
import android.ivo.popularmovies.network.uri.MdbImage;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    ActivityMovieDetailsBinding mBinding;
    DetailsActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        Intent i = getIntent();
        Movie movie = i.getParcelableExtra(BundleKeys.MOVIE_BUNDLE_KEY);
        final Bundle bundle = new Bundle();
        final Context context = this;
        final ViewPager viewPager = mBinding.vpMovieDetails;
        bundle.putParcelable(BundleKeys.MOVIE_BUNDLE_KEY, movie);

        DetailsActivityViewModelFactory factory = new DetailsActivityViewModelFactory(movie);
        mViewModel = new ViewModelProvider(this, factory).get(DetailsActivityViewModel.class);
        mViewModel.getMovie().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                viewPager.setAdapter(new DetailsPagerAdapter(getSupportFragmentManager(), context, bundle));
                Log.d("TAG", "onChanged: " + mViewModel.getMovie().getValue().getTrailers().size());
            }
        });
        mViewModel.loadReviews();
        mViewModel.loadTrailers();
        mBinding.tlMovieDetails.setupWithViewPager(viewPager);

        mViewModel.loadPosterImage(mBinding.imgMovieDetail);
    }


}
