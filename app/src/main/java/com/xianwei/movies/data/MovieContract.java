package com.xianwei.movies.data;

import android.provider.BaseColumns;

/**
 * Created by xianwei li on 9/9/2017.
 */

public final class MovieContract {

    public static class MovieEntry implements BaseColumns {
        public static final String TABLE_NAME = "movies";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_MOVIE_ID = "id";
        public static final String COLUMN_MOVIE_TITLE = "title";
        public static final String COLUMN_MOVIE_AVERAGE_RATE = "rate";
        public static final String COLUMN_MOVIE_RELEASE_DATE = "date";
        public static final String COLUMN_MOVIE_PLOT_SYNOPSIS = "synopsis";
        public static final String COLUMN_MOVIE_POSTER = "poster";
        public static final String COLUMN_MOVIE_BACKGROUND_IMAGE = "background";
    }
}
