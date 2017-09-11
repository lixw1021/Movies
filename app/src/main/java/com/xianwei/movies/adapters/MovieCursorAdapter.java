package com.xianwei.movies.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.xianwei.movies.DetailActivity;
import com.xianwei.movies.Movie;
import com.xianwei.movies.R;
import com.xianwei.movies.data.MovieContract.MovieEntry;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xianwei li on 9/10/2017.
 */

public class MovieCursorAdapter extends CursorAdapter {
    private static final String EXTRA_MOVIE = "movie";
    private Context context;

    public MovieCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
        this.context = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        final ViewHolder viewHolder = (ViewHolder) view.getTag();

        String posterUriString = cursor.getString(cursor.getColumnIndexOrThrow(MovieEntry.COLUMN_MOVIE_POSTER_URL));
        Picasso.with(context)
                .load(posterUriString)
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_broken_image)
                .into(viewHolder.posterIv);

        String movieId = cursor.getString(cursor.getColumnIndexOrThrow(MovieEntry.COLUMN_MOVIE_ID));
        viewHolder.movie.setId(movieId);

        String movieTitle = cursor.getString(cursor.getColumnIndexOrThrow(MovieEntry.COLUMN_MOVIE_TITLE));
        viewHolder.movie.setTitle(movieTitle);

        String moviePosterUriString = cursor.getString(cursor.getColumnIndexOrThrow(MovieEntry.COLUMN_MOVIE_POSTER_URL));
        viewHolder.movie.setPosterUriString(moviePosterUriString);

        String movieBackgroundUriString = cursor.getString(cursor.getColumnIndexOrThrow(MovieEntry.COLUMN_MOVIE_BACKGROUND_IMAGE_URL));
        viewHolder.movie.setBackgroundUriString(movieBackgroundUriString);

        String movieReleaseData = cursor.getString(cursor.getColumnIndexOrThrow(MovieEntry.COLUMN_MOVIE_RELEASE_DATE));
        viewHolder.movie.setReleaseDate(movieReleaseData);

        String movieRate = cursor.getString(cursor.getColumnIndexOrThrow(MovieEntry.COLUMN_MOVIE_AVERAGE_RATE));
        viewHolder.movie.setAverageVote(movieRate);

        String moviePlot = cursor.getString(cursor.getColumnIndexOrThrow(MovieEntry.COLUMN_MOVIE_PLOT_SYNOPSIS));
        viewHolder.movie.setPlotSynopsis(moviePlot);
    }

    public class ViewHolder {
        @BindView(R.id.iv_item)
        ImageView posterIv;
        public Movie movie;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(EXTRA_MOVIE, movie);
                    context.startActivity(intent);
                }
            });
        }
    }
}
