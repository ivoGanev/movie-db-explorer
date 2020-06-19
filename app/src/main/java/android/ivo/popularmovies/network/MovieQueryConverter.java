package android.ivo.popularmovies.network;

import android.ivo.popularmovies.component.Movie;
import org.json.JSONException;
import org.json.JSONObject;


public class MovieQueryConverter implements JsonToObjectConverter<Movie> {
    @Override
    public Movie convert(JSONObject movieElement) {
        Movie movie = null;
        try {
            String posterFileName = movieElement.getString("poster_path");
            posterFileName = posterFileName.replace("\\", "");

            movie = new Movie.Builder(movieElement.getString("original_title"))
                    .voteAverage(movieElement.getDouble("vote_average"))
                    .releaseDate(movieElement.getString("release_date"))
                    .plotSynopsis(movieElement.getString("overview"))
                    .posterPath(posterFileName)
                    .id(movieElement.getInt("id"))
                    .build();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movie;
    }
}
