package android.ivo.popularmovies.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.ivo.popularmovies.R;
import android.ivo.popularmovies.databinding.ActivityFavoritesRvItemBinding;
import android.ivo.popularmovies.filesystem.FileSystem;
import android.ivo.popularmovies.models.MovieInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoritesRvAdapter extends RecyclerView.Adapter<FavoritesRvAdapter.ViewHolder> {
    private Context mContext;
    private List<MovieInfo> mInfoList;

    public FavoritesRvAdapter(Context context, List<MovieInfo> infoList) {
        mContext = context;
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

        FileSystem fileSystem = new FileSystem();
        Bitmap bitmap = fileSystem.loadBitmap(mContext.getFilesDir(), Integer.toString(info.getId()));
        binding.favRvItemImage.setImageBitmap(bitmap);
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
