package android.ivo.popularmovies;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MovieUriNav {
    private static final String SCHEME = "https";
    private static final String IMAGE_AUTHORITY = "image.tmdb.org";
    private static final String IMAGE_PATH = "t/p/";
    private static final String MOVIE_DB_AUTHORITY = "api.themoviedb.org";
    private static final String POPULAR_PATH = "3/movie/popular";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({W92, W185, ORIGINAL
    })
    @interface ImageSize {
    }

    public static final String W92 = "w92/";
    public static final String W185 = "w185/";
    public static final String ORIGINAL = "original/";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({POPULARITY_ASC, POPULARITY_DESC, VOTE_AVERAGE_ASC, VOTE_AVERAGE_DESC})
    @interface SortByType {
    }

    public static final String POPULARITY_ASC = "popularity.asc";
    public static final String POPULARITY_DESC = "popularity.desc";
    public static final String VOTE_AVERAGE_ASC = " vote_average.asc";
    public static final String VOTE_AVERAGE_DESC = " vote_average.desc";

    private MovieUriNav() {
    }

    public static class Builder {
        private Uri mUri;
        private String mApiKey;
        private String mApiQueryKey;
        private Context mContext;

        public Builder(Context context, String apiKey) {
            mContext = context;
            mApiKey = apiKey;
            mApiQueryKey = mContext.getString(R.string.param_api_key);
        }

        public Builder navigateToImage(@ImageSize String size, String fileName) {
            mUri = new Uri.Builder()
                    .scheme(SCHEME)
                    .authority(IMAGE_AUTHORITY)
                    .path(IMAGE_PATH + size + fileName)
                    .appendQueryParameter(mApiQueryKey, mApiKey)
                    .build();

            return this;
        }

        public Builder navigateToPopular() {
            mUri = new Uri.Builder()
                    .scheme(SCHEME)
                    .authority(MOVIE_DB_AUTHORITY)
                    .path(POPULAR_PATH)
                    .appendQueryParameter(mApiQueryKey, mApiKey)
                    .build();
            return this;
        }

        public Builder sortBy(@SortByType String sortType) {
            String sortKey = mContext.getString(R.string.param_sort_by_key);
            mUri = mUri.buildUpon()
                    .appendQueryParameter(sortKey, sortType)
                    .appendQueryParameter(mApiQueryKey, mApiKey)
                    .build();
            return this;
        }

        public Uri getUri() {
            return mUri;
        }
    }
}