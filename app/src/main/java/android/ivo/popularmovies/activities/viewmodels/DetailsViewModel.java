package android.ivo.popularmovies.activities.viewmodels;

import android.app.Application;
import android.graphics.Bitmap;
import android.ivo.popularmovies.AppExecutors;
import android.ivo.popularmovies.database.AppDao;
import android.ivo.popularmovies.database.AppDatabase;
import android.ivo.popularmovies.filesystem.FileSystem;
import android.ivo.popularmovies.models.MovieInfo;
import android.ivo.popularmovies.network.ApiClient;
import android.ivo.popularmovies.models.Movie;
import android.ivo.popularmovies.network.uri.MdbImage;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.File;

public class DetailsViewModel extends AndroidViewModel {
    private static final String TAG = DetailsViewModel.class.getSimpleName();

    private final MutableLiveData<Movie> mMovie;
    private final ApiClient mApiClient;
    private final AppDatabase mDatabase;
    private final AppExecutors mExecutors;

    private MutableLiveData<Boolean> mMovieMarkedAsFavorite = new MutableLiveData<>();

    public DetailsViewModel(@NonNull Movie movie, @NonNull Application application) {
        super(application);
        mDatabase = AppDatabase.getInstance(application.getApplicationContext());
        mExecutors = AppExecutors.getInstance();
        mMovie = new MutableLiveData<>(movie);
        mApiClient = ApiClient.getInstance();
    }

    public LiveData<Movie> getMovie() {
        return mMovie;
    }

    public void loadReviewsAsync() {
        mApiClient.postReview(mMovie);
    }

    public void loadTrailersAsync() {
        mApiClient.postTrailer(mMovie);
    }

    public MovieInfo getMovieInfo() {
        return mMovie.getValue().getMovieInfo();
    }

    public String getPosterImageUrl() {
        return ApiClient.UrlAddressBook
                .queryImageAddress()
                .imageSize(MdbImage.W342)
                .fileName(getMovieInfo().getPosterPath())
                .get();
    }

    private void setAsFavourite(final Movie movie, final Bitmap bitmap) {
        mExecutors.getDiskIOExecutor().execute(new Runnable() {
            @Override
            public void run() {
                AppDao dao = mDatabase.dao();
                boolean inDatabase = getMovieMarkedAsFavorite();
                handlePosterBitmap(bitmap, inDatabase);

                if (!inDatabase) {
                    dao.insertMovieInfo(movie.getMovieInfo());
                    inDatabase = true;
                } else {
                    dao.deleteMovieInfo(movie.getMovieInfo());
                    inDatabase = false;
                }
                mMovieMarkedAsFavorite.postValue(inDatabase);
            }
        });
    }

    /**
     * Stores the movie in SQL database if it is not already there. If the movie information
     * is already there it will be removed.
     */
    public void setCurrentMovieAsFavourite(final Bitmap bitmap) {
        setAsFavourite(mMovie.getValue(), bitmap);
    }

    /*
     * Stores the poster bitmap if it does not already exist
     * */
    private void handlePosterBitmap(final Bitmap bitmap, final Boolean inDatabase) {
        FileSystem fileSystem = new FileSystem();
        File directory = getApplication().getFilesDir();
        // save if not in the database
        if (!inDatabase) {
            fileSystem.saveBitmap(bitmap, directory, Integer.toString(getMovieInfo().getId()));
        } else {
            String absolutePath = getApplication().getFilesDir() + "/" + Integer.toString(getMovieInfo().getId());
            fileSystem.deleteFile(absolutePath);
        }
    }

    public LiveData<Boolean> movieMarkedAsFavorite() {
        return mMovieMarkedAsFavorite;
    }

    private boolean getMovieMarkedAsFavorite() {
        AppDao dao = mDatabase.dao();
        return dao.getMovieInfo(getMovieInfo().getId()) != null;
    }
}
