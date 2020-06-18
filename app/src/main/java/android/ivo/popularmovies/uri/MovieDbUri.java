package android.ivo.popularmovies.uri;

import android.net.Uri;

import androidx.core.util.Pair;

import java.util.List;

public abstract class MovieDbUri {
    private static final String TAG = MovieDbUri.class.getSimpleName();
    private static final String API_QUERY_KEY = "api_key";
    private final static String SCHEME = "https";
    private String mApiKey;

    MovieDbUri(String apiKey) {
        mApiKey = apiKey;
    }

    protected abstract String getPath();

    protected abstract String getAuthority();

    public String fetch() {
        Uri.Builder uri = new Uri.Builder()
                .scheme(SCHEME)
                .authority(getAuthority())
                .path(getPath())
                .appendQueryParameter(API_QUERY_KEY, mApiKey);
        if (getQueries() != null) {
            for (int i = 0; i < getQueries().size(); i++) {
                Pair<String, String> query = getQueries().get(i);
                if (getQueries().get(i) != null)
                    uri.appendQueryParameter(query.first, query.second);
            }
        }

        return uri.build().toString();
    }

    abstract List<Pair<String, String>> getQueries();

}
