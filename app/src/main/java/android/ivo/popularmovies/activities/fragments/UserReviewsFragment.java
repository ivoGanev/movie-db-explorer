package android.ivo.popularmovies.activities.fragments;

import android.ivo.popularmovies.R;
import android.ivo.popularmovies.activities.viewmodels.DetailsViewModel;
import android.ivo.popularmovies.activities.viewmodels.DetailsViewModelFactory;
import android.ivo.popularmovies.adapters.ReviewsRvAdapter;
import android.ivo.popularmovies.models.Movie;
import android.ivo.popularmovies.databinding.FragmentMovieReviewsBinding;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

public class UserReviewsFragment extends MovieBundledFragment {
    private DetailsViewModel mViewModel;
    private ReviewsRvAdapter mReviewsRvAdapter;

    @Override
    public void onDataChanged(Movie movie) {
        FragmentMovieReviewsBinding binding = (FragmentMovieReviewsBinding) getInflatedViewBinding();
        boolean displayReviews = movie.getReview().size() > 0;
        if(displayReviews) {
            DividerItemDecoration decoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
            binding.movieReviewsTvEmpty.setVisibility(View.GONE);
            binding.movieReviewsRv.addItemDecoration(decoration);
            binding.movieReviewsRv.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
            binding.movieReviewsRv.setAdapter(mReviewsRvAdapter);
        }
        else {
            binding.movieReviewsTvEmpty.setVisibility(View.VISIBLE);
            binding.movieReviewsTvEmpty.setText(R.string.no_reviews_found);
        }
    }

    @Override
    void onBundleLoad(Movie movie) {

        mReviewsRvAdapter = new ReviewsRvAdapter(movie.getReview());


       // Log.d("TAG", "onChanged: " + mViewModel.getMovie().getValue().getReview().size());

    }

    @Override
    public ViewBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentMovieReviewsBinding.inflate(inflater,container,false);
    }
}
