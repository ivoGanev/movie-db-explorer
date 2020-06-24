package android.ivo.popularmovies.adapters;

import android.ivo.popularmovies.R;
import android.ivo.popularmovies.models.Review;
import android.ivo.popularmovies.databinding.FragmentReviewsRvItemBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReviewsRvAdapter extends RecyclerView.Adapter<ReviewsRvAdapter.ViewHolder> {
    private List<Review> mReviews;

    public ReviewsRvAdapter(List<Review> reviews) {
        mReviews = reviews;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_reviews_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mBinding.reviewsTvReview.setText(mReviews.get(position).getReview());
        holder.mBinding.reviewsTvReviewer.setText(mReviews.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private FragmentReviewsRvItemBinding mBinding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mBinding = FragmentReviewsRvItemBinding.bind(itemView);
        }
    }
}
