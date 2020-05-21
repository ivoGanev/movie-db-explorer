package android.ivo.popularmovies;

import android.content.Context;

import androidx.annotation.StringDef;
import androidx.core.util.Pair;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

class MovieUrlQueryImage extends MovieUrlQuery {
    private static final String AUTHORITY = "image.tmdb.org";
    private static final String PATH = "t/p/";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({W92, W185, W342
    })
    @interface ImageSize {
    }

    static final String W92 = "w92";
    static final String W185 = "w185";
    static final String W342 = "w342";

    private String mFileName;
    private String mImageSize;

    MovieUrlQueryImage(Context context, String apiKey) {
        super(context, apiKey);
    }

    MovieUrlQueryImage imageSize(@ImageSize String imageSize) {
        mImageSize = imageSize;
        return this;
    }

    MovieUrlQueryImage fileName(String fileName) {
        mFileName = fileName;
        return this;
    }

    @Override
    protected  String getPath() {
        return PATH + mImageSize + mFileName;
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

