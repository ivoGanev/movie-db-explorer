package android.ivo.popularmovies.network.uri;

import androidx.core.util.Pair;

import java.util.List;

public class MdbReview extends MdbUri {
    private static final String AUTHORITY = "api.themoviedb.org";
    private static final String PATH = "3/movie/";
    private final String mMovieId;

    public MdbReview(String apiKey, String movieId) {
        super(apiKey);
        mMovieId = movieId;
    }

    @Override
    protected String getPath() {
        return PATH  + mMovieId + "/reviews";
    }

    @Override
    protected String getAuthority() {
        return AUTHORITY;
    }

    @Override
    List<Pair<String, String>> getQueries() {
        return null;
    }
}
