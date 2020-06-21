package android.ivo.popularmovies.network.models;

import android.os.Parcel;
import android.os.Parcelable;

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
    private final Review mReview;
    private final Trailer mTrailer;

    public Movie(final MovieInfo movieInfo) {
        mMovieInfo = movieInfo;
        mReview = null;
        mTrailer = null;
    }

    public Movie(MovieInfo movieInfo, Review review, Trailer trailer) {
        mMovieInfo = movieInfo;
        mReview = review;
        mTrailer = trailer;
    }

    protected Movie(Parcel in) {
        mMovieInfo = in.readParcelable(MovieInfo.class.getClassLoader());
        mReview = in.readParcelable(Review.class.getClassLoader());
        mTrailer = in.readParcelable(Trailer.class.getClassLoader());
    }

    public MovieInfo getMovieInfo() {
        return mMovieInfo;
    }

    public Review getReview() {
        return mReview;
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
        dest.writeParcelable(mMovieInfo, 0);
        dest.writeParcelable(mReview, 1);
        dest.writeParcelable(mTrailer, 2);
    }
}
