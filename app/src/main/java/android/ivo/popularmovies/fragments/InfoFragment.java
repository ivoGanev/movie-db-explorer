package android.ivo.popularmovies.fragments;

import android.graphics.drawable.GradientDrawable;
import android.ivo.popularmovies.component.Movie;

import android.ivo.popularmovies.databinding.FragmentMovieInfoBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

public class InfoFragment extends MovieBundledFragment {

    @Override
    public ViewBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentMovieInfoBinding.inflate(inflater, container, false);
    }

    @Override
    void onBundleLoad(Movie movie) {
        FragmentMovieInfoBinding binding = (FragmentMovieInfoBinding) getInflatedViewBinding();

        binding.tvDetailsTitle.setText(movie.getTitle());
        binding.tvPlotSynopsis.setText(movie.getPlotSynopsis());
        binding.tvRating.setText(movie.getVoteAverage());
        binding.tvReleaseDate.setText(movie.getReleaseDate());

        GradientDrawable ratingCircle = (GradientDrawable) binding.tvRating.getBackground();
        int color = movie.getRatingColor(requireContext());
        ratingCircle.setColor(color);
    }
}
