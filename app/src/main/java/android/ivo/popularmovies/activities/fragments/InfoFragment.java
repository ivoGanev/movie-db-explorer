package android.ivo.popularmovies.activities.fragments;

import android.graphics.drawable.GradientDrawable;
import android.ivo.popularmovies.R;
import android.ivo.popularmovies.models.Movie;
import android.ivo.popularmovies.models.MovieInfo;

import android.ivo.popularmovies.databinding.FragmentMovieInfoBinding;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.viewbinding.ViewBinding;

public class InfoFragment extends MovieFragment {

    @Override
    public ViewBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentMovieInfoBinding.inflate(inflater, container, false);
    }

    @Override
    public void init(Movie movie) {
        MovieInfo movieInfo = getViewModel().getMovieInfo();
        FragmentMovieInfoBinding binding = (FragmentMovieInfoBinding)getInflatedViewBinding();

        binding.tvDetailsTitle.setText(movieInfo.getTitle());
        binding.tvPlotSynopsis.setText(movieInfo.getPlotSynopsis());
        binding.tvRating.setText(Double.toString(movieInfo.getVoteAverage()));
        binding.tvReleaseDate.setText(movieInfo.getReleaseDate());

        GradientDrawable ratingCircle = (GradientDrawable) binding.tvRating.getBackground();
        int color = getRatingColor(movieInfo.getVoteAverage());
        ratingCircle.setColor(color);
    }

    private int getRatingColor(double rating) {
        if (rating >= 0 && rating <= 2)
            return ContextCompat.getColor(requireContext(), R.color.rating_a);
        else if (rating > 2 && rating <= 4)
            return ContextCompat.getColor(requireContext(), R.color.rating_b);
        else if (rating > 4 && rating <= 6)
            return ContextCompat.getColor(requireContext(), R.color.rating_c);
        else if (rating > 6 && rating <= 8)
            return ContextCompat.getColor(requireContext(), R.color.rating_d);
        else
            return ContextCompat.getColor(requireContext(), R.color.rating_e);
    }

}
