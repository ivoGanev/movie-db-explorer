package android.ivo.popularmovies;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.ivo.popularmovies.databinding.ActivityMainBinding;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<List<Movie>>,
        MovieListAdapter.MovieAdapterOnClickListener {
    private static final int GRID_SPANS = 2;
    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private MovieListAdapter mMovieListAdapter;
    private ActivityMainBinding mBinding;
    private ArrayList<Movie> mMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View mainView = mBinding.getRoot();
        setContentView(mainView);

        mRecyclerView = mBinding.rvMovies;
        mMovieListAdapter = new MovieListAdapter(new ArrayList<Movie>(), this);
        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(this, GRID_SPANS, RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mMovieListAdapter);

        LoaderManager loaderManager = LoaderManager.getInstance(this);
        loaderManager.initLoader(0, null, this);
    }

    @NonNull
    @Override
    public Loader<List<Movie>> onCreateLoader(int id, @Nullable Bundle args) {
        // TODO: Use a builder pattern to refactor the MovieUriNav class
//        MovieUriNav nav = new MovieUriNav(this, "API_KEY");
//        nav.getPopular();
//        Uri uri = nav.sortBy(MovieUriNav.POPULARITY_DESC);
//        Log.d(TAG, "onCreateLoader: " + uri.toString());

        return new MovieLoaderTask(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Movie>> loader, List<Movie> data) {
        mMovies = new ArrayList<>(data);
        mMovieListAdapter = new MovieListAdapter(mMovies, this);
        mRecyclerView.setAdapter(mMovieListAdapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Movie>> loader) {

    }

    @Override
    public void OnClick(int position) {
        Intent movieDetails = new Intent(this, MovieDetailsActivity.class);
        Movie movie = mMovies.get(position);
        movieDetails.putExtra("movie", movie);
        startActivity(movieDetails);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sort_popular: {
                Log.d(TAG, "onOptionsItemSelected: sorted by popularity");
                // TODO: 1. Call the MovieUriNav get() to retrieve by popularity
                return true;
            }
            case R.id.menu_sort_rating: {
                Log.d(TAG, "onOptionsItemSelected: sorted by rating");
                // TODO: 2. Call the MovieUriNav get() to retrieve by rating
                return true;
            }
            default:
                return false;
        }
    }
}
