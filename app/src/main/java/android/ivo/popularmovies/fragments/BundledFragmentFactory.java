package android.ivo.popularmovies.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class BundledFragmentFactory {

    public static <T extends Fragment> T createFragment(Class<T> instance, Bundle bundle)
    {
        T fragment = null;
        try {
            fragment = instance.newInstance();
            fragment.setArguments(bundle);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }

        return fragment;
    }
}
