package com.xianwei.movies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.xianwei.movies.data.MovieContract.MovieEntry;

/**
 * Created by xianwei li on 9/9/2017.
 */

public class MovieDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_MOVIES =
            "CREATE TABLE " + MovieEntry.TABLE_NAME + "("
                    + MovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + MovieEntry.COLUMN_MOVIE_ID + " TEXT NOT NULL, "
                    + MovieEntry.COLUMN_MOVIE_TITLE + " TEXT NOT NULL, "
                    + MovieEntry.COLUMN_MOVIE_AVERAGE_RATE + " TEXT NUT NULL DEFAULT 0, "
                    + MovieEntry.COLUMN_MOVIE_RELEASE_DATE + " TEXT NOT NULL, "
                    + MovieEntry.COLUMN_MOVIE_PLOT_SYNOPSIS + " TEXT NOT NULL, "
                    + MovieEntry.COLUMN_MOVIE_POSTER_URL + " TEXT NOT NULL, "
                    + MovieEntry.COLUMN_MOVIE_BACKGROUND_IMAGE_URL + " TEXT NOT NULL)";

    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_MOVIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // not need for the first version
    }
}
