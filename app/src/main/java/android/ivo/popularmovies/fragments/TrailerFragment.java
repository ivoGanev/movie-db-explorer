package android.ivo.popularmovies.fragments;


import android.ivo.popularmovies.databinding.FragmentMovieTrailerBinding;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TrailerFragment extends Fragment {
    FragmentMovieTrailerBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentMovieTrailerBinding.inflate(inflater, container, false);
        View view = mBinding.getRoot();

        return view;
    }

    @Override
    public void onDestroy() {
        mBinding = null;
        super.onDestroy();
    }
}
