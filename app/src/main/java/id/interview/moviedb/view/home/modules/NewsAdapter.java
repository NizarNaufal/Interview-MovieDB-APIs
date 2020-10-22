package id.interview.moviedb.view.home.modules;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    public final List<MoviesModels> movies;
    private final LayoutInflater layoutInflater;
    private final int rowLayout;
    private final Context mContext;

    public NewsAdapter(Context context, LayoutInflater layoutInflater, List<MoviesModels> movies, @LayoutRes int rowLayout) {
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
        holder.nama_product.setText(movies_tayang.getTitle_news());
        holder.date_news.setText(movies_tayang.getPublishedAt());

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
        private AppCompatTextView nama_product;
        private AppCompatTextView date_news;
        private AppCompatImageView image;
        private MaterialRippleLayout lytParent;
        public ViewHolder(View view) {
            super(view);
            nama_product = view.findViewById(R.id.title_news);
            date_news = view.findViewById(R.id.date_news);
            image = view.findViewById(R.id.image_news);
            lytParent = view.findViewById(R.id.lyt_parent);
        }
    }

}
