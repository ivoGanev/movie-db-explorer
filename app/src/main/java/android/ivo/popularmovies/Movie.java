package android.ivo.popularmovies;

public class Movie {
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
