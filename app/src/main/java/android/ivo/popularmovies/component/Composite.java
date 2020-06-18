package android.ivo.popularmovies.component;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class Composite implements Parcelable {
    private Map<Class<? extends Component>, Component> mComponents = new HashMap<>();

    public Composite() {
    }

    Composite(Parcel in) {
        in.readMap(mComponents, Composite.class.getClassLoader());
    }

    public static final Creator<Composite> CREATOR = new Creator<Composite>() {
        @Override
        public Composite createFromParcel(Parcel in) {
            return new Composite(in);
        }

        @Override
        public Composite[] newArray(int size) {
            return new Composite[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeMap(mComponents);
    }

    public <T extends Component> void addComponent(Class<T> componentClass, T movieComponent) {
        mComponents.put(componentClass, movieComponent);
    }

    public <T extends Component> T getComponent(Class<T> componentClass) {
        return componentClass.cast(mComponents.get(componentClass));
    }

    public <T extends Component> void removeComponent(Class<T> componentClass) {
        mComponents.remove(componentClass);
    }

    public Map<Class<? extends Component>, Component> getComponents() {
        return mComponents;
    }
}
