package id.interview.newsapi.view.home.modules

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.interview.newsapi.R
import kotlinx.android.synthetic.main.item_poster.view.*

class NewsAdapter(val context: Context, var news: List<StoriesModels>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_poster, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsBind = news[position]
        holder.itemView.title_news.text = newsBind.name_stories
        holder.itemView.description_news.text = newsBind.desc_stories
        holder.itemView.featured.text = newsBind.category_news
        holder.itemView.setOnClickListener {
            val url: String? = newsBind.url_image_stories
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
           context.startActivity(i)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}