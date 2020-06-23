package android.ivo.popularmovies.activities.viewmodels;

import android.app.Application;
import android.ivo.popularmovies.R;
import android.ivo.popularmovies.databinding.ActivityMovieDetailsBinding;
import android.ivo.popularmovies.network.ApiClient;
import android.ivo.popularmovies.network.models.Movie;
import android.ivo.popularmovies.network.uri.MdbImage;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class DetailsActivityViewModel extends ViewModel {
    private final MutableLiveData<Movie> mMovie;
    private final ApiClient mApiClient;
    private final MutableLiveData<Boolean> mInDatabase;

    public DetailsActivityViewModel(Movie movie) {
        mMovie = new MutableLiveData<>(movie);
        mInDatabase = new MutableLiveData<>();
        mInDatabase.setValue(false);
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
        // tackle with the database
        if (mInDatabase.getValue() == false) {
            mInDatabase.setValue(true);
        } else {

            mInDatabase.setValue(false);
        }
        return mInDatabase.getValue();
    }
}
