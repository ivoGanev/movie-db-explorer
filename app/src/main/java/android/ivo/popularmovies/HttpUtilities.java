package android.ivo.popularmovies;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class HttpUtilities {
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
            Log.d(TAG, "getJsonData: " + url.toString());
            in = urlConnection.getInputStream();
            InputStreamReader streamReader = new InputStreamReader(in, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(streamReader);

            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200) {

                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    Log.d(TAG, "getJsonData: " + line);
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

    static List<Movie> parseJsonToMovie(URL url) {
        List<Movie> result = new ArrayList<>();
        JSONObject jsonData = null;

        try {
            jsonData = new JSONObject(getJsonData(url));
            JSONArray movies = jsonData.getJSONArray("results");
            for (int i = 0; i < movies.length(); i++) {
                JSONObject movieElement = movies.getJSONObject(i);
                String title = movieElement.getString("original_title");
                Movie movie = new Movie.Builder(title).build();

                result.add(movie);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
