package android.ivo.popularmovies;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import java.util.List;

public class MovieLoaderCallbacks  implements LoaderManager.LoaderCallbacks<List<Movie>> {
    private Context mContext;

    @NonNull
    @Override
    public Loader<List<Movie>> onCreateLoader(int id, @Nullable Bundle args) {
        return new MovieLoaderTask(mContext);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Movie>> loader, List<Movie> data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Movie>> loader) {

    }

    MovieLoaderCallbacks(@NonNull Context context) {
        mContext = context;
    }
}
