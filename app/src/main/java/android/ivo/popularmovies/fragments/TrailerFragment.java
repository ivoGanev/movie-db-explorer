package android.ivo.popularmovies.fragments;


import android.ivo.popularmovies.component.Movie;
import android.ivo.popularmovies.databinding.FragmentMovieTrailerBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

public class TrailerFragment extends MovieBundledFragment {
    @Override
    void onBundleLoad(Movie movie) {

    }

    @Override
    public ViewBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentMovieTrailerBinding.inflate(inflater, container, false);
    }
}
