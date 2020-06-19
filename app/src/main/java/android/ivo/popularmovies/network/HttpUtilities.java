package android.ivo.popularmovies.network;

import android.ivo.popularmovies.component.Movie;
import android.ivo.popularmovies.component.Review;
import android.ivo.popularmovies.component.Trailer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

// TODO: This class needs to be refactored to work as a DI
public class HttpUtilities {
    private static final String TAG = HttpUtilities.class.getSimpleName();

    private HttpUtilities() {

    }

    private static String getJsonData(URL url) {
        HttpURLConnection urlConnection = null;
        InputStream in = null;
        StringBuilder jsonResult = new StringBuilder();

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            in = urlConnection.getInputStream();
            InputStreamReader streamReader = new InputStreamReader(in, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(streamReader);

            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200) {

                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    jsonResult.append(line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }

        try {
            if (in != null)
                in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonResult.toString();
    }

    private static String getReviewsJson() {
        return "";
    }

    public static List<Movie> parseJsonToMovie(URL url) {
        String json = getJsonData(url);

        MovieQueryConverter movieQueryConverter = new MovieQueryConverter();
        List<Movie> result = new ArrayList<>();

        JSONObject movieObject;
        try {
            movieObject = new JSONObject(json);
            JSONArray array = movieObject.getJSONArray("results");
            for (int i = 0; i < array.length(); i++) {
                Movie movie = movieQueryConverter.convert(array.getJSONObject(i));

                // TODO: Change mock data to real API data
                movie.addComponent(Review.class, new Review("Morning", "Sunshine"));
                movie.addComponent(Review.class, new Review("Excellent", "Choice"));
                movie.addComponent(Review.class, new Review("Intriguing", "Proposition"));

                result.add(movie);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

//        ReviewQueryConverter reviewQueryConverter = new ReviewQueryConverter();
//        List<Review> reviews = reviewQueryConverter.convert()
        return result;
    }
}
