package android.ivo.popularmovies.activities.viewmodels;

import android.ivo.popularmovies.databinding.ActivityMovieDetailsBinding;
import android.ivo.popularmovies.network.ApiClient;
import android.ivo.popularmovies.network.models.Movie;
import android.ivo.popularmovies.network.uri.MdbImage;
import android.widget.ImageView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.squareup.picasso.Picasso;

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

    public void loadPosterImage(ImageView imageView) {
        String imageUrl = ApiClient.UrlAddressBook
                .queryImageAddress()
                .imageSize(MdbImage.W342)
                .fileName(mMovie.getValue().getMovieInfo().getPosterPath())
                .get();

        Picasso.get().load(imageUrl).into(imageView);
    }

    public void loadTrailers() {
        mApiClient.postTrailer(mMovie);
    }
}
