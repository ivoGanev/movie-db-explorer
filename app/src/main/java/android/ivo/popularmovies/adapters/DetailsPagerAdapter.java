package android.ivo.popularmovies.adapters;

import android.content.Context;
import android.ivo.popularmovies.activities.fragments.BundledFragmentFactory;
import android.ivo.popularmovies.R;

import android.ivo.popularmovies.activities.fragments.InfoFragment;
import android.ivo.popularmovies.activities.fragments.TrailerFragment;
import android.ivo.popularmovies.activities.fragments.UserReviewsFragment;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class DetailsPagerAdapter extends FragmentPagerAdapter {
    private static final int COUNT = 3;
    private final Context mContext;
    private final Bundle mBundle;

    public DetailsPagerAdapter(FragmentManager fragmentManager, Context context, Bundle bundle) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
        mBundle = bundle;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return BundledFragmentFactory.createFragment(InfoFragment.class, mBundle);
            case 1:
                return BundledFragmentFactory.createFragment(UserReviewsFragment.class, mBundle);
            case 2:
                return BundledFragmentFactory.createFragment(TrailerFragment.class, mBundle);
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