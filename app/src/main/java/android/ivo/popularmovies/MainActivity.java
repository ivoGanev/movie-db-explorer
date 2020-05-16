package android.ivo.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.ivo.popularmovies.databinding.ActivityMainBinding;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int GRID_SPANS = 2;

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
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie.Builder("Jack the amazing ripper")
                .posterLink("http://img.png")
                .releaseDate("10/12/2020")
                .build());
        movies.add(new Movie.Builder("Jack the amazing ripper")
                .posterLink("http://img.png")
                .releaseDate("10/12/2020")
                .build());
        movies.add(new Movie.Builder("Jack the amazing ripper")
                .posterLink("http://img.png")
                .releaseDate("10/12/2020")
                .build());

        mRecyclerView = mBinding.rvMovies;
        mMovieListAdapter = new MovieListAdapter(movies);

        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(this, GRID_SPANS, RecyclerView.VERTICAL, false);

        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mMovieListAdapter);

        LoaderManager loaderManager = LoaderManager.getInstance(this);
        loaderManager.initLoader(0,null, new MovieLoaderCallbacks(this));
    }
}
