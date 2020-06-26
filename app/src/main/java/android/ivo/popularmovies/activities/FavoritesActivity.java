package android.ivo.popularmovies.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.ivo.popularmovies.adapters.FavoritesRvAdapter;
import android.ivo.popularmovies.database.AppDatabase;
import android.ivo.popularmovies.databinding.ActivityFavoritesBinding;
import android.ivo.popularmovies.models.MovieInfo;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity implements FavoritesRvAdapter.ViewHolder.OnClickViewListener {
    private ActivityFavoritesBinding mBinding;
    private List<MovieInfo> mMovieInfos;
    private RecyclerView mRecyclerView;
    private FavoritesRvAdapter mFavoritesRvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityFavoritesBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        AppDatabase database = AppDatabase.getInstance(this);
        mMovieInfos = database.dao().getMovieInfoList();
        mFavoritesRvAdapter= new FavoritesRvAdapter(this, mMovieInfos, this);

        mRecyclerView = mBinding.activityFavoritesRv;
        DividerItemDecoration decoration = new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mRecyclerView.addItemDecoration(decoration);
        mRecyclerView.setAdapter(mFavoritesRvAdapter);
    }

    @Override
    public void onRemoveButtonClicked(int position) {
        AppDatabase database = AppDatabase.getInstance(this);
        database.dao().deleteMovieInfo(mMovieInfos.get(position));

        mMovieInfos = database.dao().getMovieInfoList();
        mFavoritesRvAdapter.addAll(mMovieInfos);
        mFavoritesRvAdapter.notifyItemChanged(position);
    }
}