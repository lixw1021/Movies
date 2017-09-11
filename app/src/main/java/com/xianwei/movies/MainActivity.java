package com.xianwei.movies;

import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.xianwei.movies.Utils.QueryUtil;
import com.xianwei.movies.adapters.FavoriteAdapter;
import com.xianwei.movies.adapters.MovieAdapter;
import com.xianwei.movies.data.MovieContract.MovieEntry;
import com.xianwei.movies.loaders.MovieLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks {
    @BindView(R.id.rv_main)
    RecyclerView recyclerView;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    private static final String POPULAR_MOVIE = "popular";
    private static final String TOP_RATED_MOVIE = "top_rated";
    private static final int POPULAR_LOADER_ID = 10;
    private static final int TOP_RATED_LOADER_ID = 11;
    private static final int DATABASE_LOADER_ID = 12;
    private String urlString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        urlString = QueryUtil.movieUrlBuilder(this, POPULAR_MOVIE);
        getSupportLoaderManager().initLoader(POPULAR_LOADER_ID, null, this);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        }
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        progressBar.setVisibility(View.VISIBLE);
        if (id == POPULAR_LOADER_ID || id == TOP_RATED_LOADER_ID) {
            return new MovieLoader(this, urlString);
        } else if (id == DATABASE_LOADER_ID) {
            String[] projection = new String[]{
                    MovieEntry.COLUMN_MOVIE_ID,
                    MovieEntry.COLUMN_MOVIE_TITLE,
                    MovieEntry.COLUMN_MOVIE_AVERAGE_RATE,
                    MovieEntry.COLUMN_MOVIE_RELEASE_DATE,
                    MovieEntry.COLUMN_MOVIE_PLOT_SYNOPSIS,
                    MovieEntry.COLUMN_MOVIE_POSTER_URL,
                    MovieEntry.COLUMN_MOVIE_BACKGROUND_IMAGE_URL,
            };
            return new CursorLoader(this, MovieEntry.CONTENT_URL, projection, null, null, null);
        }
        return null;
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public void onLoadFinished(Loader loader, Object data) {
        progressBar.setVisibility(View.INVISIBLE);

        if (loader.getId() == POPULAR_LOADER_ID || loader.getId() == TOP_RATED_LOADER_ID) {
            MovieAdapter movieAdapter = new MovieAdapter(this, (List<Movie>) data);
            recyclerView.setAdapter(movieAdapter);
        } else if (loader.getId() == DATABASE_LOADER_ID) {
            FavoriteAdapter favoriteAdapter = new FavoriteAdapter(this);
            recyclerView.setAdapter(favoriteAdapter);
            favoriteAdapter.swapCursor((Cursor) data);
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {
        recyclerView.setAdapter(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_most_popular:
                getSupportActionBar().setTitle(getString(R.string.menu_most_popular));
                urlString = QueryUtil.movieUrlBuilder(this, POPULAR_MOVIE);
                getSupportLoaderManager().destroyLoader(DATABASE_LOADER_ID);
                getSupportLoaderManager().initLoader(POPULAR_LOADER_ID, null, this);
                break;
            case R.id.menu_top_rated:
                getSupportActionBar().setTitle(getString(R.string.menu_top_rated));
                urlString = QueryUtil.movieUrlBuilder(this, TOP_RATED_MOVIE);
                getSupportLoaderManager().destroyLoader(DATABASE_LOADER_ID);
                getSupportLoaderManager().initLoader(TOP_RATED_LOADER_ID, null, this);
                break;
            case R.id.menu_favorite:
                getSupportActionBar().setTitle(getString(R.string.menu_favorite));
                getSupportLoaderManager().initLoader(DATABASE_LOADER_ID, null, this);
        }
        return true;
    }
}
