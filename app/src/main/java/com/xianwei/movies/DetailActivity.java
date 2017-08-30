package com.xianwei.movies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        setupUI(intent);
    }

    private void setupUI(Intent intent) {
        String movieTitle = intent.getStringExtra("title");
        String movieImageUrl = intent.getStringExtra("imageUrl");
        String movieRate = intent.getStringExtra("averageVote");
        String movieReleaseDate = intent.getStringExtra("releaseDate");
        String moviePlot = intent.getStringExtra("plotSynopsis");

        Picasso.with(this).load(movieImageUrl).into(image);
        title.setText(movieTitle);
        releaseDate.setText(movieReleaseDate);
        rate.setText(movieRate);
        plot.setText(moviePlot);
    }
}
