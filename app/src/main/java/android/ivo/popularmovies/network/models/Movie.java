package android.ivo.popularmovies.network.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie implements Parcelable {
    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
    private final MovieInfo mMovieInfo;
    private final ArrayList<Review> mReviews;
    private final Trailer mTrailer;

    public Movie(final MovieInfo movieInfo) {
        mMovieInfo = movieInfo;
        mReviews = new ArrayList<>();
        mTrailer = new Trailer();
    }

    protected Movie(Parcel in) {
        Review[] reviews = new Review[]{};
        mMovieInfo = in.readParcelable(MovieInfo.class.getClassLoader());
        in.readTypedArray(reviews, Review.CREATOR);
        mTrailer = in.readParcelable(Trailer.class.getClassLoader());

        mReviews = new ArrayList<>(Arrays.asList(reviews)) ;
    }

    public MovieInfo getMovieInfo() {
        return mMovieInfo;
    }

    public ArrayList<Review> getReview() {
        return mReviews;
    }

    public Trailer getTrailer() {
        return mTrailer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Parcelable[] reviews = mReviews.toArray(new Parcelable[0]);
        dest.writeParcelable(mMovieInfo, 0);
        dest.writeTypedArray(reviews, 0);
        dest.writeParcelable(mTrailer, 0);
    }
}
