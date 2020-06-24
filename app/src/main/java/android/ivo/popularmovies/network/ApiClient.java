package android.ivo.popularmovies.network;

import android.ivo.popularmovies.AppExecutors;
import android.ivo.popularmovies.BuildConfig;
import android.ivo.popularmovies.models.Movie;
import android.ivo.popularmovies.models.MovieInfo;
import android.ivo.popularmovies.models.Review;
import android.ivo.popularmovies.models.Trailer;
import android.ivo.popularmovies.network.uri.MdbDiscover;
import android.ivo.popularmovies.network.uri.MdbImage;
import android.ivo.popularmovies.network.uri.MdbReview;
import android.ivo.popularmovies.network.uri.MdbTrailer;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ApiClient {

    private AppExecutors mAppExecutors;
    private SynchronizedString mApiCall = new SynchronizedString();

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

    private Future<String> fetchJsonDataAsync(final String urlAddress) {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() {
                return fetchJsonData(urlAddress);
            }
        };

        return mAppExecutors.getNetworkExecutor().submit(callable);
    }

    public void postMovies(final @MdbDiscover.OrderType String orderType, final MutableLiveData<List<Movie>> movies) {
        String urlAddress = UrlAddressBook
                .queryMovieAddress()
                .orderBy(orderType)
                .get();

        final Future<String> future = fetchJsonDataAsync(urlAddress);

        mAppExecutors.getNetworkExecutor().execute(new Runnable() {
            List<Movie> movieList;

            @Override
            public void run() {
                try {
                    ApiObjectModeler modeler = new ApiObjectModeler();
                    movieList = modeler.modelMovieList(future.get());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    movies.postValue(movieList);
                }
            }
        });
    }

    public void postReview(@NonNull final MutableLiveData<Movie> movie) {
        MovieInfo movieInfo = getValidMovieInfo(movie);
        String urlAddress = UrlAddressBook.queryMovieReviews(Integer.toString(movieInfo.getId())).get();

        final Future<String> future = fetchJsonDataAsync(urlAddress);
        mAppExecutors.getNetworkExecutor().execute(new Runnable() {
            List<Review> reviews;
            @Override
            public void run() {
                try {
                    ApiObjectModeler modeler = new ApiObjectModeler();
                    String jsonObject = future.get();
                    reviews = modeler.modelReviewList(jsonObject);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    movie.getValue().getReview().addAll(reviews);
                    movie.postValue(movie.getValue());
                }
            }
        });
    }

    public void postTrailer(@NonNull final MutableLiveData<Movie> movie) {
        MovieInfo movieInfo = getValidMovieInfo(movie);
        String urlAddress = UrlAddressBook.queryMovieTrailer(Integer.toString(movieInfo.getId())).get();

        final Future<String> future = fetchJsonDataAsync(urlAddress);
        mAppExecutors.getNetworkExecutor().execute(new Runnable() {
            List<Trailer> trailers;
            @Override
            public void run() {
                try {
                    ApiObjectModeler modeler = new ApiObjectModeler();
                    String jsonObject = future.get();
                    trailers = modeler.modelTrailerList(jsonObject);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    movie.getValue().getTrailers().addAll(trailers);
                    movie.postValue(movie.getValue());
                }
            }
        });
    }

    private MovieInfo getValidMovieInfo(@NonNull MutableLiveData<Movie> movie) {
        MovieInfo movieInfo = movie.getValue().getMovieInfo();
        if (movieInfo == null)
            throw new NullPointerException("Trying to get movie information but none can be found.");
        return movieInfo;
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

        public static MdbReview queryMovieReviews(String movieId) { return new MdbReview(API_KEY, movieId); }

        public static MdbTrailer queryMovieTrailer(String movieId) { return new MdbTrailer(API_KEY, movieId); }
    }

    private static class SynchronizedString {
        private String mString;

        public synchronized String setString(String string) {
            mString = string;
            return mString;
        }

        public synchronized String getString() {
            return mString;
        }
    }

}