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
    private final ViewHolder.OnClickViewListener mClickViewListener;
    private List<MovieInfo> mInfoList;
    private Context mContext;

    public FavoritesRvAdapter(Context context, List<MovieInfo> infoList, ViewHolder.OnClickViewListener listener) {
        mContext = context;
        mInfoList = infoList;
        mClickViewListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_favorites_rv_item, parent, false);
        return new ViewHolder(itemView, mClickViewListener);
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

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ActivityFavoritesRvItemBinding mBinding;
        private OnClickViewListener mOnClickViewListener;

        public ViewHolder(@NonNull View itemView, OnClickViewListener listener) {
            super(itemView);
            mBinding = ActivityFavoritesRvItemBinding.bind(itemView);
            mBinding.favRvBtnRemove.setOnClickListener(this);
            mOnClickViewListener = listener;
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.fav_rv_btn_remove)
                if (mOnClickViewListener != null)
                    mOnClickViewListener.onRemoveButtonClicked(getAdapterPosition());
        }

        public interface OnClickViewListener {
            void onRemoveButtonClicked(int position);
        }
    }

    public void addAll(List<MovieInfo> movieInfos)
    {
        mInfoList.clear();
        mInfoList.addAll(movieInfos);
        notifyDataSetChanged();
    }

}
