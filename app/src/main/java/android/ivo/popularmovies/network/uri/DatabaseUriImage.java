package android.ivo.popularmovies.network.uri;

import androidx.annotation.StringDef;
import androidx.core.util.Pair;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public class DatabaseUriImage extends DatabaseUri {
    private static final String AUTHORITY = "image.tmdb.org";
    private static final String PATH = "t/p/";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({W92, W185, W342
    })
     @interface ImageSize {
    }

    public static final String W92 = "w92";
    public static final String W185 = "w185";
    public static final String W342 = "w342";

    private String mFileName;
    private String mImageSize;

    DatabaseUriImage(String apiKey) {
        super(apiKey);
        mImageSize = W185;
    }

    public DatabaseUriImage imageSize(@ImageSize String imageSize) {
        mImageSize = imageSize;
        return this;
    }

    public DatabaseUriImage fileName(String fileName) {
        mFileName = fileName;
        return this;
    }

    @Override
    protected String getPath() {
        return PATH + mImageSize + mFileName;
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

