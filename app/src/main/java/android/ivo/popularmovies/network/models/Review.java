package android.ivo.popularmovies.network.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Review implements Parcelable {
    private Review(Parcel in) {
        mAuthor = in.readString();
        mReview = in.readString();
    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mAuthor);
        dest.writeString(mReview);
    }

    private final String mAuthor;
    private final String mReview;

    public Review(String author, String review) {
        mAuthor = author;
        mReview = review;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getReview() {
        return mReview;
    }

    @Override
    public String toString() {
        return "Review{" +
                "mAuthor='" + mAuthor + '\'' +
                ", mReview='" + mReview + '\'' +
                '}';
    }
}