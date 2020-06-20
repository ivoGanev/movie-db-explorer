package android.ivo.popularmovies.component;

import android.os.Parcel;
import android.os.Parcelable;

public class Trailer implements Parcelable {
    public Trailer()
    {

    }

    private Trailer(Parcel in) {
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
