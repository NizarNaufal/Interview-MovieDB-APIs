package id.interview.moviedb.view.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import id.interview.moviedb.R
import id.interview.moviedb.repository.IView
import id.interview.moviedb.repository.NetworkingState
import id.interview.moviedb.repository.ViewNetworkState
import id.interview.moviedb.repository.base.BaseFragment
import id.interview.moviedb.support.*
import id.interview.moviedb.view.movies.modules.MoviesAdapter
import id.interview.moviedb.view.movies.modules.MoviesModels
import id.interview.moviedb.view.movies.support.MoviesPresenter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_movies.*

class FragmentMovies : BaseFragment(), ViewNetworkState, IView {

    private val presenter by lazy { context?.let { MoviesPresenter(it, this) } }
    private var isRefresh = false
    private var products = mutableListOf<MoviesModels>()
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
                    recycler_view_movies_fragment_ku?.apply { if (status) gone() else visible() }
                }
            }
        }
    }
    override fun requestSuccess(key: String, response: Any?) {
        super.requestSuccess(key, response)
        activity?.runOnUiThread {
            when (key) {
                presenter?.moviesParam -> {
                    products = (response as List<MoviesModels>).toMutableList()
                    initList(products as ArrayList<MoviesModels>)
                }
            }
        }
    }

    override fun requestFailure(key: String, code: Int, message: Any?) {
        super.requestFailure(key, code, message)
        activity?.runOnUiThread {
            when (key) {
                presenter?.moviesParam -> activity?.showToast(message.toString())
            }
        }
    }
    override fun initView() {
        requestEvent()
        swipe_refresh_layout_movies_fragment?.apply {
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

    private fun initList(dataList: ArrayList<MoviesModels>) {
        val adapterProduct = MoviesAdapter(
            context, layoutInflater, dataList, R.layout.item_poster
        )
       recycler_view?.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
            recycler_view?.setHasFixedSize(true)
            adapter = adapterProduct
        }

    }
}
