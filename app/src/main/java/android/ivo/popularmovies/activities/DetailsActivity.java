package android.ivo.popularmovies.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.ivo.popularmovies.BundleKeys;
import android.ivo.popularmovies.R;
import android.ivo.popularmovies.activities.viewmodels.DetailsActivityViewModel;
import android.ivo.popularmovies.activities.viewmodels.DetailsActivityViewModelFactory;
import android.ivo.popularmovies.network.models.Movie;
import android.ivo.popularmovies.databinding.ActivityMovieDetailsBinding;
import android.ivo.popularmovies.adapters.DetailsPagerAdapter;

import android.ivo.popularmovies.network.ApiClient;
import android.ivo.popularmovies.network.uri.MdbImage;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {
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
            }
        });

        mViewModel.getInDatabase().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean==true) {
                    Toast.makeText(getApplication().getApplicationContext(),
                            mViewModel.getMovie().getValue().getMovieInfo().getTitle() + " added to favourites",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplication().getApplicationContext(),
                            mViewModel.getMovie().getValue().getMovieInfo().getTitle() + " removed from favourites",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        mViewModel.loadReviews();
        mViewModel.loadTrailers();

        mBinding.tlMovieDetails.setupWithViewPager(viewPager);
        mBinding.btnFavMovieDetails.setOnClickListener(this);

        mViewModel.loadPosterImage(mBinding.imgMovieDetail);
    }

    @Override
    public void onClick(View v) {
        mViewModel.favourite((FloatingActionButton) v);
    }
}
