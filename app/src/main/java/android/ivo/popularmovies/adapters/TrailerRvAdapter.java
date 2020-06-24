package android.ivo.popularmovies.adapters;

import android.ivo.popularmovies.R;
import android.ivo.popularmovies.databinding.FragmentTrailersRvItemBinding;
import android.ivo.popularmovies.models.Trailer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TrailerRvAdapter extends RecyclerView.Adapter<TrailerRvAdapter.ViewHolder> {
    private List<Trailer> mTrailers;

    public TrailerRvAdapter(List<Trailer> trailers) {
        mTrailers = trailers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_trailers_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mBinding.trailerEtName.setText(mTrailers.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mTrailers.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        FragmentTrailersRvItemBinding mBinding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mBinding = FragmentTrailersRvItemBinding.bind(itemView);
        }
    }
}
