package id.interview.moviedb.view.home.modules;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.LayoutRes;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import id.interview.moviedb.R;
import id.interview.moviedb.view.listcategory.ActivityDetailsMovies;

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.ViewHolder> {

    public final List<MoviesModels> movies;
    private final LayoutInflater layoutInflater;
    private final int rowLayout;
    private final Context mContext;

    public StoriesAdapter(Context context, LayoutInflater layoutInflater, List<MoviesModels> movies, @LayoutRes int rowLayout) {
        mContext = context;
        this.movies = movies;
        this.layoutInflater = layoutInflater;
        this.rowLayout = rowLayout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(rowLayout, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MoviesModels movies_tayang = movies.get(position);
        holder.nama_news.setText(movies_tayang.getTitle_news());
        holder.category_news.setText(movies_tayang.getSource().getName());
        holder.content_news.setText(movies_tayang.getDesc_news());
        holder.author_news.setText("Author : "+ movies_tayang.getAuthor_name());
        Glide.with(mContext)
                .load( movies_tayang.getUrl_to_image())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.image);

        holder.lytParent.setOnClickListener(view1 -> {
            Intent intent = new Intent(view1.getContext(), ActivityDetailsMovies.class);
            intent.putExtra("data", movies.get(position));
            view1.getContext().startActivity(intent);
        });
    }
    @Override
    public int getItemCount() {
        return movies.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView nama_news;
        private AppCompatTextView content_news;
        private AppCompatTextView category_news;
        private AppCompatTextView author_news;
        private AppCompatImageView image;
        private RelativeLayout lytParent;
        public ViewHolder(View view) {
            super(view);
            nama_news = view.findViewById(R.id.nama_news_stories);
            content_news = view.findViewById(R.id.content_news_stories);
            category_news = view.findViewById(R.id.category_news_stories);
            author_news = view.findViewById(R.id.author_news_stories);
            image = view.findViewById(R.id.image_news_stories);
            lytParent = view.findViewById(R.id.rootLayout_news);
        }
    }

}

