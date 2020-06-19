package android.ivo.popularmovies.component;

public class ComposedObject<T> {
    private final Composite mComposition;
    private T mObject;

    public ComposedObject(Composite composition, T object) {
        mComposition = composition;
        mObject = object;
    }

    public T getObject() {
        return mObject;
    }

    public Composite getComposition() {
        return mComposition;
    }
}
