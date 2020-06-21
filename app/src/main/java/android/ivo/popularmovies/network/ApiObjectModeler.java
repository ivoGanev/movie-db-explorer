package android.ivo.popularmovies.network;
import android.ivo.popularmovies.network.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class ApiObjectModeler {
    Movie toMovie(JSONObject movieJson) {
        Movie movie = null;
        try {
            String posterFileName = movieJson.getString("poster_path");
            posterFileName = posterFileName.replace("\\", "");

            movie = new Movie.Builder(movieJson.getString("original_title"))
                    .voteAverage(movieJson.getDouble("vote_average"))
                    .releaseDate(movieJson.getString("release_date"))
                    .plotSynopsis(movieJson.getString("overview"))
                    .posterPath(posterFileName)
                    .id(movieJson.getInt("id"))
                    .build();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movie;
    }

    List<Movie> toMovieList(String moviesAddress) {

        List<Movie> result = new ArrayList<>();

        JSONObject movieObject;
        try {
            movieObject = new JSONObject(moviesAddress);
            JSONArray array = movieObject.getJSONArray("results");
            for (int i = 0; i < array.length(); i++) {
                Movie movie = toMovie(array.getJSONObject(i));
                result.add(movie);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }
}
