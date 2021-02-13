package android.ivo.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;

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
    private final ArrayList<Trailer> mTrailers;

    public Movie(final MovieInfo movieInfo) {
        mMovieInfo = movieInfo;
        mReviews = new ArrayList<>();
        mTrailers = new ArrayList<>();
    }

    protected Movie(Parcel in) {
        Review[] reviews = new Review[]{};
        Trailer[] trailers = new Trailer[]{};

        mMovieInfo = in.readParcelable(MovieInfo.class.getClassLoader());
        in.readTypedArray(reviews, Review.CREATOR);
        in.readTypedArray(trailers, Trailer.CREATOR);

        mReviews = new ArrayList<>(Arrays.asList(reviews));
        mTrailers = new ArrayList<>(Arrays.asList(trailers));
    }

    public MovieInfo getMovieInfo() {
        return mMovieInfo;
    }

    public ArrayList<Review> getReview() {
        return mReviews;
    }

    public ArrayList<Trailer> getTrailers() {
        return mTrailers;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Parcelable[] reviews = mReviews.toArray(new Parcelable[0]);
        Parcelable[] trailers = mTrailers.toArray(new Parcelable[0]);
        dest.writeParcelable(mMovieInfo, 0);
        dest.writeTypedArray(reviews, 0);
        dest.writeTypedArray(trailers, 0);
    }
}
