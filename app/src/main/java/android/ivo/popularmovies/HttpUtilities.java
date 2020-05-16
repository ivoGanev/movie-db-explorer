package android.ivo.popularmovies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class HttpUtilities {
    private HttpUtilities() {

    }

    public static String getJsonData(URL url) {
        HttpURLConnection urlConnection = null;
        InputStream in = null;
        StringBuilder jsonResult = new StringBuilder();
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
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

    Movie parseToMovie(String jsonData)
    {
        return null;
    }
}
