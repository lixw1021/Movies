package com.xianwei.movies.Utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.xianwei.movies.Movie;
import com.xianwei.movies.R;
import com.xianwei.movies.Trailer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xianwei li on 8/29/2017.
 */

public class QueryUtil {

    private static final String RESULTS = "results";
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String VOTE_AVERAGE = "vote_average";
    private static final String OVERVIEW = "overview";
    private static final String RELEASE_DATE = "release_date";
    private static final String POSTER_PATH = "poster_path";
    private static final String BACKGROUND_PATH = "backdrop_path";
    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p";
    private static final String MOVIE_BASE_URL = "http://api.themoviedb.org/3/movie";
    private static final String API_KEY = "api_key";

    private static final String VIDEO_KEY = "key";
    private static final String YOUTUBE_VIDEO_BASE_URL = "https://www.youtube.com";
    private static final String YOUTUBE_IMAGE_BASE_URL = "https://img.youtube.com";

    private static String LOG_TAG = QueryUtil.class.getName();

    public static List<Movie> fetchMoviesList(String urlString) {
        String jsonResponse = jsonStringFromUrlString(urlString);
        return extractMoviesFromJson(jsonResponse);
    }

    private static List<Movie> extractMoviesFromJson(String jsonResponse) {
        if (jsonResponse == null) {
            return null;
        }

        List<Movie> movies = new ArrayList<>();
        try {
            JSONObject response = new JSONObject(jsonResponse);
            JSONArray results;
            if (response.has(RESULTS)) {
                results = response.getJSONArray(RESULTS);
                for (int i = 0; i < results.length(); i++) {
                    JSONObject item = results.getJSONObject(i);
                    String title = null;
                    String id = null;
                    String vote = null;
                    String overview = null;
                    String releaseDate = null;
                    String posterImagePath = null;
                    String backgroundImagePath = null;

                    if (item.has(ID)) {
                        id = item.getString(ID);
                    }
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
                        posterImagePath = item.getString(POSTER_PATH);
                    }
                    if (item.has(BACKGROUND_PATH)) {
                        backgroundImagePath = item.getString(BACKGROUND_PATH);
                    }

                    movies.add(new Movie(title, urlStringFromPosterPath(posterImagePath), urlStringFromBackgroundPath(backgroundImagePath), releaseDate, vote, overview, id));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return movies;
    }


    public static List<Trailer> fetchTrailerInfo(String urlString) {
        String jsonResponse = jsonStringFromUrlString(urlString);
        return extractKeysFromJson(jsonResponse);
    }

    private static List<Trailer> extractKeysFromJson(String jsonResponse) {
        if (jsonResponse == null) {
            return null;
        }

        List<Trailer> trailerList = new ArrayList<>();
        JSONObject response = null;
        try {
            response = new JSONObject(jsonResponse);
            if (response.has(RESULTS)) {
                JSONArray results = response.getJSONArray(RESULTS);
                for (int i = 0; i < results.length() && i < 4; i++) {
                    JSONObject item = results.getJSONObject(i);
                    if (item.has(VIDEO_KEY)) {
                        String key = item.getString(VIDEO_KEY);
                        trailerList.add(
                                new Trailer(youTubeVideoUrlBuilder(key), youTubeImageUrlBuilder(key)));
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return trailerList;
    }


    private static String jsonStringFromUrlString(String urlString) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(urlString).build();
        Response response;
        String jsonResponse;
        try {
            response = client.newCall(request).execute();
            jsonResponse = response.body().string();
        } catch (IOException e) {
            Log.e(LOG_TAG, e.toString());
            return null;
        }
        return jsonResponse;
    }

    private static String urlStringFromPosterPath(String imagePath) {
        return IMAGE_BASE_URL + "/w185" + imagePath;
    }

    private static String urlStringFromBackgroundPath(String imagePath) {
        return IMAGE_BASE_URL + "/w342" + imagePath;
    }

    public static String movieUrlBuilder(Context context, String title) {
        return Uri.parse(MOVIE_BASE_URL)
                .buildUpon()
                .appendEncodedPath(title)
                .appendQueryParameter(API_KEY, context.getString(R.string.api_key))
                .build().toString();
    }

    public static String trailerUrlBuilder(Context context, String id) {
        return Uri.parse(MOVIE_BASE_URL)
                .buildUpon()
                .appendEncodedPath(id)
                .appendEncodedPath("videos")
                .appendQueryParameter(API_KEY, context.getString(R.string.api_key))
                .build().toString();
    }

    private static String youTubeVideoUrlBuilder(String key) {
        return Uri.parse(YOUTUBE_VIDEO_BASE_URL)
                .buildUpon()
                .appendEncodedPath("watch")
                .appendQueryParameter("v", key)
                .build().toString();
    }

    private static String youTubeImageUrlBuilder(String key) {
        return Uri.parse(YOUTUBE_IMAGE_BASE_URL)
                .buildUpon()
                .appendEncodedPath("vi")
                .appendEncodedPath(key)
                .appendEncodedPath("0.jpg")
                .build().toString();
    }
}
