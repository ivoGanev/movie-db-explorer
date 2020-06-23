package android.ivo.popularmovies.activities.viewmodels;

import android.app.Application;
import android.ivo.popularmovies.database.AppDatabase;
import android.ivo.popularmovies.network.ApiClient;
import android.ivo.popularmovies.network.models.Movie;
import android.ivo.popularmovies.network.uri.MdbImage;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class DetailsActivityViewModel extends AndroidViewModel {
    private final MutableLiveData<Movie> mMovie;
    private final ApiClient mApiClient;
    private final AppDatabase mAppDatabase;

    private final MutableLiveData<Boolean> mInDatabase;

    public DetailsActivityViewModel(Movie movie, Application application) {
        super(application);
        mAppDatabase = AppDatabase.getInstance(application.getApplicationContext());
        mMovie = new MutableLiveData<>(movie);
        mInDatabase = new MutableLiveData<>(false);
        mApiClient = ApiClient.getInstance();
    }

    public void loadReviews() {
        mApiClient.postReview(mMovie);
    }

    public void loadTrailers() {
        mApiClient.postTrailer(mMovie);
    }

    public MutableLiveData<Movie> getMovie() {
        return mMovie;
    }

    public MutableLiveData<Boolean> getInDatabase() {
        return mInDatabase;
    }

    public String getPosterImageUrl() {
        return ApiClient.UrlAddressBook
                .queryImageAddress()
                .imageSize(MdbImage.W342)
                .fileName(mMovie.getValue().getMovieInfo().getPosterPath())
                .get();
    }

    public Boolean setCurrentMovieAsFavourite() {
        if (movieInfoIsInDatabase()) {
            mInDatabase.setValue(false);
            mAppDatabase.dao().deleteMovieInfo(mMovie.getValue().getMovieInfo());

        } else {

            mInDatabase.setValue(true);
            mAppDatabase.dao().insertMovieInfo(mMovie.getValue().getMovieInfo());
        }
        return mInDatabase.getValue();
    }

    public Boolean movieInfoIsInDatabase() {
        return mAppDatabase.dao().getMovieInfo(mMovie.getValue().getMovieInfo().getId()) != null;
    }
}
