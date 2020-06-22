package android.ivo.popularmovies.activities.fragments;

import android.ivo.popularmovies.adapters.ReviewsRvAdapter;
import android.ivo.popularmovies.network.ApiClient;
import android.ivo.popularmovies.network.models.Movie;
import android.ivo.popularmovies.databinding.FragmentMovieReviewsBinding;

import android.ivo.popularmovies.network.models.Review;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class UserReviewsFragment extends MovieBundledFragment {

    @Override
    void onBundleLoad(Movie movie) {
        FragmentMovieReviewsBinding binding = (FragmentMovieReviewsBinding) getInflatedViewBinding();
        ReviewsRvAdapter reviewsRvAdapter = new ReviewsRvAdapter(movie.getReview());
        binding.movieReviewsRv.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        binding.movieReviewsRv.setAdapter(reviewsRvAdapter);
    }

    @Override
    public ViewBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentMovieReviewsBinding.inflate(inflater,container,false);
    }
}
