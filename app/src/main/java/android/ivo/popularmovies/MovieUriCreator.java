package android.ivo.popularmovies;

import android.content.Context;
import android.content.ContextWrapper;

public class MovieUriCreator extends ContextWrapper {
    private static final String API_KEY = "";

    public MovieUriCreator(Context base) {
        super(base);
    }

    public MovieUrlQueryImage createImageQuery()
    {
        return new  MovieUrlQueryImage(this, API_KEY);
    }

    public MovieUrlQueryDiscover createDiscoverQuery()
    {
        return new  MovieUrlQueryDiscover(this, API_KEY);
    }
}
