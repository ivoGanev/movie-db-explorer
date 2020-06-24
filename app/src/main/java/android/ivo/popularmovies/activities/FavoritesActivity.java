package android.ivo.popularmovies.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.ivo.popularmovies.adapters.FavoritesRvAdapter;
import android.ivo.popularmovies.database.AppDatabase;
import android.ivo.popularmovies.databinding.ActivityFavoritesBinding;
import android.ivo.popularmovies.models.MovieInfo;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity {
    ActivityFavoritesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavoritesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        AppDatabase database = AppDatabase.getInstance(this);
        List<MovieInfo> infoList = database.dao().getMovieInfoList();
        FavoritesRvAdapter adapter = new FavoritesRvAdapter(infoList);
        RecyclerView recyclerView = binding.activityFavoritesRv;
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }
}