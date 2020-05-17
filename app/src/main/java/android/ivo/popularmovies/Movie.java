package android.ivo.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    Movie(Parcel in) {
        mTitle = in.readString();
        mReleaseDate = in.readString();
        mPlotSynopsis = in.readString();
        mPosterPath = in.readString();
        mVoteAverage = in.readDouble();
    }

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
    }

    private final String mTitle;
    private final String mReleaseDate;
    private final String mPlotSynopsis;
    private final String mPosterPath;
    private final double mVoteAverage;

    String getTitle() {
        return mTitle;
    }

    String getReleaseDate() {
        return mReleaseDate;
    }

    String getPlotSynopsis() {
        return mPlotSynopsis;
    }

    String getPosterPath() {
        return mPosterPath;
    }

    double getVoteAverage() {
        return mVoteAverage;
    }

    private Movie(Builder builder)
    {
        mTitle = builder.mTitle;
        mReleaseDate = builder.mReleaseDate;
        mPlotSynopsis = builder.mPlotSynopsis;
        mPosterPath = builder.mPosterPath;
        mVoteAverage = builder.mVoteAverage;
    }

    public static class Builder {
        private final String mTitle;
        private String mReleaseDate;
        private String mPlotSynopsis;
        private String mPosterPath;
        private double mVoteAverage;

        Builder(String title)
        {
            mTitle = title;
        }

        Builder releaseDate(String releaseDate) {
            mReleaseDate = releaseDate;
            return this;
        }
        public Builder plotSynopsis(String plotSynopsis) {
            mPlotSynopsis = plotSynopsis;
            return this;
        }
        Builder posterPath(String posterPath) {
            mPosterPath = posterPath;
            return this;
        }

        Builder voteAverage(double voteAverage) {
            mVoteAverage = voteAverage;
            return this;
        }

        Movie build() {
            return new Movie(this);
        }
    }

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
}
