package com.xianwei.movies;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.xianwei.movies.Utils.QueryUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Movie>> {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private static final String POPULAR_MOVIE = "popular";
    private static final String TOP_RATED_MOVIE = "top_rated";
    private String urlString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        urlString = QueryUtil.urlBuilder(this, POPULAR_MOVIE);
        getSupportLoaderManager().initLoader(0, null, this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int id, Bundle args) {
        return new MovieLoader(this, urlString);
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> movies) {
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
                urlString = QueryUtil.urlBuilder(this, POPULAR_MOVIE);
                getSupportLoaderManager().restartLoader(0, null, this);
                break;
            case R.id.menu_top_rated:
                urlString = QueryUtil.urlBuilder(this, TOP_RATED_MOVIE);
                getSupportLoaderManager().restartLoader(0, null, this);
        }
        return true;
    }
}
