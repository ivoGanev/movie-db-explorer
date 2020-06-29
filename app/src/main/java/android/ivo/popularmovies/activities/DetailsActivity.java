package android.ivo.popularmovies.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.ivo.popularmovies.AppExecutors;
import android.ivo.popularmovies.BundleKeys;
import android.ivo.popularmovies.R;
import android.ivo.popularmovies.activities.viewmodels.DetailsViewModel;
import android.ivo.popularmovies.database.AppDao;
import android.ivo.popularmovies.database.AppDatabase;
import android.ivo.popularmovies.models.Movie;
import android.ivo.popularmovies.databinding.ActivityMovieDetailsBinding;
import android.ivo.popularmovies.adapters.DetailsPagerAdapter;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMovieDetailsBinding mBinding;
    private DetailsViewModel mViewModel;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        Movie movie = getIntent()
                .getParcelableExtra(BundleKeys.MOVIE_BUNDLE_KEY);

        mViewModel = new ViewModelProvider(this)
                .get(DetailsViewModel.class);

        if(movie==null)
            throw new NullPointerException("Couldn't retrieve a Movie parcel.");

        mViewModel.setMovie(movie);
        initViewPager(movie);

        mBinding.tlMovieDetails.setupWithViewPager(mViewPager);
        mBinding.btnMakeFavorite.setOnClickListener(this);

        final Observer<Boolean> markedAsFavoriteObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                setFavouriteButtonImage(aBoolean);
                toastShowFavouriteStatus(aBoolean);
            }
        };
        mViewModel.movieMarkedAsFavorite().observe(this, markedAsFavoriteObserver);

        final Context context = this;

        AppExecutors.getInstance().getDiskIOExecutor().execute(new Runnable() {
            @Override
            public void run() {
                AppDao dao = AppDatabase.getInstance(context).dao();
                boolean inDatabase = dao.getMovieInfo(mViewModel.getMovieInfo().getId()) != null;
                setFavouriteButtonImage(inDatabase);
            }
        });

        Picasso.get().load(mViewModel.getPosterImageUrl()).into(mBinding.imgMovieDetail);
    }

    private void initViewPager(Movie movie) {
        Bundle bundle = new Bundle();
        mViewPager = mBinding.vpMovieDetails;
        bundle.putParcelable(BundleKeys.MOVIE_BUNDLE_KEY, movie);
        mViewPager.setAdapter(new DetailsPagerAdapter(getSupportFragmentManager(), this, bundle));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_make_favorite) {
            Bitmap posterImage = ((BitmapDrawable) mBinding.imgMovieDetail.getDrawable()).getBitmap();
            // save asynchronously the current movie in the database,
            // save the bitmap in the file system and
            // finally emit that the movie is marked as favorite
            mViewModel.setCurrentMovieAsFavourite(posterImage);
        }
    }

    private void setFavouriteButtonImage(Boolean add) {
        int imageId = (add)
                ? R.drawable.ic_baseline_favorite_24
                : R.drawable.ic_baseline_favorite_border_24;
        mBinding.btnMakeFavorite.setImageResource(imageId);
    }

    // Shows a toast message if the current movie is in the 'favorites' database
    private void toastShowFavouriteStatus(Boolean inDatabase) {
        String message = (inDatabase)
                ? "added to favourites"
                : "removed from favourites";

        Toast.makeText(this,
                mViewModel.getMovieInfo().getTitle() + " " + message,
                Toast.LENGTH_SHORT).show();
    }
}