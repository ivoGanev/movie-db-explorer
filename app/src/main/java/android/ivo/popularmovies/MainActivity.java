package android.ivo.popularmovies;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.ivo.popularmovies.databinding.ActivityMainBinding;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Movie>> {
    private static final int GRID_SPANS = 2;
    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private MovieListAdapter mMovieListAdapter;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View mainView = mBinding.getRoot();
        setContentView(mainView);

        // TODO: Replace the mock data with the https://developers.themoviedb.org/ API
//        ArrayList<Movie> movies = new ArrayList<>();
//        movies.add(new Movie.Builder("Jack the amazing ripper")
//                .posterLink("http://img.png")
//                .releaseDate("10/12/2020")
//                .build());
//        movies.add(new Movie.Builder("Jack the amazing ripper")
//                .posterLink("http://img.png")
//                .releaseDate("10/12/2020")
//                .build());
//        movies.add(new Movie.Builder("Jack the amazing ripper")
//                .posterLink("http://img.png")
//                .releaseDate("10/12/2020")
//                .build());

        mRecyclerView = mBinding.rvMovies;
        mMovieListAdapter = new MovieListAdapter(new ArrayList<Movie>());
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
        return new MovieLoaderTask(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Movie>> loader, List<Movie> data) {
        mMovieListAdapter = new MovieListAdapter(new ArrayList<Movie>(data));
        mRecyclerView.setAdapter(mMovieListAdapter);
        for (Movie m : data) {
            Log.d(TAG, "onLoadFinished: " + data.toString());
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Movie>> loader) {

    }
}
