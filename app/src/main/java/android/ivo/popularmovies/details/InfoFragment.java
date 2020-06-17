package android.ivo.popularmovies.details;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.ivo.popularmovies.Movie;
import android.ivo.popularmovies.R;

import android.ivo.popularmovies.databinding.ActivityMovieDetailsBinding;
import android.ivo.popularmovies.databinding.FragmentMovieInfoBinding;
import android.ivo.popularmovies.uri.MovieDbUriImage;
import android.ivo.popularmovies.uri.MovieUriCreator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

public class InfoFragment extends Fragment {
    private FragmentMovieInfoBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentMovieInfoBinding.inflate(inflater, container, false);
        View view = mBinding.getRoot();

        Bundle bundle = getArguments();
        if (bundle != null) {
            Movie movie = getArguments().getParcelable("movie");
            Log.d("TAG", "onCreateView: aaa" + movie.getTitle());
            mBinding.tvDetailsTitle.setText(movie.getTitle());
            mBinding.tvPlotSynopsis.setText(movie.getPlotSynopsis());
            mBinding.tvRating.setText(movie.getVoteAverage());
            mBinding.tvReleaseDate.setText(movie.getReleaseDate());

            GradientDrawable ratingCircle = (GradientDrawable) mBinding.tvRating.getBackground();
            int color = movie.getRatingColor(requireContext());
            ratingCircle.setColor(color);
        } else {
            throw new IllegalStateException("A bundle with movie information should always be in the fragment's arguments.");
        }
        return view;
    }

    public static Fragment newInstance(Bundle bundle) {
        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onDestroy() {
        mBinding = null;
        super.onDestroy();
    }
}
