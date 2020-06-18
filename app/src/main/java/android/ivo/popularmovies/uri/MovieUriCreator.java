package android.ivo.popularmovies.uri;

import android.ivo.popularmovies.BuildConfig;

public class MovieUriCreator {
    private static final String API_KEY = BuildConfig.API_KEY;

    public MovieDbUriImage createImageQuery() {
        return new MovieDbUriImage(API_KEY);
    }

    public MovieDbUriDiscover createDiscoverQuery() {
        return new MovieDbUriDiscover(API_KEY);
    }
}
