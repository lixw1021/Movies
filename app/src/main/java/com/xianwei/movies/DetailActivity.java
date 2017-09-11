package com.xianwei.movies;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.xianwei.movies.Utils.QueryUtil;
import com.xianwei.movies.adapters.ReviewAdapter;
import com.xianwei.movies.adapters.TrailerAdapter;
import com.xianwei.movies.data.MovieContract.MovieEntry;
import com.xianwei.movies.loaders.ReviewLoader;
import com.xianwei.movies.loaders.TrailerLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks {

    @BindView(R.id.iv_movie_poster)
    ImageView image;
    @BindView(R.id.tv_movie_title)
    TextView title;
    @BindView(R.id.tv_movie_rate)
    TextView rate;
    @BindView(R.id.tv_movie_release_date)
    TextView releaseDate;
    @BindView(R.id.tv_movie_plot)
    TextView plot;
    @BindView(R.id.ib_favorite)
    ImageButton favoriteButton;
    @BindView(R.id.rv_trailer)
    RecyclerView trailerRecyclerView;
    @BindView(R.id.rv_review)
    RecyclerView reviewRecyclerView;

    private static final String EXTRA_MOVIE = "movie";
    private static final int TRAILER_LOADER = 1;
    private static final int REVIEW_LOADER = 2;

    private Movie movie;
    private String movieId;
    private TrailerAdapter trailerAdapter;
    private ReviewAdapter reviewAdapter;
    private Boolean favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        movie = intent.getExtras().getParcelable(EXTRA_MOVIE);
        movieId = movie.getId();

        if (checkInDb(movieId)) {
            favorite = true;
        } else {
            favorite = false;
        }

        setupBasicInfoUI(movie);
        setupTrailerUI();
        setupReviewUI();
    }

    private boolean checkInDb(String movieId) {
        String queryUri = MovieEntry.CONTENT_URL + "/" + movieId;
        String[] projection = new String[]{movieId};
        Cursor cursor = getContentResolver().query(Uri.parse(queryUri), projection, null, null, null);
        if (cursor.moveToNext()) {
            return true;
        }
        return false;
    }

    private void setupReviewUI() {
        getSupportLoaderManager().initLoader(REVIEW_LOADER, null, this);
        reviewAdapter = new ReviewAdapter(this);

        reviewRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reviewRecyclerView.setAdapter(reviewAdapter);
    }

    private void setupTrailerUI() {
        getSupportLoaderManager().initLoader(TRAILER_LOADER, null, this);
        trailerAdapter = new TrailerAdapter(this);

        trailerRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        trailerRecyclerView.setAdapter(trailerAdapter);
    }

    private void setupBasicInfoUI(Movie movie) {
        String movieTitle = movie.getTitle();
        String movieImageUrl = movie.getBackgroundUriString();
        String movieRate = movie.getAverageVote() + "/10";
        String movieReleaseDate = movie.getReleaseDate();
        String moviePlot = movie.getPlotSynopsis();

        Picasso.with(this)
                .load(movieImageUrl)
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_broken_image)
                .into(image);

        title.setText(movieTitle);
        releaseDate.setText(movieReleaseDate);
        rate.setText(movieRate);
        plot.setText(moviePlot);
        if (favorite) {
            favoriteButton.setImageResource(R.drawable.ic_favorite);
        }
    }

    @OnClick(R.id.ib_favorite)
    void favorite() {
        if (favorite) {
            favoriteButton.setImageResource(R.drawable.ic_unfavorite);
            favorite = false;
            deleteFromDb();
            Toast.makeText(this, "DELETE from DB", Toast.LENGTH_LONG).show();
        } else {
            favoriteButton.setImageResource(R.drawable.ic_favorite);
            favorite = true;
            insertIntoDb();
            Toast.makeText(this, "Add to DB", Toast.LENGTH_LONG).show();
        }
    }

    private void deleteFromDb() {
        getContentResolver().delete(MovieEntry.CONTENT_URL.buildUpon().appendEncodedPath(movieId).build(), null, null);
    }

    private void insertIntoDb() {
        ContentValues values = new ContentValues();
        values.put(MovieEntry.COLUMN_MOVIE_ID, movie.getId());
        values.put(MovieEntry.COLUMN_MOVIE_TITLE, movie.getTitle());
        values.put(MovieEntry.COLUMN_MOVIE_AVERAGE_RATE, movie.getAverageVote());
        values.put(MovieEntry.COLUMN_MOVIE_RELEASE_DATE, movie.getReleaseDate());
        values.put(MovieEntry.COLUMN_MOVIE_PLOT_SYNOPSIS, movie.getPlotSynopsis());
        values.put(MovieEntry.COLUMN_MOVIE_POSTER_URL, movie.getPosterUriString());
        values.put(MovieEntry.COLUMN_MOVIE_BACKGROUND_IMAGE_URL, movie.getBackgroundUriString());

        getContentResolver().insert(MovieEntry.CONTENT_URL, values);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        if (id == TRAILER_LOADER) {
            String url = QueryUtil.trailerUrlBuilder(this, movieId);
            return new TrailerLoader(this, url);
        } else if (id == REVIEW_LOADER) {
            String url = QueryUtil.reviewUrlBuilder(this, movieId);
            return new ReviewLoader(this, url);
        }
        return null;
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public void onLoadFinished(Loader loader, Object data) {
        int id = loader.getId();
        if (id == TRAILER_LOADER) {
            trailerAdapter.setTrailerList((List<Trailer>) data);
        } else if (id == REVIEW_LOADER) {
            reviewAdapter.setReviews((List<String>) data);
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {
        int id = loader.getId();
        if (id == TRAILER_LOADER) {
            trailerAdapter.setTrailerList(null);
        } else if (id == REVIEW_LOADER) {
            reviewAdapter.setReviews(null);
        }
    }
}
