package android.ivo.popularmovies.component;

import android.content.Context;
import android.ivo.popularmovies.R;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.core.content.ContextCompat;

public class Movie extends Composite implements Parcelable {
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

    private Movie(Parcel in) {
        super(in);
        mTitle = in.readString();
        mReleaseDate = in.readString();
        mPlotSynopsis = in.readString();
        mPosterPath = in.readString();
        mVoteAverage = in.readDouble();
        mId = in.readInt();
    }

    private Movie(Builder builder) {
        super();
        mTitle = builder.mTitle;
        mReleaseDate = builder.mReleaseDate;
        mPlotSynopsis = builder.mPlotSynopsis;
        mPosterPath = builder.mPosterPath;
        mVoteAverage = builder.mVoteAverage;
        mId = builder.mId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);

        dest.writeString(mTitle);
        dest.writeString(mReleaseDate);
        dest.writeString(mPlotSynopsis);
        dest.writeString(mPosterPath);
        dest.writeDouble(mVoteAverage);
        dest.writeInt(mId);
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

    public int getId() { return mId; }

    public int getRatingColor(Context context) {
        if (mVoteAverage >= 0 && mVoteAverage <= 2)
            return ContextCompat.getColor(context, R.color.rating_a);
        else if (mVoteAverage > 2 && mVoteAverage <= 4)
            return ContextCompat.getColor(context, R.color.rating_b);
        else if (mVoteAverage > 4 && mVoteAverage <= 6)
            return ContextCompat.getColor(context, R.color.rating_c);
        else if (mVoteAverage > 6 && mVoteAverage <= 8)
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

    public static class Builder {
        private final String mTitle;
        private String mReleaseDate;
        private String mPlotSynopsis;
        private String mPosterPath;
        private double mVoteAverage;
        private int mId;

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

        public Movie build() {
            return new Movie(this);
        }
    }
}
