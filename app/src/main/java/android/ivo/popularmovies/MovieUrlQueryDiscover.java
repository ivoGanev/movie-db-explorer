package android.ivo.popularmovies;

import android.content.Context;

import androidx.annotation.StringDef;
import androidx.core.util.Pair;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MovieUrlQueryDiscover extends MovieUrlQuery {
    private static final String AUTHORITY = "api.themoviedb.org";
    private static final String PATH = "3/movie/";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({POPULAR, TOP_RATED, NOW_PLAYING, UPCOMING})
    @interface OrderType {
    }

    static final String POPULAR = "popular";
    static final String TOP_RATED = "top_rated";
    static final String NOW_PLAYING = "now_playing";
    static final String UPCOMING = "upcoming";

    private String mOrderTypePath;

    MovieUrlQueryDiscover(Context context, String apiKey) {
        super(context, apiKey);
        // default is popular
        mOrderTypePath = POPULAR;
    }

    MovieUrlQuery orderBy(@OrderType String orderType) {
        mOrderTypePath = orderType;
        return this;
    }

    @Override
    protected String getPath() {
        return PATH + mOrderTypePath;
    }

    @Override
    protected String getAuthority() {
        return AUTHORITY;
    }

    @Override
    Pair<String, String> getQuery() {
        return null;
    }
}
