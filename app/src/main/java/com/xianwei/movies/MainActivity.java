package com.xianwei.movies;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.xianwei.movies.Utils.QueryUtil;
import com.xianwei.movies.adapters.MovieAdapter;
import com.xianwei.movies.loaders.MovieLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<List<Movie>> {
    @BindView(R.id.rv_main)
    RecyclerView recyclerView;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    private static final String POPULAR_MOVIE = "popular";
    private static final String TOP_RATED_MOVIE = "top_rated";
    private static final int LOADER_ID = 0;
    private String urlString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        urlString = QueryUtil.movieUrlBuilder(this, POPULAR_MOVIE);
        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int id, Bundle args) {
        progressBar.setVisibility(View.VISIBLE);
        return new MovieLoader(this, urlString);
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> movies) {
        progressBar.setVisibility(View.INVISIBLE);
        MovieAdapter movieAdapter = new MovieAdapter(this, movies);
        recyclerView.setAdapter(movieAdapter);
    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {
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
                urlString = QueryUtil.movieUrlBuilder(this, POPULAR_MOVIE);
                getSupportLoaderManager().restartLoader(LOADER_ID, null, this);
                break;
            case R.id.menu_top_rated:
                urlString = QueryUtil.movieUrlBuilder(this, TOP_RATED_MOVIE);
                getSupportLoaderManager().restartLoader(LOADER_ID, null, this);
        }
        return true;
    }
}
