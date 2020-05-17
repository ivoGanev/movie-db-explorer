package android.ivo.popularmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.ivo.popularmovies.databinding.ActivityMovieDetailsBinding;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {
    ActivityMovieDetailsBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        View v = mBinding.getRoot();
        setContentView(v);

        Intent i = getIntent();
        Movie movie = i.getParcelableExtra("movie");

        Picasso.get().load(movie.getPosterPath()).into(mBinding.imgMovieDetail);
        mBinding.tvDetailsTitle.setText(movie.getTitle());
        mBinding.tvPlotSynopsis.setText(movie.getPlotSynopsis());
        mBinding.tvRating.setText(movie.getVoteAverage());
        mBinding.tvReleaseDate.setText(movie.getReleaseDate());
     //   Toast.makeText(this, movie.toString(), Toast.LENGTH_SHORT).show();
    }
}
