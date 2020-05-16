package android.ivo.popularmovies;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MovieLoaderTask extends AsyncTaskLoader<List<Movie>> {
    private static final String MOVIE_DB_URL = "https://api.themoviedb.org/";
    private static final String SEARCH_PATH = "3/movie/popular";
    private static final String API_KEY = "";
    private static final String TAG = "MovieLoaderTask";

    @Nullable
    @Override
    public List<Movie> loadInBackground() {
        List<Movie> movies = new ArrayList<>();
        Uri searchUri = Uri.parse(MOVIE_DB_URL);
        Uri builder = searchUri.buildUpon()
                .appendQueryParameter("api_key", API_KEY)
                .path(SEARCH_PATH)
                .build();

      //  Log.d(TAG, "loadInBackground: " + builder.toString());
        return HttpUtilities.parseJsonToMovie(stringToURL(builder.toString()));
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
