package android.ivo.popularmovies.fragments;

import android.ivo.popularmovies.BundleKeys;
import android.ivo.popularmovies.component.Movie;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class MovieBundledFragment extends Fragment {
    abstract void onBundleLoad(Movie movie);

    @Nullable
    @Override
    @CallSuper
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            Movie movie = getArguments().getParcelable(BundleKeys.MOVIE_BUNDLE_KEY);
            onBundleLoad(movie);
        }
        return null;
    }
}
