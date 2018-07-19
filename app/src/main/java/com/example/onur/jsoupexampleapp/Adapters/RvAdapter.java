package com.example.onur.jsoupexampleapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.onur.jsoupexampleapp.AsyncTask.AsyncArticleDetail;
import com.example.onur.jsoupexampleapp.Model.Article;
import com.example.onur.jsoupexampleapp.R;

import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder>{


    private Context context;
    private List<Article> mData;

    public RvAdapter(Context context,List list){
        this.context = context;
        this.mData = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.article_item_row, parent,false);
        final MyViewHolder viewHolder = new MyViewHolder(view);

        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Article article = mData.get(viewHolder.getAdapterPosition());
                new AsyncArticleDetail(context,article).execute(article.getArticleLink());
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvTitle.setText(mData.get(position).getHeader());
        holder.tvAuthor.setText(mData.get(position).getWriter());
        Glide.with(context).load(mData.get(position).getPhotoPath()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView tvAuthor,tvTitle;
        LinearLayout view_container;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            tvAuthor = itemView.findViewById(R.id.txtauthorName);
            tvTitle = itemView.findViewById(R.id.txtTitle);

            view_container = itemView.findViewById(R.id.container1);
        }
    }

}
