package android.ivo.popularmovies.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.ivo.popularmovies.BundleKeys;
import android.ivo.popularmovies.MainActivityViewModel;
import android.ivo.popularmovies.MainActivityViewModelFactory;
import android.ivo.popularmovies.network.models.Movie;
import android.ivo.popularmovies.adapters.MovieRvAdapter;
import android.ivo.popularmovies.R;
import android.ivo.popularmovies.databinding.ActivityMainBinding;
import android.ivo.popularmovies.network.ApiClient;
import android.ivo.popularmovies.network.uri.MdbDiscover;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        MovieRvAdapter.MovieAdapterOnClickListener {
    private static final int GRID_SPANS = 2;
    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private MovieRvAdapter mMovieRvAdapter;
    private ApiClient mApiClient;
    private MainActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View mainView = binding.getRoot();
        setContentView(mainView);

        /* Initialize recycler view*/
        mRecyclerView = binding.rvMovies;
        mMovieRvAdapter = new MovieRvAdapter(new ArrayList<Movie>(), this);
        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(this, GRID_SPANS, RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mMovieRvAdapter);
        mApiClient = new ApiClient();

        MainActivityViewModelFactory factory = new MainActivityViewModelFactory();
        mViewModel = new ViewModelProvider(this, factory).get(MainActivityViewModel.class);

        mViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                updateUI();
            }
        });
        mViewModel.updateMovies(MdbDiscover.POPULAR);
    }

    public void updateUI() {
        mMovieRvAdapter = new MovieRvAdapter((ArrayList<Movie>) mViewModel.getMovies().getValue(), this);
        mRecyclerView.setAdapter(mMovieRvAdapter);
    }

    @Override
    public void OnClick(int position) {
        Intent movieDetails = new Intent(this, DetailsActivity.class);
        Movie movie = mViewModel.getMovies().getValue().get(position);

        movieDetails.putExtra(BundleKeys.MOVIE_BUNDLE_KEY, movie);
        startActivity(movieDetails);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item) {
        if (item.getItemId() == R.id.menu_sort_popular) {
            mViewModel.updateMovies(MdbDiscover.POPULAR);
        } else if (item.getItemId() == R.id.menu_sort_rating) {
            mViewModel.updateMovies(MdbDiscover.TOP_RATED);
        } else if (item.getItemId() == R.id.menu_sort_now_playing) {
            mViewModel.updateMovies(MdbDiscover.NOW_PLAYING);
        } else if (item.getItemId() == R.id.menu_sort_upcoming) {
            mViewModel.updateMovies(MdbDiscover.UPCOMING);
        }
        return true;
    }
}
