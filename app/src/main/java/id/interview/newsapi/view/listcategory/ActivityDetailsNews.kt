package id.interview.newsapi.view.listcategory

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.squareup.picasso.Picasso
import id.interview.newsapi.R
import id.interview.newsapi.repository.IView
import id.interview.newsapi.repository.ViewNetworkState
import id.interview.newsapi.repository.base.BaseActivity
import id.interview.newsapi.support.DateFormat
import id.interview.newsapi.view.home.WebviewArticle
import id.interview.newsapi.view.home.modules.MoviesModels
import kotlinx.android.synthetic.main.activity_news_details.*
import kotlinx.android.synthetic.main.include_news_details_content.*
import kotlinx.android.synthetic.main.item_stories.view.*


class ActivityDetailsNews: BaseActivity(), ViewNetworkState, IView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        initView()
        initToolbar()
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
    private fun initToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar_news_details)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.navigationIcon?.setColorFilter(
            resources.getColor(R.color.colorTextAction),
            PorterDuff.Mode.SRC_ATOP
        )
        setSupportActionBar(toolbar)
        supportActionBar?.title = null
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun initView() {
        val data = intent.getParcelableExtra<MoviesModels>("data")
        Picasso.get()
            .load(data?.url_to_image)
            .error(R.drawable.image_not_found)
            .into(image_news_content)
        title_news_ku.text = data?.title_news
        content_news.text = data?.desc_news
        date?.text = DateFormat(data?.publishedAt)
        btn_share?.setOnClickListener {
            try {
                val i = Intent(Intent.ACTION_SEND)
                i.type = "text/plan"
                i.putExtra(Intent.EXTRA_SUBJECT, data?.title_news)
                val body: String =
                    data?.title_news.toString() + "\n" + data?.url_image + "\n" + "Share from the News App" + "\n"
                i.putExtra(Intent.EXTRA_TEXT, body)
                startActivity(Intent.createChooser(i, "Share with :"))
            } catch (e: Exception) {
                Toast.makeText(this, "Sorry, \nCannot be share", Toast.LENGTH_SHORT).show()
            }
        }
        read_more?.setOnClickListener {
            val intent = Intent(this, WebviewArticle::class.java)
            intent.putExtra("url_parse", data?.url_image)
            startActivity(intent)
        }
    }
}