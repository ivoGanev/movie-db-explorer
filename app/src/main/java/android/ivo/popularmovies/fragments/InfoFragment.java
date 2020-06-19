package android.ivo.popularmovies.fragments;

import android.graphics.drawable.GradientDrawable;
import android.ivo.popularmovies.component.Movie;

import android.ivo.popularmovies.databinding.FragmentMovieInfoBinding;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class InfoFragment extends MovieBundledFragment {
    private FragmentMovieInfoBinding mBinding;

    @Override
    void onBundleLoad(Movie movie) {
        mBinding.tvDetailsTitle.setText(movie.getTitle());
        mBinding.tvPlotSynopsis.setText(movie.getPlotSynopsis());
        mBinding.tvRating.setText(movie.getVoteAverage());
        mBinding.tvReleaseDate.setText(movie.getReleaseDate());

        GradientDrawable ratingCircle = (GradientDrawable) mBinding.tvRating.getBackground();
        int color = movie.getRatingColor(requireContext());
        ratingCircle.setColor(color);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentMovieInfoBinding.inflate(inflater, container, false);
        super.onCreateView(inflater, container, savedInstanceState);

        return mBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        mBinding = null;
        super.onDestroy();
    }
}
