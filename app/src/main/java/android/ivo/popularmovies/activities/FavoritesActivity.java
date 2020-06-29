package android.ivo.popularmovies.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.ivo.popularmovies.activities.viewmodels.FavoritesViewModel;
import android.ivo.popularmovies.adapters.FavoritesRvAdapter;
import android.ivo.popularmovies.databinding.ActivityFavoritesBinding;
import android.ivo.popularmovies.models.MovieInfo;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity implements FavoritesRvAdapter.ViewHolder.OnClickViewListener {
    private FavoritesRvAdapter mFavoritesRvAdapter;
    private FavoritesViewModel mViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFavoritesBinding binding = ActivityFavoritesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mViewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);
        mViewModel.getLiveData().observe(this, new Observer<List<MovieInfo>>() {
            @Override
            public void onChanged(List<MovieInfo> movieInfos) {
                mFavoritesRvAdapter.addAll(movieInfos);
                mFavoritesRvAdapter.notifyDataSetChanged();
            }
        });

        mFavoritesRvAdapter= new FavoritesRvAdapter(this, new ArrayList<MovieInfo>(), this);

        RecyclerView recyclerView = binding.activityFavoritesRv;
        DividerItemDecoration decoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(mFavoritesRvAdapter);
    }

    @Override
    public void onRemoveButtonClicked(int position) {
        mViewModel.removeEntry(position);
    }
}