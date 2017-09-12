package com.xianwei.movies.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.xianwei.movies.R;
import com.xianwei.movies.Trailer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xianwei li on 9/8/2017.
 */

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {

    private Context context;
    private List<Trailer> trailerList;

    public TrailerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public TrailerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.trailer_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrailerAdapter.ViewHolder holder, int position) {
        Trailer currentItem = trailerList.get(position);
        Picasso.with(context)
                .load(currentItem.getImageUrl())
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_broken_image)
                .into(holder.trailerImageView);

        holder.trailer = currentItem;
    }

    @Override
    public int getItemCount() {
        if (trailerList == null) {
            return 0;
        }
        return trailerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_trailer)
        ImageView trailerImageView;

        private Trailer trailer;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(trailer.getVideoUrl()));
                    if (intent.resolveActivity(context.getPackageManager()) != null) {
                        context.startActivity(intent);
                    }
                }
            });
        }
    }

    public void setTrailerList(List<Trailer> trailerList) {
        this.trailerList = trailerList;
        notifyDataSetChanged();
    }
}
