package android.ivo.popularmovies.network.uri;

import android.ivo.popularmovies.BuildConfig;

public class DatabaseUriCreator {
    private static final String API_KEY = BuildConfig.API_KEY;

    public DatabaseUriImage createImageQuery() {
        return new DatabaseUriImage(API_KEY);
    }

    public DatabaseUriDiscover createDiscoverQuery() {
        return new DatabaseUriDiscover(API_KEY);
    }
}
