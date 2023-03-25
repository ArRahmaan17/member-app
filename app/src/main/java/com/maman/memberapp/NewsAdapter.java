package com.maman.memberapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maman.memberapp.model.NewsModel;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {
    private Context context;
    private ArrayList<NewsModel> NewsList = new ArrayList<NewsModel>();

    public NewsAdapter(Context context, ArrayList<NewsModel> newsList) {
        this.context = context;
        NewsList = newsList;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_news,parent, false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        NewsModel News = NewsList.get(position);
        Log.d("jingan", News.getTitle());
        holder.title.setText(News.getTitle());
        holder.date.setText(News.getDate());
        holder.content.setText(News.getContent());
    }

    @Override
    public int getItemCount() {
        return NewsList.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder{
        TextView title, date, content;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.content_title);
            date = itemView.findViewById(R.id.content_date);
            content = itemView.findViewById(R.id.content_prefix);
        }
    }
}
