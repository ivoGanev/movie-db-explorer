package android.ivo.popularmovies.activities.fragments;

import android.ivo.popularmovies.adapters.ReviewsRvAdapter;
import android.ivo.popularmovies.models.Movie;
import android.ivo.popularmovies.databinding.FragmentMovieReviewsBinding;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

public class UserReviewsFragment extends MovieBundledFragment {

    @Override
    void onBundleLoad(Movie movie) {
        FragmentMovieReviewsBinding binding = (FragmentMovieReviewsBinding) getInflatedViewBinding();
        ReviewsRvAdapter reviewsRvAdapter = new ReviewsRvAdapter(movie.getReview());
        DividerItemDecoration decoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);

        binding.movieReviewsRv.addItemDecoration(decoration);
        binding.movieReviewsRv.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        binding.movieReviewsRv.setAdapter(reviewsRvAdapter);
    }

    @Override
    public ViewBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentMovieReviewsBinding.inflate(inflater,container,false);
    }
}
