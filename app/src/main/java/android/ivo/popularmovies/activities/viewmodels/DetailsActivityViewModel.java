package android.ivo.popularmovies.activities.viewmodels;

import android.app.Application;
import android.graphics.Bitmap;
import android.ivo.popularmovies.database.AppDatabase;
import android.ivo.popularmovies.filesystem.FileSystem;
import android.ivo.popularmovies.models.MovieInfo;
import android.ivo.popularmovies.network.ApiClient;
import android.ivo.popularmovies.models.Movie;
import android.ivo.popularmovies.network.uri.MdbImage;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.io.File;

public class DetailsActivityViewModel extends AndroidViewModel {
    private static final String TAG = DetailsActivityViewModel.class.getSimpleName();
    private final MutableLiveData<Movie> mMovie;
    private final ApiClient mApiClient;
    private final AppDatabase mAppDatabase;

    private final MutableLiveData<Boolean> mInDatabase;
    private final MovieInfo mMovieInfo;

    public DetailsActivityViewModel(Movie movie, Application application) {
        super(application);
        mAppDatabase = AppDatabase.getInstance(application.getApplicationContext());
        mMovie = new MutableLiveData<>(movie);
        mInDatabase = new MutableLiveData<>(false);
        mApiClient = ApiClient.getInstance();
        mMovieInfo = mMovie.getValue().getMovieInfo();
    }

    public MovieInfo getMovieInfo() {
        return mMovieInfo;
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

    public String getPosterImageUrl() {
        return ApiClient.UrlAddressBook
                .queryImageAddress()
                .imageSize(MdbImage.W342)
                .fileName(mMovieInfo.getPosterPath())
                .get();
    }

    private Boolean setAsFavourite(Movie movie, Boolean value) {
        if (value) {
            mAppDatabase.dao().insertMovieInfo(movie.getMovieInfo());
        } else {
            mAppDatabase.dao().deleteMovieInfo(movie.getMovieInfo());
        }
        mInDatabase.setValue(value);
        return mInDatabase.getValue();
    }

    /**
     * Stores the movie in SQL database if it is not already there. If the movie information
     * is already there it will be removed.
     * @param posterImage the poster image to store in the filesystem
     * */
    public Boolean setCurrentMovieAsFavourite(Bitmap posterImage) {
        storePosterBitmap(posterImage);
        return setAsFavourite(mMovie.getValue(), !movieInfoIsInDatabase());
    }

    /*
    * Stores the poster bitmap if it already exists
    * */
    private void storePosterBitmap(Bitmap bitmap) {
        FileSystem fileSystem = new FileSystem();
        File directory = getApplication().getFilesDir();
        if (movieInfoIsInDatabase()) {
            String absolutePath = getApplication().getFilesDir() + "/" + Integer.toString(mMovieInfo.getId());
            fileSystem.deleteFile(absolutePath);
           // fileSystem.listFiles(directory);
        } else {
            fileSystem.saveBitmap(bitmap, directory, Integer.toString(mMovieInfo.getId()));
        }
    }

    /**
     * Checks whether the movie information persists in the SQL database
     * @return true if the information persists, false if it doesn't
     * */
    public Boolean movieInfoIsInDatabase() {
        return mAppDatabase.dao().getMovieInfo(mMovieInfo.getId()) != null;
    }
}
