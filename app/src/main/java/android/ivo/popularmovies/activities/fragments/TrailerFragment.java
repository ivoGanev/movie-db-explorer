package android.ivo.popularmovies.activities.fragments;


import android.ivo.popularmovies.adapters.TrailerRvAdapter;
import android.ivo.popularmovies.network.models.Movie;
import android.ivo.popularmovies.databinding.FragmentMovieTrailerBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.ivo.popularmovies.databinding.FragmentTrailersRvItemBinding;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

public class TrailerFragment extends MovieBundledFragment {
    @Override
    void onBundleLoad(Movie movie) {
        FragmentMovieTrailerBinding binding = (FragmentMovieTrailerBinding) getInflatedViewBinding();
        TrailerRvAdapter trailerRvAdapter = new TrailerRvAdapter(movie.getTrailers());
        binding.movieTrailerRv.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false));
        binding.movieTrailerRv.setAdapter(trailerRvAdapter);
    }

    @Override
    public ViewBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentMovieTrailerBinding.inflate(inflater, container, false);
    }
}
