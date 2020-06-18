package android.ivo.popularmovies;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class MovieFragmentFactory {

    public static class Create<T extends Fragment>
    {
        /**
         * A factory method used to create a new Fragment and pass Bundle to it.
         * */
        public T newInstance(Class<T> instance, Bundle bundle)
        {
            T fragment = null;
            try {
                fragment = instance.newInstance();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            }
            fragment.setArguments(bundle);
            return fragment;
        }
    }

}
