package android.ivo.popularmovies.activities.viewmodels;

import android.ivo.popularmovies.AppExecutors;
import android.ivo.popularmovies.network.ApiClient;
import android.ivo.popularmovies.models.Movie;
import android.ivo.popularmovies.network.uri.MdbDiscover;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<List<Movie>> mMovies;
    private ApiClient mApiClient;
    private AppExecutors mAppExecutors;

    public MainActivityViewModel() {
        mApiClient = new ApiClient();
        mAppExecutors = AppExecutors.getInstance();
        mMovies = new MutableLiveData<>();
    }

    public LiveData<List<Movie>> getMovies() {
        return mMovies;
    }

    public void updateMovies(@MdbDiscover.OrderType final String orderType)
    {
        mApiClient.postMovies(orderType, mMovies);
    }
}
