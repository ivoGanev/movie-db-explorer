package android.ivo.popularmovies;

import android.content.Context;
import android.content.ContextWrapper;
import android.net.Uri;
import android.util.Log;

import androidx.core.util.Pair;

abstract class MovieUrlQuery{
    private static final String TAG = MovieUrlQuery.class.getSimpleName();

    public MovieUrlQuery(Context context, String apiKey) {
        mApiKey = apiKey;
        mApiQueryKey = context.getString(R.string.param_api_key);
    }

    private String mApiKey;
    private String mApiQueryKey;
    private final static String SCHEME = "https";

    protected abstract String getPath();

    protected abstract String getAuthority();

    String create() {
        Uri.Builder uri = new Uri.Builder()
                .scheme(SCHEME)
                .authority(getAuthority())
                .path(getPath())
                .appendQueryParameter(getApiQueryKey(), getApiKey());
        if (getQuery() != null)
            uri.appendQueryParameter(getQuery().first, getQuery().second);

        return uri.build().toString();
    }

    abstract Pair<String, String> getQuery();

    String getApiKey() {
        return mApiKey;
    }

    String getApiQueryKey() {
        return mApiQueryKey;
    }

}
