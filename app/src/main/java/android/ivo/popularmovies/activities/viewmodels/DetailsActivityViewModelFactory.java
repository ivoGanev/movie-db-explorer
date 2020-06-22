package android.ivo.popularmovies.activities.viewmodels;

import android.ivo.popularmovies.network.models.Movie;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class DetailsActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Movie mMovieLiveData;

    public DetailsActivityViewModelFactory(Movie movieLiveData) {
        mMovieLiveData = movieLiveData;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new DetailsActivityViewModel(mMovieLiveData);
    }
}
