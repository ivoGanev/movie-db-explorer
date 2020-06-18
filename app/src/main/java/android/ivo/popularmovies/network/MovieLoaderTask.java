package android.ivo.popularmovies.network;

import android.content.Context;
import android.ivo.popularmovies.Movie;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MovieLoaderTask extends AsyncTaskLoader<List<Movie>> {
    private static final String TAG = MovieLoaderTask.class.getSimpleName();
    private final String mUrlAddress;

    public MovieLoaderTask(@NonNull Context context, String urlAddress) {
        super(context);
        mUrlAddress = urlAddress;
    }

    @Nullable
    @Override
    public List<Movie> loadInBackground() {
        return HttpUtilities.parseJsonToMovie(stringToURL(mUrlAddress));
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
}
