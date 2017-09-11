package com.xianwei.movies.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by xianwei li on 9/9/2017.
 */

public final class MovieContract {

    public static final String CONTENT_AUTHORITY = "com.xianwei.movies";
    public static final Uri BASE_CONTENT_RUL = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_MOVIES = "movies";

    public static class MovieEntry implements BaseColumns {

        public static final Uri CONTENT_URL = Uri.withAppendedPath(BASE_CONTENT_RUL, PATH_MOVIES);

        public static final String TABLE_NAME = "movies";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_MOVIE_ID = "id";
        public static final String COLUMN_MOVIE_TITLE = "title";
        public static final String COLUMN_MOVIE_AVERAGE_RATE = "rate";
        public static final String COLUMN_MOVIE_RELEASE_DATE = "date";
        public static final String COLUMN_MOVIE_PLOT_SYNOPSIS = "synopsis";
        public static final String COLUMN_MOVIE_POSTER_URL = "poster";
        public static final String COLUMN_MOVIE_BACKGROUND_IMAGE_URL = "background";
    }
}
