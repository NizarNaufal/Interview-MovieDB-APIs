package id.interview.newsapi.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import id.interview.newsapi.R
import id.interview.newsapi.repository.IView
import id.interview.newsapi.repository.NetworkingState
import id.interview.newsapi.repository.ViewNetworkState
import id.interview.newsapi.repository.base.BaseFragment
import id.interview.newsapi.support.*
import id.interview.newsapi.view.home.modules.NewsAdapter
import id.interview.newsapi.view.home.modules.MoviesModels
import id.interview.newsapi.view.home.modules.StoriesAdapter
import id.interview.newsapi.view.home.modules.StoriesModels
import id.interview.newsapi.view.home.support.presenter.NewsPresenter
import id.interview.newsapi.view.home.support.presenter.StoriesPresenter
import kotlinx.android.synthetic.main.fragment_home.*

class FragmentHome : BaseFragment(), ViewNetworkState, IView {

    private val presenter by lazy { context?.let { NewsPresenter(it, this) } }
    private val presenterStories by lazy { context?.let { StoriesPresenter(it, this) } }
    private var isRefresh = false
    private var headlines = mutableListOf<MoviesModels>()
    private var stories = mutableListOf<StoriesModels>()
    private var country = "us"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }
    override fun onDestroy() {
        super.onDestroy()
        networkState = NetworkingState.Destroy()
    }


    override fun showLoading(key: String, status: Boolean) {
        super.showLoading(key, status)
        activity?.runOnUiThread {
            when (key) {
                presenter?.moviesParam -> {
                    recycler_view?.apply { if (status) gone() else visible() }
                }
            }
        }
    }
    override fun requestSuccess(key: String, response: Any?) {
        super.requestSuccess(key, response)
        activity?.runOnUiThread {
            when (key) {
                presenter?.moviesParam -> {
                    headlines = (response as List<MoviesModels>).toMutableList()
                    initList(headlines as ArrayList<MoviesModels>)
                }

                presenterStories?.storiesParam -> {
                    stories = (response as List<StoriesModels>).toMutableList()
                    initStories(stories as ArrayList<StoriesModels>)
                }
            }
        }
    }

    override fun requestFailure(key: String, code: Int, message: Any?) {
        super.requestFailure(key, code, message)
        activity?.runOnUiThread {
            when (key) {
                presenter?.moviesParam -> activity?.showToast(message.toString())
                presenterStories?.storiesParam -> activity?.showToast(message.toString())
            }
        }
    }
    override fun initView() {
        requestEvent()
        requestStories()
        swipe_refresh_news?.apply {
            setColorSchemeResources(R.color.red, R.color.yellow, R.color.colorRed)
            setOnRefreshListener {
                hide()
                requestEvent(true)
            }
        }
    }

    private fun requestEvent(isRefresh: Boolean = false) {
        this.isRefresh = isRefresh
        presenter?.getListNews(country, tokenUrl)

    }

    private fun requestStories(isRefresh: Boolean = false) {
        this.isRefresh = isRefresh
        presenterStories?.getStoriesNews(tokenUrl)

    }
    private fun initStories(dataList: ArrayList<StoriesModels>) {
        val adapterProduct = NewsAdapter(
            context, layoutInflater, dataList, R.layout.item_poster
        )
        recycler_view?.apply {
            recycler_view?.layoutManager = LinearLayoutManager(activity)
            recycler_view?.setHasFixedSize(true)
            adapter = adapterProduct
        }
    }

    private fun initList(dataList: ArrayList<MoviesModels>) {
        val adapterProduct = StoriesAdapter(
            context, layoutInflater, dataList, R.layout.item_stories
        )
        recycler_view_stories?.apply {
            recycler_view_stories?.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            recycler_view_stories?.setHasFixedSize(true)
            adapter = adapterProduct
        }
    }
}
