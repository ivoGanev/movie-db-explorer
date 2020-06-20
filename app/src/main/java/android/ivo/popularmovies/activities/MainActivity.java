package android.ivo.popularmovies.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.ivo.popularmovies.AppExecutors;
import android.ivo.popularmovies.BundleKeys;
import android.ivo.popularmovies.component.Movie;
import android.ivo.popularmovies.adapters.MovieRvAdapter;
import android.ivo.popularmovies.R;
import android.ivo.popularmovies.databinding.ActivityMainBinding;
import android.ivo.popularmovies.network.ApiHandler;
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
    private List<Movie> mMovies;
    private ApiHandler mApiHandler;

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
        mApiHandler = new ApiHandler();

        // this needs to get called when the task has finished
        updateUI();
    }

    public void updateUI() {
        mMovieRvAdapter = new MovieRvAdapter((ArrayList<Movie>) mMovies, this);
        mRecyclerView.setAdapter(mMovieRvAdapter);
    }

    @Override
    public void OnClick(int position) {
        Intent movieDetails = new Intent(this, DetailsActivity.class);
        Movie movie = mMovies.get(position);

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
            mMovies = mApiHandler.getMovies(MdbDiscover.POPULAR);
        } else if (item.getItemId() == R.id.menu_sort_rating) {
            mMovies = mApiHandler.getMovies(MdbDiscover.TOP_RATED);
        } else if (item.getItemId() == R.id.menu_sort_now_playing) {
            mMovies = mApiHandler.getMovies(MdbDiscover.POPULAR);
        } else if (item.getItemId() == R.id.menu_sort_upcoming) {
            mMovies = mApiHandler.getMovies(MdbDiscover.UPCOMING);
        }
        updateUI();

        return true;
    }
}
