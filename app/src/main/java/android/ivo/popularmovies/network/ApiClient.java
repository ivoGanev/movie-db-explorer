package android.ivo.popularmovies.network;

import android.ivo.popularmovies.AppExecutors;
import android.ivo.popularmovies.BuildConfig;
import android.ivo.popularmovies.network.models.Movie;
import android.ivo.popularmovies.network.uri.MdbDiscover;
import android.ivo.popularmovies.network.uri.MdbImage;

import androidx.lifecycle.MutableLiveData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ApiClient {

    private AppExecutors mAppExecutors;

    public ApiClient() {
        mAppExecutors = AppExecutors.getInstance();
    }

    public static ApiClient getInstance() {
        return Holder.INSTANCE;
    }

    // needs to be asynchronous
    private String fetchJsonData(String urlAddress) {
        URL url = null;
        try {
            url = new URL(urlAddress);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }

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
                String line;
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

    public void postMovies(final @MdbDiscover.OrderType String orderType, final MutableLiveData<List<Movie>> movies) {
        mAppExecutors.getNetworkExecutor().execute(new Runnable() {
            @Override
            public void run() {
                String urlAddress = UrlAddressBook
                        .queryMovieAddress()
                        .orderBy(orderType)
                        .get();

                String json = fetchJsonData(urlAddress);
                ApiObjectModeler modeler = new ApiObjectModeler();
                List<Movie> movieList = modeler.modelMovieList(json);
                movies.postValue(movieList);
            }
        });
    }

    private static class Holder {
        private static final ApiClient INSTANCE = new ApiClient();
    }

    public static class UrlAddressBook {
        private static final String API_KEY = BuildConfig.API_KEY;

        public static MdbImage queryImageAddress() {
            return new MdbImage(API_KEY);
        }

        public static MdbDiscover queryMovieAddress() {
            return new MdbDiscover(API_KEY);
        }
    }
}