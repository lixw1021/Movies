package com.xianwei.movies.mvp.feature.movieList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.xianwei.movies.R;
import com.xianwei.movies.Utils.QueryUtil;
import com.xianwei.movies.model.Movie;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xianweili on 4/22/18.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private Context context;
    private List<Movie> movieList;
    private LayoutInflater layoutInflater;
    private OnMovieItemClickCallback callback;

    @Inject
    public MovieListAdapter(Context context) {
        this.context = context;
        movieList = new ArrayList<>();
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void addAllToList(List<Movie> movieList) {
        this.movieList.clear();
        this.movieList.addAll(movieList);
        notifyItemRangeChanged(0, movieList.size());
    }

    @Override
    public MovieListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.main_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieListAdapter.ViewHolder holder, int position) {
        Movie item = movieList.get(position);
        Picasso.with(context)
                .load(QueryUtil.urlStringFromPosterPath(item.getPosterPath()))
                .placeholder(R.drawable.ic_image)
                .resize(1000,800)
                .error(R.drawable.ic_broken_image)
                .into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item)
        ImageView itemImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                callback.onMovieItemClicked(getAdapterPosition());
            });
        }
    }

    public void setCallback(OnMovieItemClickCallback callback) {
        this.callback = callback;
    }

    interface OnMovieItemClickCallback{
        void onMovieItemClicked(int position);
    }
}
