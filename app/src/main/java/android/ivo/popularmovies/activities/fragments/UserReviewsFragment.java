package android.ivo.popularmovies.activities.fragments;

import android.ivo.popularmovies.R;
import android.ivo.popularmovies.activities.viewmodels.DetailsViewModel;
import android.ivo.popularmovies.adapters.ReviewsRvAdapter;
import android.ivo.popularmovies.models.Movie;
import android.ivo.popularmovies.databinding.FragmentMovieReviewsBinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

public class UserReviewsFragment extends MovieFragment {
    private DetailsViewModel mViewModel;
    private ReviewsRvAdapter mAdapter;

    @Override
    public void onDataChanged(Movie movie) {
        FragmentMovieReviewsBinding binding = (FragmentMovieReviewsBinding)getInflatedViewBinding();
        boolean displayReviews = movie.getReview().size() > 0;
        if(displayReviews) {
            DividerItemDecoration decoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
            binding.movieReviewsTvEmpty.setVisibility(View.GONE);
            binding.movieReviewsRv.addItemDecoration(decoration);
            binding.movieReviewsRv.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
            binding.movieReviewsRv.setAdapter(mAdapter);
        }
        else {
            binding.movieReviewsTvEmpty.setVisibility(View.VISIBLE);
            binding.movieReviewsTvEmpty.setText(R.string.no_reviews_found);
        }
    }

    @Override
    public void init(Movie movie) {
        mAdapter = new ReviewsRvAdapter(movie.getReview());
    }

    @Override
    public ViewBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentMovieReviewsBinding.inflate(inflater,container,false);
    }
}
