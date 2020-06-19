package android.ivo.popularmovies.network;
import org.json.JSONObject;

public interface JsonToObjectConverter<T> {
    T convert(JSONObject json);
}
