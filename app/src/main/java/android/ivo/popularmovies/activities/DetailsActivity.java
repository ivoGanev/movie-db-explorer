package android.ivo.popularmovies.activities;

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
import android.ivo.popularmovies.database.AppDatabase;
import android.ivo.popularmovies.models.Movie;
import android.ivo.popularmovies.databinding.ActivityMovieDetailsBinding;
import android.ivo.popularmovies.adapters.DetailsPagerAdapter;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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

        DetailsActivityViewModelFactory factory = new DetailsActivityViewModelFactory(movie, getApplication());
        mViewModel = new ViewModelProvider(this, factory).get(DetailsActivityViewModel.class);
        mViewModel.getMovie().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                viewPager.setAdapter(new DetailsPagerAdapter(getSupportFragmentManager(), context, bundle));
                Picasso.get().load(mViewModel.getPosterImageUrl()).into(mBinding.imgMovieDetail);
            }
        });

        mViewModel.loadReviews();
        mViewModel.loadTrailers();

        mBinding.tlMovieDetails.setupWithViewPager(viewPager);
        mBinding.btnFavMovieDetails.setOnClickListener(this);

        AppDatabase appDatabase = AppDatabase.getInstance(this);
        setAddFavouriteButtonImage(mViewModel.movieInfoIsInDatabase());
    }

    @Override
    public void onClick(View v) {
        Boolean favouriteSet = mViewModel.setCurrentMovieAsFavourite();
        String message = (favouriteSet)
                ? "added to favourites"
                : "removed from favourites";
        toastShowFavouriteStatus(message);
        setAddFavouriteButtonImage(favouriteSet);
    }

    private void setAddFavouriteButtonImage(Boolean add) {
        int imageId = (add)
                ? R.drawable.ic_baseline_favorite_24
                : R.drawable.ic_baseline_favorite_border_24;
        mBinding.btnFavMovieDetails.setImageResource(imageId);
    }

    private void toastShowFavouriteStatus(String status) {
        Toast.makeText(getApplication().getApplicationContext(),
                mViewModel.getMovie().getValue().getMovieInfo().getTitle() + " " + status,
                Toast.LENGTH_SHORT).show();
    }
}
