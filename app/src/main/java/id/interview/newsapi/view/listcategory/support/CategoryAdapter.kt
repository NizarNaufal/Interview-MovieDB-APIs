package id.interview.newsapi.view.listcategory.support

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import id.interview.newsapi.R
import id.interview.newsapi.view.home.modules.MoviesModels
import kotlinx.android.synthetic.main.item_news_category.view.*

class CategoryAdapter(val context: Context, var news: List<MoviesModels>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>()  {

    var listener: onClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_poster, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsBind = news[position]
        holder.itemView.title_ku_option.text = newsBind.title_news
        holder.itemView.featured_ku.text = newsBind.source?.name
        Picasso.get()
            .load(newsBind.url_to_image)
            .error(R.drawable.image_not_found)
            .into(holder.itemView.image_ku);
        holder.itemView.setOnClickListener {
            listener?.onViewNews(position)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface onClickListener {
        fun onViewNews(index: Int)
    }
}