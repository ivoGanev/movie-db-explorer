package android.ivo.popularmovies.adapters;

import android.ivo.popularmovies.network.models.MovieInfo;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class FavoritesRvAdapter extends RecyclerView.Adapter<FavoritesRvAdapter.ViewHolder>  {
    private List<MovieInfo> mMovieInfos;

    public FavoritesRvAdapter(List<MovieInfo> movieInfos) {
        mMovieInfos = movieInfos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
