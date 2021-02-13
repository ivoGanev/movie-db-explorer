package android.ivo.popularmovies.network;

import android.ivo.popularmovies.models.Movie;
import android.ivo.popularmovies.models.MovieInfo;
import android.ivo.popularmovies.models.Review;
import android.ivo.popularmovies.models.Trailer;

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

    List<Movie> modelMovieList(String movieUrl) {
        List<Movie> result = new ArrayList<>();

        JSONObject movieJson;
        try {
            movieJson = new JSONObject(movieUrl);
            JSONArray array = movieJson.getJSONArray("results");
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

    List<Review> modelReviewList(String reviewUrl) {
        List<Review> reviews = new ArrayList<>();

        JSONObject reviewJson;
        try {
            reviewJson = new JSONObject(reviewUrl);
            JSONArray array = reviewJson.getJSONArray("results");
            for (int i = 0; i < array.length(); i++) {
                JSONObject element = array.getJSONObject(i);
                String author = element.getString("author");
                String content = element.getString("content");
                reviews.add(new Review(author, content));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    public List<Trailer> modelTrailerList(String trailerUrl) {
        List<Trailer> trailers = new ArrayList<>();

        JSONObject trailerJson;
        try {
            trailerJson = new JSONObject(trailerUrl);
            JSONArray array = trailerJson.getJSONArray("results");
            for (int i = 0; i < array.length(); i++) {
                JSONObject element = array.getJSONObject(i);
                String youtubeKey = element.getString("key");
                String site = element.getString("site");
                String name = element.getString("name");
                trailers.add(new Trailer(youtubeKey, site, name));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return trailers;
    }
}
