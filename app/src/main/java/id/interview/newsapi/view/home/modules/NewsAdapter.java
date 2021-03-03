package id.interview.newsapi.view.home.modules;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.LayoutRes;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.interview.newsapi.R;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    public final List<StoriesModels> movies;
    private final LayoutInflater layoutInflater;
    private final int rowLayout;
    private final Context mContext;

    public NewsAdapter(Context context, LayoutInflater layoutInflater, List<StoriesModels> movies, @LayoutRes int rowLayout) {
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
        StoriesModels movies_tayang = movies.get(position);
        holder.nama_news.setText(movies_tayang.getName_stories());
        holder.feature_news.setText(movies_tayang.getCategory_news());
        holder.desc_news.setText(movies_tayang.getDesc_stories());
        holder.lytParent.setOnClickListener(view1 -> {
            String url = movies_tayang.getUrl_image_stories();
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            mContext.startActivity(i);
        });
    }
    @Override
    public int getItemCount() {
        return movies.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView nama_news;
        private AppCompatTextView feature_news;
        private AppCompatTextView desc_news;
        private RelativeLayout lytParent;
        public ViewHolder(View view) {
            super(view);
            nama_news = view.findViewById(R.id.title_news);
            desc_news = view.findViewById(R.id.description_news);
            feature_news = view.findViewById(R.id.featured);
            lytParent = view.findViewById(R.id.rootLayout_news);
        }
    }

}
