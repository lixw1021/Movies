package com.xianwei.movies.Utils;

import android.net.Uri;

/**
 * Created by xianwei li on 8/29/2017.
 */

public class QueryUtil {
    private static final String POSTER_SIZE = "w185";
    private static final String BACKGROUND_IMAGE_SIZE = "w342";
    private static final String YOUTUBE_VIDEO_PATH = "watch";
    private static final String YOUTUBE_VIDEO_Query_Parameter = "v";
    private static final String YOUTUBE_IMAGE_PATH = "vi";
    private static final String YOUTUBE_IMAGE_POSTFIX = "0.jpg";

    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p";
    private static final String MOVIE_BASE_URL = "http://api.themoviedb.org/3/movie";
    private static final String YOUTUBE_VIDEO_BASE_URL = "https://www.youtube.com";
    private static final String YOUTUBE_IMAGE_BASE_URL = "https://img.youtube.com";

    private static final int MAX_TRAILER_NUMBER = 6;
    private static String LOG_TAG = QueryUtil.class.getName();

    public static String urlStringFromPosterPath(String imagePath) {
        return Uri.parse(IMAGE_BASE_URL)
                .buildUpon()
                .appendEncodedPath(POSTER_SIZE)
                .appendEncodedPath(imagePath)
                .build().toString();
    }

    public static String urlStringFromBackgroundPath(String imagePath) {
        return Uri.parse(IMAGE_BASE_URL)
                .buildUpon()
                .appendEncodedPath(BACKGROUND_IMAGE_SIZE)
                .appendEncodedPath(imagePath)
                .build().toString();
    }

    public static String youTubeVideoUrlBuilder(String key) {
        return Uri.parse(YOUTUBE_VIDEO_BASE_URL)
                .buildUpon()
                .appendEncodedPath(YOUTUBE_VIDEO_PATH)
                .appendQueryParameter(YOUTUBE_VIDEO_Query_Parameter, key)
                .build().toString();
    }

    public static String youTubeImageUrlBuilder(String key) {
        return Uri.parse(YOUTUBE_IMAGE_BASE_URL)
                .buildUpon()
                .appendEncodedPath(YOUTUBE_IMAGE_PATH)
                .appendEncodedPath(key)
                .appendEncodedPath(YOUTUBE_IMAGE_POSTFIX)
                .build().toString();
    }
}

