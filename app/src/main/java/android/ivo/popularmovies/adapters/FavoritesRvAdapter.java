package android.ivo.popularmovies.adapters;

import android.ivo.popularmovies.R;
import android.ivo.popularmovies.databinding.ActivityFavoritesRvItemBinding;
import android.ivo.popularmovies.models.MovieInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoritesRvAdapter extends RecyclerView.Adapter<FavoritesRvAdapter.ViewHolder> {
    private List<MovieInfo> mInfoList;

    public FavoritesRvAdapter(List<MovieInfo> infoList) {
        mInfoList = infoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_favorites_rv_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieInfo info = mInfoList.get(position);
        ActivityFavoritesRvItemBinding binding = holder.mBinding;

        binding.favRvItemSynopsis.setText(info.getPlotSynopsis());
        binding.favRvItemDate.setText(info.getReleaseDate());
        binding.favRvItemTitle.setText(info.getTitle());
       // binding.favRvItemImage.setImageBitmap(FileSystem.loadBitmap("path", "filename"));
    }

    @Override
    public int getItemCount() {
        return mInfoList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ActivityFavoritesRvItemBinding mBinding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mBinding = ActivityFavoritesRvItemBinding.bind(itemView);
        }
    }
}
