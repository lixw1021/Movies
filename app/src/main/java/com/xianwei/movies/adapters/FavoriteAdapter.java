package com.xianwei.movies.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private static final String EXTRA_MOVIE = "movie";
    private Context context;
    private Cursor cursor;

    public FavoriteAdapter(Context context) {
        this.context = context;
    }

    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteAdapter.ViewHolder holder, int position) {
        if (cursor == null || cursor.getCount() ==0) {
            return;
        }
        cursor.moveToPosition(position);
        String posterUriString = cursor.getString(cursor.getColumnIndexOrThrow(MovieEntry.COLUMN_MOVIE_POSTER_URL));
        Picasso.with(context)
                .load(posterUriString)
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_broken_image)
                .into(holder.posterIv);
    }

    @Override
    public int getItemCount() {
        if (cursor == null) {
            return 0;
        }
        return cursor.getCount();
    }

    public void swapCursor(Cursor cursor) {
        this.cursor = cursor;
        if (cursor != null) {
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item)
        ImageView posterIv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    Movie movie = getCurrentMovie(getAdapterPosition());
                    intent.putExtra(EXTRA_MOVIE, movie);
                    context.startActivity(intent);
                }
            });
        }
    }

    private Movie getCurrentMovie(int adapterPosition) {

        cursor.moveToPosition(adapterPosition);
        Movie movie = new Movie();
        String movieId = cursor.getString(cursor.getColumnIndexOrThrow(MovieEntry.COLUMN_MOVIE_ID));
        movie.setId(movieId);

        String movieTitle = cursor.getString(cursor.getColumnIndexOrThrow(MovieEntry.COLUMN_MOVIE_TITLE));
        movie.setTitle(movieTitle);

        String moviePosterUriString = cursor.getString(cursor.getColumnIndexOrThrow(MovieEntry.COLUMN_MOVIE_POSTER_URL));
        movie.setPosterUriString(moviePosterUriString);

        String movieBackgroundUriString = cursor.getString(cursor.getColumnIndexOrThrow(MovieEntry.COLUMN_MOVIE_BACKGROUND_IMAGE_URL));
        movie.setBackgroundUriString(movieBackgroundUriString);

        String movieReleaseData = cursor.getString(cursor.getColumnIndexOrThrow(MovieEntry.COLUMN_MOVIE_RELEASE_DATE));
        movie.setReleaseDate(movieReleaseData);

        String movieRate = cursor.getString(cursor.getColumnIndexOrThrow(MovieEntry.COLUMN_MOVIE_AVERAGE_RATE));
        movie.setAverageVote(movieRate);

        String moviePlot = cursor.getString(cursor.getColumnIndexOrThrow(MovieEntry.COLUMN_MOVIE_PLOT_SYNOPSIS));
        movie.setPlotSynopsis(moviePlot);

        return movie;
    }

}
