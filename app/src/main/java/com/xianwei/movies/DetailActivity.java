package com.xianwei.movies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

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

    private static final String EXTRA_TITLE = "title";
    private static final String EXTRA_IMAGE = "imageUrl";
    private static final String EXTRA_VOTE = "averageVote";
    private static final String EXTRA_DATE = "releaseDate";
    private static final String EXTRA_PLOT = "plotSynopsis";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        setupUI(intent);
    }

    private void setupUI(Intent intent) {
        String movieTitle = intent.getStringExtra(EXTRA_TITLE);
        String movieImageUrl = intent.getStringExtra(EXTRA_IMAGE);
        String movieRate = intent.getStringExtra(EXTRA_VOTE);
        String movieReleaseDate = intent.getStringExtra(EXTRA_DATE);
        String moviePlot = intent.getStringExtra(EXTRA_PLOT);

        Picasso.with(this).load(movieImageUrl).into(image);
        title.setText(movieTitle);
        releaseDate.setText(movieReleaseDate);
        rate.setText(movieRate);
        plot.setText(moviePlot);
    }
}
