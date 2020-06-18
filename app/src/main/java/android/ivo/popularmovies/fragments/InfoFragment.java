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

            mBinding.tvDetailsTitle.setText(movie.getTitle());
            mBinding.tvPlotSynopsis.setText(movie.getPlotSynopsis());
            mBinding.tvRating.setText(movie.getVoteAverage());
            mBinding.tvReleaseDate.setText(movie.getReleaseDate());

            GradientDrawable ratingCircle = (GradientDrawable) mBinding.tvRating.getBackground();
            int color = movie.getRatingColor(requireContext());
            ratingCircle.setColor(color);

//            for (Map.Entry<Class<? extends Component>, Component> classComponentEntry : movie.getComponents().entrySet()) {
//                Log.d("TAG", "onCreateView: " + movie.getComponent(Review.class).toString());
//            }
        } else {
            throw new IllegalStateException("A bundle with movie information should always be in the fragment's arguments.");
        }
        return view;
    }

    @Override
    public void onDestroy() {
        mBinding = null;
        super.onDestroy();
    }
}
