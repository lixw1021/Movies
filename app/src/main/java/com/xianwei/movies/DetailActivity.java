package com.xianwei.movies;

import android.content.Intent;
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

import com.squareup.picasso.Picasso;
import com.xianwei.movies.Utils.QueryUtil;
import com.xianwei.movies.adapters.ReviewAdapter;
import com.xianwei.movies.adapters.TrailerAdapter;
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
    ImageButton starButton;
    @BindView(R.id.rv_trailer)
    RecyclerView trailerRecyclerView;
    @BindView(R.id.rv_review)
    RecyclerView reviewRecyclerView;

    private static final String EXTRA_MOVIE = "movie";
    private static final int TRAILER_LOADER = 1;
    private static final int REVIEW_LOADER = 2;

    private String movieId;
    private TrailerAdapter trailerAdapter;
    private ReviewAdapter reviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Movie movie = intent.getExtras().getParcelable(EXTRA_MOVIE);
        movieId = movie.getId();

        setupBasicInfoUI(movie);
        setupTrailerUI();
        setupReviewUI();
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
        String movieRate = movie.getAverageVote();
        String movieReleaseDate = movie.getReleaseDate();
        String moviePlot = movie.getPlotSynopsis();

        Picasso.with(this)
                .load(movieImageUrl)
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_broken_image)
                .into(image);

        title.setText(movieTitle);
        releaseDate.setText(movieReleaseDate);
        rate.setText(movieRate + "/10");
        plot.setText(moviePlot);
    }

    @OnClick(R.id.ib_favorite)
    void favorite() {
        starButton.setColorFilter(R.color.colorAccent);
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
