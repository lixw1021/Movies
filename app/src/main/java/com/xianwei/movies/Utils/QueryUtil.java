package com.xianwei.movies.Utils;

import android.util.Log;

import com.xianwei.movies.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xianwei li on 8/29/2017.
 */

public class QueryUtil {

    private static String LOG_TAG = QueryUtil.class.getName();

    public static List<Movie> fetchMoviesList(String urlString) {
        String jsonResponse = jsonStringFromUrlString(urlString);
        return extractJson(jsonResponse);
    }

    private static List<Movie> extractJson(String jsonResponse) {
        if (jsonResponse == null) {
            return null;
        }
        Log.i("jsonResponse",jsonResponse);

        List<Movie> movies = null;
        try {
            JSONObject response = new JSONObject(jsonResponse);
            JSONArray results = null;
            if (response.has("results")) {
                results = response.getJSONArray("results");
                for (int i = 0; i < results.length(); i++) {
                    JSONObject item = results.getJSONObject(i);
                    String title = null;
                    String vote = null;
                    String overview = null;
                    String releaseDate = null;
                    String imagePath = null;

                    if (item.has("title")) {
                        title = item.getString("title");
                    }
                    if (item.has("vote_average")) {
                        vote = item.getString("vote_average");
                    }
                    if (item.has("overview")) {
                        overview = item.getString("overview");
                    }
                    if (item.has("release_date")) {
                        releaseDate = item.getString("release_date");
                    }
                    if (item.has("poster_path")) {
                        imagePath = item.getString("poster_path");
                    }

                    movies.add(new Movie(title, imagePath, releaseDate, vote, overview));
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
}
