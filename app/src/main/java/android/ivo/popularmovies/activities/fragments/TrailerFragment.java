package android.ivo.popularmovies.activities.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.ivo.popularmovies.R;
import android.ivo.popularmovies.adapters.TrailerRvAdapter;
import android.ivo.popularmovies.models.Movie;
import android.ivo.popularmovies.databinding.FragmentMovieTrailerBinding;
import android.ivo.popularmovies.models.Trailer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import java.util.List;

public class TrailerFragment extends MovieBundledFragment implements TrailerRvAdapter.OnClickViewListener {
    private static final String TAG = TrailerFragment.class.getCanonicalName();
    private TrailerRvAdapter mAdapter;
    private Movie mMovie;

    @Override
    public void onDataChanged(Movie movie) {
        mMovie = movie;
        // trailers are loaded asynchronously so the first time the data comes around its size is 0
        // so there is no need to apply logic for them
        if (movie.getTrailers().size() == 0)
            return;

        displayTrailers();
    }

    private void displayTrailers() {
        FragmentMovieTrailerBinding binding = (FragmentMovieTrailerBinding) getInflatedViewBinding();
        boolean displayTrailers = mMovie.getTrailers().size() > 0;
        if (displayTrailers) {
            DividerItemDecoration decoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
            binding.movieTrailerRv.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
            binding.movieTrailerRv.setNestedScrollingEnabled(true);
            binding.movieTrailerRv.addItemDecoration(decoration);
            binding.movieTrailerRv.setAdapter(mAdapter);
            binding.movieTrailerTvNoItems.setVisibility(View.GONE);
        } else {
            binding.movieTrailerTvNoItems.setVisibility(View.VISIBLE);
            binding.movieTrailerTvNoItems.setText(R.string.no_trailers_found);
        }
    }

    @Override
    void onBundleLoad(Movie movie) {
        mAdapter = new TrailerRvAdapter(movie.getTrailers(), this);
    }

    @Override
    public ViewBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentMovieTrailerBinding.inflate(inflater, container, false);
    }

    @Override
    public void onTrailerButtonClicked(int position) {
        String site = mMovie.getTrailers().get(position).getSite();
        String key = mMovie.getTrailers().get(position).getKey();

        if (site.equals("YouTube")) {
            Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + key));
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + key));
            try {
                startActivity(appIntent);
            } catch (ActivityNotFoundException e) {
                startActivity(webIntent);
            }
        }
    }

    @Override
    public void onShareButtonClicked(int position) {
        Trailer trailer = mMovie.getTrailers().get(position);

        ShareCompat.IntentBuilder builder = ShareCompat.IntentBuilder.from(requireActivity());
        Intent shareIntent = builder.setChooserTitle("Share with")
                .setText("https://www.youtube.com/watch?v=" + trailer.getKey())
                .setSubject("Hey, this movie trailer might interest you: " + trailer.getName())
                .setStream(Uri.parse("https://www.youtube.com/watch?v=" + trailer.getKey()))
                .setType("text/plain")
                .createChooserIntent();

        PackageManager packageManager = requireContext().getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(shareIntent, 0);
        boolean safeToStart = activities.size() > 0;

        if (safeToStart) {
            startActivity(shareIntent);
        }
    }
}
