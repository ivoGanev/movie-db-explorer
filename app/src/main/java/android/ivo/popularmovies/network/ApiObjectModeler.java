package android.ivo.popularmovies.network;
import android.ivo.popularmovies.network.models.Movie;
import android.ivo.popularmovies.network.models.MovieInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class ApiObjectModeler {
    MovieInfo modelMovieInfo(JSONObject movieJson) {
        MovieInfo movieInfo = null;
        try {
            String posterFileName = movieJson.getString("poster_path");
            posterFileName = posterFileName.replace("\\", "");

            movieInfo = new MovieInfo.Builder(movieJson.getString("original_title"))
                    .voteAverage(movieJson.getDouble("vote_average"))
                    .releaseDate(movieJson.getString("release_date"))
                    .plotSynopsis(movieJson.getString("overview"))
                    .posterPath(posterFileName)
                    .id(movieJson.getInt("id"))
                    .build();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movieInfo;
    }

    List<Movie> modelMovieList(String moviesAddress) {
        List<Movie> result = new ArrayList<>();

        JSONObject movieObject;
        try {
            movieObject = new JSONObject(moviesAddress);
            JSONArray array = movieObject.getJSONArray("results");
            for (int i = 0; i < array.length(); i++) {
                MovieInfo movieInfo = modelMovieInfo(array.getJSONObject(i));
                Movie movie = new Movie(movieInfo);
                result.add(movie);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }
}
