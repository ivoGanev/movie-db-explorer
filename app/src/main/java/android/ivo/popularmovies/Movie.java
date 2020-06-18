package android.ivo.popularmovies;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class Movie implements Parcelable {
    private Movie(Parcel in) {
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

   public String getTitle() {
        return mTitle;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public  String getPlotSynopsis() {
        return mPlotSynopsis;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public String getVoteAverage() {
        return Double.toString(mVoteAverage);
    }

    private Movie(Builder builder)
    {
        mTitle = builder.mTitle;
        mReleaseDate = builder.mReleaseDate;
        mPlotSynopsis = builder.mPlotSynopsis;
        mPosterPath = builder.mPosterPath;
        mVoteAverage = builder.mVoteAverage;
    }

     static class Builder {
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
        Builder plotSynopsis(String plotSynopsis) {
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

    public int getRatingColor(Context context) {
        if(mVoteAverage>= 0 && mVoteAverage <= 2)
            return ContextCompat.getColor(context, R.color.rating_a);
        else if(mVoteAverage> 2 && mVoteAverage <= 4)
            return ContextCompat.getColor(context, R.color.rating_b);
        else if(mVoteAverage> 4 && mVoteAverage <= 6)
            return ContextCompat.getColor(context, R.color.rating_c);
        else if(mVoteAverage> 6 && mVoteAverage <= 8)
            return ContextCompat.getColor(context, R.color.rating_d);
        else
            return ContextCompat.getColor(context, R.color.rating_e);
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
