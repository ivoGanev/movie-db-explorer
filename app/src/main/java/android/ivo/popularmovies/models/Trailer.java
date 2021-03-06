package android.ivo.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Trailer implements Parcelable {
    public static final Creator<Trailer> CREATOR = new Creator<Trailer>() {
        @Override
        public Trailer createFromParcel(Parcel in) {
            return new Trailer(in);
        }

        @Override
        public Trailer[] newArray(int size) {
            return new Trailer[size];
        }
    };
    private final String mTrailerKey;
    private final String mSite;
    private final String mName;

    public Trailer(String trailerKey, String site, String name) {
        mTrailerKey = trailerKey;
        mSite = site;
        mName = name;
    }

    private Trailer(Parcel in) {
        mTrailerKey = in.readString();
        mSite = in.readString();
        mName = in.readString();
    }

    public String getName() {
        return mName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTrailerKey);
        dest.writeString(mSite);
        dest.writeString(mName);
    }

    public String getKey() {
        return mTrailerKey;
    }

    public String getSite() {
        return mSite;
    }
}
