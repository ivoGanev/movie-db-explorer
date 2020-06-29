package android.ivo.popularmovies.activities.viewmodels;

import android.app.Application;
import android.ivo.popularmovies.AppExecutors;
import android.ivo.popularmovies.database.AppDatabase;
import android.ivo.popularmovies.models.MovieInfo;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class FavoritesViewModel extends AndroidViewModel {
    private static final String TAG = FavoritesViewModel.class.getCanonicalName();
    private final AppDatabase mDatabase;
    private final AppExecutors mExecutors;
    private LiveData<List<MovieInfo>> mLiveData;

    public FavoritesViewModel(@NonNull Application application) {
        super(application);
        mDatabase = AppDatabase.getInstance(getApplication().getApplicationContext());
        mExecutors = AppExecutors.getInstance();
        mLiveData = mDatabase.dao().getEntities();
    }

    public LiveData<List<MovieInfo>> getLiveData() {
        if (mLiveData == null) {
            mLiveData = mDatabase.dao().getEntities();
        }
        return mLiveData;
    }

    public void removeEntry(final int position) {
        final List<MovieInfo> value = mLiveData.getValue();
        if (value != null) {
            mExecutors.getDiskIOExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    mDatabase.dao().deleteMovieInfo(value.get(position));
                }
            });
        } else {
            Log.e(TAG, "removeEntry: Trying to remove an entry at position " + position +
                    "but the live data is null.");
        }
    }
}
