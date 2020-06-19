package android.ivo.popularmovies.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.ivo.popularmovies.BundleKeys;
import android.ivo.popularmovies.component.Movie;
import android.ivo.popularmovies.adapters.MovieListAdapter;
import android.ivo.popularmovies.network.MovieLoaderTask;
import android.ivo.popularmovies.R;
import android.ivo.popularmovies.databinding.ActivityMainBinding;
import android.ivo.popularmovies.network.uri.DatabaseUriCreator;
import android.ivo.popularmovies.network.uri.DatabaseUriDiscover;
import android.os.Bundle;
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
    private DatabaseUriCreator mDatabaseUriCreator;

    private static final String URL_BUNDLE_KEY = "urlAddress";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View mainView = mBinding.getRoot();
        setContentView(mainView);

        /* Initialize recycler view*/
        mRecyclerView = mBinding.rvMovies;
        mMovieListAdapter = new MovieListAdapter(new ArrayList<Movie>(), this);
        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(this, GRID_SPANS, RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mMovieListAdapter);

        /* Load URL query */
        mDatabaseUriCreator = new DatabaseUriCreator();
        Bundle bundle = new Bundle();

        bundle.putString(URL_BUNDLE_KEY, mDatabaseUriCreator
                .createDiscoverQuery()
                .fetch());

        LoaderManager loaderManager = LoaderManager.getInstance(this);
        loaderManager.initLoader(0, bundle, this);
    }

    @NonNull
    @Override
    public Loader<List<Movie>> onCreateLoader(int id, @Nullable Bundle args) {
        String urlQuery = args.getString(URL_BUNDLE_KEY);
        return new MovieLoaderTask(this, urlQuery);
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Bundle bundle = new Bundle();

        if (item.getItemId() == R.id.menu_sort_popular) {
            bundle.putString(URL_BUNDLE_KEY, mDatabaseUriCreator.createDiscoverQuery()
                    .orderBy(DatabaseUriDiscover.POPULAR)
                    .fetch());
        } else if (item.getItemId() == R.id.menu_sort_rating) {
            bundle.putString(URL_BUNDLE_KEY, mDatabaseUriCreator.createDiscoverQuery()
                    .orderBy(DatabaseUriDiscover.TOP_RATED)
                    .fetch());
        } else if (item.getItemId() == R.id.menu_sort_now_playing) {
            bundle.putString(URL_BUNDLE_KEY, mDatabaseUriCreator.createDiscoverQuery()
                    .orderBy(DatabaseUriDiscover.NOW_PLAYING)
                    .fetch());
        } else if (item.getItemId() == R.id.menu_sort_upcoming) {
            bundle.putString(URL_BUNDLE_KEY, mDatabaseUriCreator.createDiscoverQuery()
                    .orderBy(DatabaseUriDiscover.UPCOMING)
                    .fetch());
        }

        LoaderManager loaderManager = LoaderManager.getInstance(this);
        loaderManager.restartLoader(0, bundle, this);
        return true;
    }
}