package id.interview.newsapi.view.home.modules

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import id.interview.newsapi.R
import kotlinx.android.synthetic.main.item_stories.view.*

class StoriesAdapter(val context: Context, var news: List<MoviesModels>) : RecyclerView.Adapter<StoriesAdapter.ViewHolder>()  {

    var listener: onClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_stories, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsBind = news[position]
        holder.itemView.nama_news_stories.text = newsBind.title_news
        holder.itemView.content_news_stories.text = newsBind.desc_news
        holder.itemView.category_news_stories.text = newsBind.source?.name
        holder.itemView.author_news_stories.text = newsBind.author_name
        Picasso.get()
            .load(newsBind.url_to_image)
            .error(R.drawable.image_not_found)
            .into(holder.itemView.image_news_stories);
        holder.itemView.setOnClickListener {

        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface onClickListener {
        fun onViewNews(index: Int)
    }
}