package com.xianwei.movies;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
import com.xianwei.movies.model.Movie;
import com.xianwei.movies.model.Review;
import com.xianwei.movies.model.Trailer;

import java.util.ArrayList;
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
    @BindView(R.id.empty_review_tv)
    TextView emptyReviewTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private static final String EXTRA_MOVIE = "movie";
    private static final int TRAILER_LOADER = 20;
    private static final int REVIEW_LOADER = 21;

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

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            if (extras.containsKey(EXTRA_MOVIE)) {
                movie = intent.getExtras().getParcelable(EXTRA_MOVIE);
                movieId = String.valueOf(movie.getId());
                favorite = checkInDb(movieId);

                setupBasicInfoUI(movie);
                setupTrailerUI();
                setupReviewUI();
            }
        }
    }

    private boolean checkInDb(String movieId) {
        String queryUri = MovieEntry.CONTENT_URL + "/" + movieId;
        String[] projection = new String[]{movieId};
        Cursor cursor = getContentResolver().query(Uri.parse(queryUri), projection, null, null, null);
        if (cursor != null && cursor.moveToNext()) {
            cursor.close();
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

        trailerRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        trailerRecyclerView.setAdapter(trailerAdapter);
    }

    private void setupBasicInfoUI(Movie movie) {
        String movieTitle = movie.getTitle();
        String movieImageUrl = movie.getBackdropPath();
        String movieRate = movie.getVoteAverage() + "/10";
        String movieReleaseDate = movie.getReleaseDate();
        String moviePlot = movie.getOverview();

        Picasso.with(this)
                .load(QueryUtil.urlStringFromBackgroundPath(movieImageUrl))
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
        values.put(MovieEntry.COLUMN_MOVIE_AVERAGE_RATE, movie.getVoteAverage());
        values.put(MovieEntry.COLUMN_MOVIE_RELEASE_DATE, movie.getReleaseDate());
        values.put(MovieEntry.COLUMN_MOVIE_PLOT_SYNOPSIS, movie.getOverview());
        values.put(MovieEntry.COLUMN_MOVIE_POSTER_URL, movie.getPosterPath());
        values.put(MovieEntry.COLUMN_MOVIE_BACKGROUND_IMAGE_URL, movie.getBackdropPath());

        getContentResolver().insert(MovieEntry.CONTENT_URL, values);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        if (id == TRAILER_LOADER) {
            return new TrailerLoader(this, movie.getId());
        } else if (id == REVIEW_LOADER) {
            return new ReviewLoader(this, movie.getId());
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
            List<Review> reviews = (List<Review>) data;
            if (reviews != null && reviews.size() > 0) {
                List<String> reviewContent = new ArrayList<>();
                emptyReviewTv.setVisibility(View.INVISIBLE);
                reviewContent = getReviewContent(reviews);
                reviewAdapter.setReviews(reviewContent);
            } else {
                emptyReviewTv.setVisibility(View.VISIBLE);
            }
        }
    }

    private List<String> getReviewContent(List<Review> reviews) {
        if (reviews == null || reviews.size() == 0) return null;
        List<String> reviewContent = new ArrayList<>();
        for (int i = 0; i < reviews.size(); i++) {
            reviewContent.add(reviews.get(i).getContent());
        }
        return reviewContent;
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
