package com.xianwei.movies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xianwei li on 8/29/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private List<Movie> movies;
    private Context context;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.movies = movies;
        this.context = context;
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapter.ViewHolder holder, int position) {
        Movie currentMovie = movies.get(position);
        Picasso.with(context).load(currentMovie.getImageUriString()).into(holder.imageView);
        holder.title = currentMovie.getTitle();
        holder.imageUrl = currentMovie.getImageUriString();
        holder.releaseDate = currentMovie.getReleaseDate();
        holder.averageVote = currentMovie.getAverageVote();
        holder.plotSynopsis = currentMovie.getPlotSynopsis();
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item)
        ImageView imageView;

        private String title;
        private String imageUrl;
        private String releaseDate;
        private String averageVote;
        private String plotSynopsis;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("title", title);
                    intent.putExtra("imageUrl", imageUrl);
                    intent.putExtra("releaseDate", releaseDate);
                    intent.putExtra("averageVote", averageVote);
                    intent.putExtra("plotSynopsis", plotSynopsis);
                    context.startActivity(intent);
                }
            });
        }
    }
}
