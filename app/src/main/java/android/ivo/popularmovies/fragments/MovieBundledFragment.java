package android.ivo.popularmovies.fragments;

import android.ivo.popularmovies.BundleKeys;
import android.ivo.popularmovies.network.apimodels.Movie;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

public abstract class MovieBundledFragment extends Fragment {
    ViewBinding mViewBinding;

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
        }
        return mViewBinding.getRoot();
    }

    public abstract ViewBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container);

    public ViewBinding getInflatedViewBinding()
    {
        return mViewBinding;
    }

    @Override
    @CallSuper
    public void onDestroy() {
        super.onDestroy();
        mViewBinding = null;
    }
}
