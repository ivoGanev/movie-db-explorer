package android.ivo.popularmovies.network.apimodels;

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

    private final String mTitle;
    private final String mReleaseDate;
    private final String mPlotSynopsis;
    private final String mPosterPath;
    private final double mVoteAverage;
    private final int mId;
    private Review mReview;
    private Trailer mTrailer;

    private Movie(Parcel in) {
        mTitle = in.readString();
        mReleaseDate = in.readString();
        mPlotSynopsis = in.readString();
        mPosterPath = in.readString();
        mVoteAverage = in.readDouble();
        mId = in.readInt();
        mReview = in.readParcelable(Review.class.getClassLoader());
        mTrailer = in.readParcelable(Trailer.class.getClassLoader());
    }

    private Movie(Builder builder) {
        super();
        mTitle = builder.mTitle;
        mReleaseDate = builder.mReleaseDate;
        mPlotSynopsis = builder.mPlotSynopsis;
        mPosterPath = builder.mPosterPath;
        mVoteAverage = builder.mVoteAverage;
        mId = builder.mId;
        mReview = builder.mReview;
        mTrailer = builder.mTrailer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mReleaseDate);
        dest.writeString(mPlotSynopsis);
        dest.writeString(mPosterPath);
        dest.writeDouble(mVoteAverage);
        dest.writeInt(mId);
        dest.writeParcelable(mReview, 0);
        dest.writeParcelable(mTrailer, 0);
    }

    public String getTitle() {
        return mTitle;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getPlotSynopsis() {
        return mPlotSynopsis;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public String getVoteAverage() {
        return Double.toString(mVoteAverage);
    }

    public Review getReview() { return mReview; }

    public Trailer getTrailer() { return mTrailer; }

    public int getId() { return mId; }

    @Override
    public String toString() {
        return "Movie{" +
                "mTitle='" + mTitle + '\'' +
                ", mReleaseDate='" + mReleaseDate + '\'' +
                ", mPlotSynopsis='" + mPlotSynopsis + '\'' +
                ", mPosterLink='" + mPosterPath + '\'' +
                ", mVoteAverage=" + mVoteAverage +
                '}';
    }

    public static class Builder {
        private final String mTitle;
        private String mReleaseDate;
        private String mPlotSynopsis;
        private String mPosterPath;
        private double mVoteAverage;
        private int mId;
        private Review mReview;
        private Trailer mTrailer;

        public Builder(String title) {
            mTitle = title;
        }

        public Builder releaseDate(String releaseDate) {
            mReleaseDate = releaseDate;
            return this;
        }

        public Builder plotSynopsis(String plotSynopsis) {
            mPlotSynopsis = plotSynopsis;
            return this;
        }

        public Builder posterPath(String posterPath) {
            mPosterPath = posterPath;
            return this;
        }

        public Builder voteAverage(double voteAverage) {
            mVoteAverage = voteAverage;
            return this;
        }

        public Builder id(int id) {
            mId = id;
            return this;
        }

        public Builder trailer(Trailer trailer) {
            mTrailer = trailer;
            return this;
        }

        public Builder review(Review review) {
            mReview = review;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }
}
