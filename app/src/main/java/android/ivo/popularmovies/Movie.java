package android.ivo.popularmovies;

public class Movie {
    private final String mTitle;
    private final String mReleaseDate;
    private final String mPlotSynopsis;
    private final String mPosterLink;
    private final float mVoteAverage;

    String getTitle() {
        return mTitle;
    }

    String getReleaseDate() {
        return mReleaseDate;
    }

    String getPlotSynopsis() {
        return mPlotSynopsis;
    }

    String getPosterLink() {
        return mPosterLink;
    }

    float getVoteAverage() {
        return mVoteAverage;
    }

    private Movie(Builder builder)
    {
        mTitle = builder.mTitle;
        mReleaseDate = builder.mReleaseDate;
        mPlotSynopsis = builder.mPlotSynopsis;
        mPosterLink = builder.mPosterLink;
        mVoteAverage = builder.mVoteAverage;
    }

    public static class Builder {
        private final String mTitle;
        private String mReleaseDate;
        private String mPlotSynopsis;
        private String mPosterLink;
        private float mVoteAverage;

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
        Builder posterLink(String posterLink) {
            mPosterLink = posterLink;
            return this;
        }

        public Builder voteAverage(float voteAverage) {
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
                ", mPosterLink='" + mPosterLink + '\'' +
                ", mVoteAverage=" + mVoteAverage +
                '}';
    }
}
