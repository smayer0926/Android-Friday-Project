package com.epicodus.athletetracker.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.epicodus.athletetracker.Models.News;
import com.epicodus.athletetracker.R;
import com.epicodus.athletetracker.ui.WelcomePage2;
import com.squareup.picasso.Picasso;


import org.parceler.Parcels;

import java.util.ArrayList;


import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>{
    private ArrayList<News> mNews = new ArrayList<>();
    private Context mContext;

    public NewsListAdapter(Context context, ArrayList<News> news) {
        mContext = context;
        mNews = news;
    }

    @Override
    public NewsListAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item, parent, false);
        NewsViewHolder viewHolder = new NewsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsListAdapter.NewsViewHolder holder, int position) {
        holder.bindNews(mNews.get(position));
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }


    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.title) TextView title;
        @Bind(R.id.imageNews) ImageView image;
        @Bind(R.id.author) TextView author;
        @Bind(R.id.url) TextView url;

        private Context mContext;

        public NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindNews(News news) {
            Picasso.with(mContext).load(news.getImage()).into(image);
            title.setText(news.getTitle());
            author.setText(news.getAuthor());
            url.setText(news.getUrl());

        }

        @Override
        public void onClick(View v) {
            Log.d("click listener", "working!");
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, WelcomePage2.class);
            intent.putExtra("position", itemPosition + "");
            intent.putExtra("news", Parcels.wrap(mNews));
            mContext.startActivity(intent);
        }
    }
}

