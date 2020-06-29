package android.ivo.popularmovies.activities.fragments;

import android.ivo.popularmovies.activities.viewmodels.DetailsViewModel;
import android.ivo.popularmovies.models.Movie;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

public abstract class MovieFragment extends Fragment {
    private ViewBinding mViewBinding;
    private DetailsViewModel mViewModel;

    public DetailsViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    @CallSuper
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewBinding = getViewBinding(inflater, container);

        if (mViewBinding == null)
            throw new IllegalStateException("Cannot create a view without a View Binding. Ensure you present a view with getViewBinding");

        mViewModel = new ViewModelProvider(requireActivity())
                .get(DetailsViewModel.class);
        init(mViewModel.getMovie().getValue());

        mViewModel.getMovie().observe(requireActivity(), new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                onDataChanged(movie);
            }
        });

        return mViewBinding.getRoot();
    }

    public abstract ViewBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container);

    public ViewBinding getInflatedViewBinding() {
        return mViewBinding;
    }

    /**
     * This method is called whenever the <b>DetailsViewModel</> changes its data. Currently that is
     * when the reviews and trailers have been loaded.
     */
    public void onDataChanged(Movie movie){}

    /**
     * Use this method instead of onCreateView. It is called after the ViewModel and view bindings
     * being initialised.
     */
    public abstract void init(Movie movie);

    @Override
    @CallSuper
    public void onDestroy() {
        super.onDestroy();
        mViewBinding = null;
    }
}
