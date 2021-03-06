package android.ivo.popularmovies.network.uri;

import androidx.annotation.StringDef;
import androidx.core.util.Pair;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public final class MdbDiscover extends MdbUri {
    private static final String AUTHORITY = "api.themoviedb.org";
    private static final String PATH = "3/movie/";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({POPULAR, TOP_RATED, NOW_PLAYING, UPCOMING})
   public  @interface OrderType {
    }

    public static final String POPULAR = "popular";
    public static final String TOP_RATED = "top_rated";
    public static final String NOW_PLAYING = "now_playing";
    public static final String UPCOMING = "upcoming";

    private String mOrderTypePath;

    public MdbDiscover(String apiKey) {
        super(apiKey);
        mOrderTypePath = POPULAR;
    }

    public MdbUri orderBy(@OrderType String orderType) {
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
    protected List<Pair<String, String>> getQueries() {
        return null;
    }
}
