package android.ivo.popularmovies.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.ivo.popularmovies.BundleKeys;
import android.ivo.popularmovies.activities.viewmodels.MainViewModel;
import android.ivo.popularmovies.models.Movie;
import android.ivo.popularmovies.adapters.MovieRvAdapter;
import android.ivo.popularmovies.R;
import android.ivo.popularmovies.databinding.ActivityMainBinding;
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
    private MainViewModel mViewModel;

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

        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                updateUI();
            }
        });

        mViewModel.initMoviesInSavedOrder();
    }

    public void updateUI() {
        mMovieRvAdapter = new MovieRvAdapter((ArrayList<Movie>) mViewModel.getMovies().getValue(), this);
        mRecyclerView.setAdapter(mMovieRvAdapter);
    }

    @Override
    public void OnClick(int position) {
        Intent movieDetailsIntent = new Intent(this, DetailsActivity.class);

        // getValue() is called on the main thread so it shouldn't impose any problems
        // being NOT_SET
        @SuppressWarnings("ConstantConditions")
        Movie movie = mViewModel.getMovies()
                .getValue()
                .get(position);

        movieDetailsIntent.putExtra(BundleKeys.MOVIE_BUNDLE_KEY, movie);
        startActivity(movieDetailsIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item) {
        if (item.getItemId() == R.id.menu_sort_popular) {
            mViewModel.setMoviesInOrder(MdbDiscover.POPULAR);
        } else if (item.getItemId() == R.id.menu_sort_rating) {
            mViewModel.setMoviesInOrder(MdbDiscover.TOP_RATED);
        } else if (item.getItemId() == R.id.menu_sort_now_playing) {
            mViewModel.setMoviesInOrder(MdbDiscover.NOW_PLAYING);
        } else if (item.getItemId() == R.id.menu_sort_upcoming) {
            mViewModel.setMoviesInOrder(MdbDiscover.UPCOMING);
        } else if (item.getItemId() == R.id.main_menu_fav) {
            Intent i = new Intent(this, FavoritesActivity.class);
            startActivity(i);
        }
        return true;
    }
}
