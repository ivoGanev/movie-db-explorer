package android.ivo.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies")
public class MovieInfo implements Parcelable {
    public static final Creator<MovieInfo> CREATOR = new Creator<MovieInfo>() {
        @Override
        public MovieInfo createFromParcel(Parcel in) {
            return new MovieInfo(in);
        }

        @Override
        public MovieInfo[] newArray(int size) {
            return new MovieInfo[size];
        }
    };

    private final String mTitle;
    private final String mReleaseDate;
    private final String mPlotSynopsis;
    private final String mPosterPath;
    private final double mVoteAverage;

    @PrimaryKey(autoGenerate = false)
    private final int mId;

    @Ignore
    private MovieInfo(Parcel in) {
        mTitle = in.readString();
        mReleaseDate = in.readString();
        mPlotSynopsis = in.readString();
        mPosterPath = in.readString();
        mVoteAverage = in.readDouble();
        mId = in.readInt();
    }

    @Ignore
    private MovieInfo(Builder builder) {
        mTitle = builder.mTitle;
        mReleaseDate = builder.mReleaseDate;
        mPlotSynopsis = builder.mPlotSynopsis;
        mPosterPath = builder.mPosterPath;
        mVoteAverage = builder.mVoteAverage;
        mId = builder.mId;
    }

    /**
     * Used by Room only
     * */
    public MovieInfo(String title, String releaseDate, String plotSynopsis, String posterPath, double voteAverage, int id) {
        mTitle = title;
        mReleaseDate = releaseDate;
        mPlotSynopsis = plotSynopsis;
        mPosterPath = posterPath;
        mVoteAverage = voteAverage;
        mId = id;
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

    public Double getVoteAverage() {
        return mVoteAverage;
    }

    public int getId() { return mId; }

    @Override
    @NonNull
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

        public MovieInfo build() {
            return new MovieInfo(this);
        }
    }
}
