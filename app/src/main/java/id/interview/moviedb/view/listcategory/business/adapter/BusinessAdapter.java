package id.interview.moviedb.view.listcategory.business.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.balysv.materialripple.MaterialRippleLayout;

import java.util.List;

import id.interview.moviedb.R;
import id.interview.moviedb.view.home.modules.StoriesModels;
import id.interview.moviedb.view.listcategory.ActivityDetailsMovies;

public class BusinessAdapter extends RecyclerView.Adapter<BusinessAdapter.ViewHolder> {

    public final List<BusinessModels> movies;
    private final LayoutInflater layoutInflater;
    private final int rowLayout;
    private final Context mContext;

    public BusinessAdapter(Context context, LayoutInflater layoutInflater, List<BusinessModels> movies, @LayoutRes int rowLayout) {
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
        BusinessModels movies_tayang = movies.get(position);
        holder.nama_news.setText(movies_tayang.getName_stories());
        holder.feature_news.setText(movies_tayang.getCategory_news());
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
        private AppCompatTextView feature_news;

        private MaterialRippleLayout lytParent;
        public ViewHolder(View view) {
            super(view);
            nama_news = view.findViewById(R.id.title_news);
            feature_news = view.findViewById(R.id.featured);
            lytParent = view.findViewById(R.id.lyt_parent);
        }
    }

}
