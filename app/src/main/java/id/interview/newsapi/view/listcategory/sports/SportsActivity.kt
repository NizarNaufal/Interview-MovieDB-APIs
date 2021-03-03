package id.interview.newsapi.view.listcategory.sports

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.interview.newsapi.R
import id.interview.newsapi.repository.IView
import id.interview.newsapi.repository.NetworkingState
import id.interview.newsapi.repository.ViewNetworkState
import id.interview.newsapi.repository.base.BaseActivity
import id.interview.newsapi.support.*
import id.interview.newsapi.view.home.modules.MoviesModels
import id.interview.newsapi.view.home.modules.NewsAdapter
import id.interview.newsapi.view.home.support.presenter.NewsPresenter
import id.interview.newsapi.view.listcategory.ActivityDetailsNews
import id.interview.newsapi.view.listcategory.support.CategoryAdapter
import kotlinx.android.synthetic.main.activity_technology.*


class SportsActivity : BaseActivity(), ViewNetworkState, IView , CategoryAdapter.onClickListener {

    private var isRefresh = false
    private val presenter by lazy {
        NewsPresenter(
            baseContext,
            this
        )
    }
    private var products = mutableListOf<MoviesModels>()
    private var country = "us"
    private var category = "sports"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_technology)
        initView()
    }

    override fun initView() {
        requestProduct()
        swipe_refresh_news_ku?.apply {
            setColorSchemeResources(R.color.colorRed, R.color.pruple, R.color.colorRed)
            setOnRefreshListener { requestProduct(true) }
        }
    }

    private fun initList(dataList: ArrayList<MoviesModels>) {
        val adapterCart = CategoryAdapter(
            this, dataList
        )
        recycler_view_dummy?.apply {
            recycler_view_dummy?.layoutManager = LinearLayoutManager(context)
            recycler_view_dummy?.setHasFixedSize(true)
            adapter = adapterCart
        }
    }

    private fun requestProduct(isRefresh: Boolean = false) {
        this.isRefresh = isRefresh
        presenter.getListCategory(country,category, tokenUrl)
    }

    override fun onDestroy() {
        super.onDestroy()
        networkState = NetworkingState.Destroy()
    }

    override fun showLoading(key: String, status: Boolean) {
        super.showLoading(key, status)
        runOnUiThread {
            when (key) {
                presenter.moviesListParam -> {
                    recycler_view_dummy?.apply { if (status) gone() else visible() }
                    shimmer_home?.apply { if (status) visible() else gone() }
                }
            }
        }
    }

    override fun requestSuccess(key: String, response: Any?) {
        super.requestSuccess(key, response)
        runOnUiThread {
            when (key) {
                presenter.moviesListParam -> {
                    products = (response as List<MoviesModels>).toMutableList()
                    initList(products as ArrayList<MoviesModels>)
                }
            }
        }
    }

    override fun requestFailure(key: String, code: Int, message: Any?) {
        super.requestFailure(key, code, message)
        Log.d("datanya gamasuk gan ", message.toString())
        runOnUiThread {
            when (key) {
                presenter.moviesListParam -> showToast(message.toString())
            }
        }
    }

    override fun onViewNews(index: Int) {
        val cart = products[index]
        val intent = Intent(this, ActivityDetailsNews::class.java)
        intent.putExtra("data", cart)
        startActivity(intent)
    }

}