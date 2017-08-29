package com.xianwei.movies;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.xianwei.movies.Utils.QueryUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Movie>>{
    @BindView(R.id.display)
    TextView display;
    String urlString = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=9d2bf28fa6d0d012175b828880e084d9";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getSupportLoaderManager().initLoader(0, null, this);

    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int id, Bundle args) {
        return new MovieLoader(this, urlString);
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> data) {
        display.setText("\n");
        for (int i = 0; i < data.size(); i++) {
            display.append(data.get(i).getTitle() + "\n");
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {

    }
}
