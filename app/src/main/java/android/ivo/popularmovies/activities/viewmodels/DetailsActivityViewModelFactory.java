package android.ivo.popularmovies.activities.viewmodels;

import android.app.Application;
import android.ivo.popularmovies.network.models.Movie;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class DetailsActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @NonNull
    private final Movie mMovieLiveData;
    @NonNull
    private final Application mApplication;

    public DetailsActivityViewModelFactory(Movie movieLiveData, Application application) {
        mMovieLiveData = movieLiveData;
        mApplication = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new DetailsActivityViewModel(mMovieLiveData, mApplication);
    }
}
