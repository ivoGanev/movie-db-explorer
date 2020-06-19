package android.ivo.popularmovies.network;

import android.ivo.popularmovies.component.Review;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class ReviewQueryConverter implements JsonToObjectConverter<List<Review>> {
    @Override
    public List<Review> convert(JSONObject json) {
        List<Review> result = new ArrayList<>();
        Review review = new Review("Hello beautiful","darling");

        return result;
    }
}
