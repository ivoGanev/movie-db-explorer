package android.ivo.popularmovies;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MovieLoaderTask extends AsyncTaskLoader<List<Movie>> {
    private static final String MOVIE_DB_URL = "https://api.themoviedb.org/3/movie/550?api_key=&callback=test";
    private static final String API_KEY = "";
    private static final String TAG = "MovieLoaderTask";

    @Nullable
    @Override
    public List<Movie> loadInBackground() {
        List<Movie> movies = new ArrayList<>();
        String jsonData = HttpUtilities.getJsonData(stringToURL(MOVIE_DB_URL));
        Log.d(TAG, "loadInBackground: " + jsonData);
        return movies;
    }

    private URL stringToURL(String urlString) {
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            Log.e(TAG, "stringToURL: ", e);
        }
        return url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    MovieLoaderTask(@NonNull Context context) {
        super(context);
    }
}
