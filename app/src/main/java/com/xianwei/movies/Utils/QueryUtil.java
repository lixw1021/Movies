package com.xianwei.movies.Utils;

import android.util.Log;

import com.xianwei.movies.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xianwei li on 8/29/2017.
 */

public class QueryUtil {

    private static final String RESULTS = "results";
    private static final String TITLE = "title";
    private static final String VOTE_AVERAGE = "vote_average";
    private static final String OVERVIEW = "overview";
    private static final String RELEASE_DATE = "release_date";
    private static final String POSTER_PATH = "poster_path";
    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w185";

    private static String LOG_TAG = QueryUtil.class.getName();

    public static List<Movie> fetchMoviesList(String urlString) {
        String jsonResponse = jsonStringFromUrlString(urlString);
        return extractJson(jsonResponse);
    }

    private static List<Movie> extractJson(String jsonResponse) {
        if (jsonResponse == null) {
            return null;
        }

        List<Movie> movies = new ArrayList<>();
        try {
            JSONObject response = new JSONObject(jsonResponse);
            JSONArray results = null;
            if (response.has(RESULTS)) {
                results = response.getJSONArray(RESULTS);
                for (int i = 0; i < results.length(); i++) {
                    JSONObject item = results.getJSONObject(i);
                    String title = null;
                    String vote = null;
                    String overview = null;
                    String releaseDate = null;
                    String imagePath = null;

                    if (item.has(TITLE)) {
                        title = item.getString(TITLE);
                    }
                    if (item.has(VOTE_AVERAGE)) {
                        vote = item.getString(VOTE_AVERAGE);
                    }
                    if (item.has(OVERVIEW)) {
                        overview = item.getString(OVERVIEW);
                    }
                    if (item.has(RELEASE_DATE)) {
                        releaseDate = item.getString(RELEASE_DATE);
                    }
                    if (item.has(POSTER_PATH)) {
                        imagePath = item.getString(POSTER_PATH);
                    }

                    movies.add(new Movie(title, urlStringFromPath(imagePath), releaseDate, vote, overview));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movies;
    }

    private static String jsonStringFromUrlString(String urlString) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(urlString).build();
        Response response = null;
        String jsonResponse = null;
        try {
            response = client.newCall(request).execute();
            jsonResponse = response.body().string();
        } catch (IOException e) {
            Log.e(LOG_TAG, e.toString());
            return null;
        }
        return jsonResponse;
    }

    private static String urlStringFromPath (String imagePath) {
        return IMAGE_BASE_URL + imagePath;
    }
}
