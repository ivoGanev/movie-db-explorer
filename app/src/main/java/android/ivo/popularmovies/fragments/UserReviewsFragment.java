package android.ivo.popularmovies.fragments;

import android.ivo.popularmovies.databinding.FragmentMovieReviewsBinding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UserReviewsFragment extends Fragment {
    FragmentMovieReviewsBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentMovieReviewsBinding.inflate(inflater, container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        mBinding = null;
        super.onDestroy();
    }
}
