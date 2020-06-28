package android.ivo.popularmovies.activities.fragments;

import android.ivo.popularmovies.BundleKeys;
import android.ivo.popularmovies.activities.viewmodels.DetailsViewModel;
import android.ivo.popularmovies.activities.viewmodels.DetailsViewModelFactory;
import android.ivo.popularmovies.models.Movie;
import android.os.Bundle;
import android.util.Log;
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

public abstract class MovieBundledFragment extends Fragment {
    private ViewBinding mViewBinding;

    abstract void onBundleLoad(Movie movie);

    @Nullable
    @Override
    @CallSuper
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewBinding = getViewBinding(inflater, container);

        if (mViewBinding == null)
            throw new IllegalStateException("Cannot create a view without a View Binding. Ensure you present a view with getViewBinding");

        Bundle bundle = getArguments();
        if (bundle != null) {
            Movie movie = getArguments().getParcelable(BundleKeys.MOVIE_BUNDLE_KEY);
            onBundleLoad(movie);
            DetailsViewModelFactory factory =
                    new DetailsViewModelFactory(movie, requireActivity().getApplication());
            DetailsViewModel viewModel = new ViewModelProvider(requireActivity(), factory)
                    .get(DetailsViewModel.class);
            viewModel.getMovie().observe(requireActivity(), new Observer<Movie>() {
                @Override
                public void onChanged(Movie movie) {
                    onDataChanged(movie);
                }
            });
        }
        return mViewBinding.getRoot();
    }

    public abstract ViewBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container);

    public ViewBinding getInflatedViewBinding() {
        return mViewBinding;
    }

    public abstract void onDataChanged(Movie movie);

    @Override
    @CallSuper
    public void onDestroy() {
        super.onDestroy();
        mViewBinding = null;
    }
}
