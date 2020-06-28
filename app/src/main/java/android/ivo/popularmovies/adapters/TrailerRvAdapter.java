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
    private final List<Trailer> mTrailers;
    private final OnClickViewListener mListener;

    public TrailerRvAdapter(List<Trailer> trailers, @NonNull OnClickViewListener listener) {
        mTrailers = trailers;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_trailers_rv_item, parent, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mBinding.trailerEtName.setText(mTrailers.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mTrailers.size();
    }

   public interface OnClickViewListener {
        void onTrailerButtonClicked(int position);

        void onShareButtonClicked(int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final OnClickViewListener mListener;
        private final FragmentTrailersRvItemBinding mBinding;

        public ViewHolder(@NonNull View itemView, OnClickViewListener listener) {
            super(itemView);
            mListener = listener;
            mBinding = FragmentTrailersRvItemBinding.bind(itemView);
            mBinding.trailerBtnLink.setOnClickListener(this);
            mBinding.trailerBtnShare.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                if (v.getId() == R.id.trailer_btn_link) {
                    mListener.onTrailerButtonClicked(getAdapterPosition());
                } else if (v.getId() == R.id.trailer_btn_share) {
                    mListener.onShareButtonClicked(getAdapterPosition());
                }
            }
        }
    }
}
