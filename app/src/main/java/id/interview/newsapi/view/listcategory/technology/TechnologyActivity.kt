package id.interview.newsapi.view.listcategory.technology

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
import id.interview.newsapi.view.listcategory.support.CategoryAdapter
import kotlinx.android.synthetic.main.activity_technology.*


class TechnologyActivity : BaseActivity(), ViewNetworkState, IView {

    private val parent_view: View? = null
    private var recyclerView: RecyclerView? = null
    private var mAdapter: NewsAdapter? = null
    private var isRefresh = false
    private val presenter by lazy {
        NewsPresenter(
            baseContext,
            this
        )
    }
    private var products = mutableListOf<MoviesModels>()
    private var country = "us"
    private var category = "technology"

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
            this, layoutInflater, dataList, R.layout.item_news_category
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
                    if (isRefresh) {
                        swipe_refresh_news_ku?.apply { if (status) show() else hide()
                        }
                    } else {
                        if (status) {
                            swipe_refresh_news_ku?.disable()
                            recycler_view_dummy?.gone()
                        } else {
                            swipe_refresh_news_ku?.enable()
                            recycler_view_dummy?.visible()
                        }
                    }
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
        Log.d("datanya gamasuk gan ", "datanya gamasuk euy")
        runOnUiThread {
            when (key) {
                presenter.moviesListParam -> showToast(message.toString())
            }
        }
    }

}