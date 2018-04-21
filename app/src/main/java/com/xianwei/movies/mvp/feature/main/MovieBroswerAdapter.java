package com.xianwei.movies.mvp.feature.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xianweili on 4/20/18.
 */

public class MovieBroswerAdapter extends RecyclerView.Adapter<MovieBroswerAdapter.ViewHolder> {
    @Override
    public MovieBroswerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MovieBroswerAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
