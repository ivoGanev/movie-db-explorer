package android.ivo.popularmovies.activities.viewmodels;

import android.app.Application;
import android.content.SharedPreferences;
import android.ivo.popularmovies.BundleKeys;
import android.ivo.popularmovies.network.ApiClient;
import android.ivo.popularmovies.models.Movie;
import android.ivo.popularmovies.network.uri.MdbDiscover;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private MutableLiveData<List<Movie>> mMovies;
    private final ApiClient mApiClient;

    public MainViewModel(@NonNull Application application) {
        super(application);
        mApiClient = new ApiClient();
    }

    public LiveData<List<Movie>> getMovies() {
        if(mMovies==null)
            mMovies = new MutableLiveData<>();
        return mMovies;
    }

    public void initMoviesInSavedOrder() {
        SharedPreferences preferences = getApplication()
                .getSharedPreferences(BundleKeys.MOVIE_SHARED_PREFS, 0);
        String order = preferences.getString(BundleKeys.MOVIE_SEARCH_ORDER, null);
        if (order == null)
            order = MdbDiscover.POPULAR;
        mApiClient.postMovies(order, mMovies);
    }

    public void setMoviesInOrder(@MdbDiscover.OrderType final String orderType) {
        mApiClient.postMovies(orderType, mMovies);
        SharedPreferences preferences = getApplication()
                .getSharedPreferences(BundleKeys.MOVIE_SHARED_PREFS, 0);
        preferences
                .edit()
                .putString(BundleKeys.MOVIE_SEARCH_ORDER, orderType)
                .apply();
    }
}
