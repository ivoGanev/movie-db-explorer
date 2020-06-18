package android.ivo.popularmovies.details;

import android.content.Context;
import android.ivo.popularmovies.MovieFragmentFactory;
import android.ivo.popularmovies.R;

import android.ivo.popularmovies.fragments.TrailerFragment;
import android.ivo.popularmovies.fragments.UserReviewsFragment;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MovieDetailsPagerAdapter extends FragmentPagerAdapter {
    private static final int COUNT = 3;
    private final Context mContext;
    private Bundle mBundle;

    public MovieDetailsPagerAdapter(FragmentManager fragmentManager, Context context, Bundle bundle) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
        mBundle = bundle;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
            return new MovieFragmentFactory.Create<InfoFragment>().newInstance(InfoFragment.class, mBundle);
            case 1:
                return new MovieFragmentFactory.Create<UserReviewsFragment>().newInstance(UserReviewsFragment.class, mBundle);
            case 2:
            return new MovieFragmentFactory.Create<TrailerFragment>().newInstance(TrailerFragment.class, mBundle);
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.tab_information);
            case 1:
                return mContext.getString(R.string.tab_reviews);
            case 2:
                return mContext.getString(R.string.tab_trailer);
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }
    }

    @Override
    public int getCount() {
        return COUNT;
    }

}
