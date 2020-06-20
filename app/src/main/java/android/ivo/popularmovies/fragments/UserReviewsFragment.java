package android.ivo.popularmovies.fragments;

import android.ivo.popularmovies.component.Movie;
import android.ivo.popularmovies.databinding.FragmentMovieReviewsBinding;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

public class UserReviewsFragment extends MovieBundledFragment {
    @Override
    void onBundleLoad(Movie movie) {
        FragmentMovieReviewsBinding binding = (FragmentMovieReviewsBinding) getInflatedViewBinding();
    }

    @Override
    public ViewBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentMovieReviewsBinding.inflate(inflater,container,false);
    }
}
