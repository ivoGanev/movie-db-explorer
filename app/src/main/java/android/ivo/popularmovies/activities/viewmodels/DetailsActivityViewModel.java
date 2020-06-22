package android.ivo.popularmovies.activities.viewmodels;

import android.ivo.popularmovies.network.ApiClient;
import android.ivo.popularmovies.network.models.Movie;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DetailsActivityViewModel extends ViewModel {
    private final MutableLiveData<Movie> mMovie;
    private final ApiClient mApiClient;

    public DetailsActivityViewModel(Movie movie) {
        mMovie = new MutableLiveData<>(movie);
        mApiClient = ApiClient.getInstance();
    }

    public void loadReviews() {
        mApiClient.postReview(mMovie);
    }

    public MutableLiveData<Movie> getMovie() {
        return mMovie;
    }
}
