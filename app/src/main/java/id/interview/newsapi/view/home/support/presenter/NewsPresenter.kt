package id.interview.newsapi.view.home.support.presenter

import android.content.Context
import com.google.gson.Gson
import id.interview.newsapi.repository.AppApiClient
import id.interview.newsapi.repository.NetworkingState
import id.interview.newsapi.repository.ViewNetworkState
import id.interview.newsapi.repository.api.ResponseCode
import id.interview.newsapi.view.home.modules.MoviesModels
import id.interview.newsapi.view.home.support.IMoviesPresenter
import id.interview.newsapi.view.home.support.iteractor.NewsIteractor
import org.json.JSONObject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewsPresenter(var context: Context, var view: ViewNetworkState) :
    IMoviesPresenter {

    private val iteractor by lazy {
        NewsIteractor(
            AppApiClient.mainClient()
        )
    }

    val moviesParam = "movies.param"
    val moviesListParam = "movies.list.param"

    override fun getListNews(country:String,apiKey:String) {
        view.networkState = NetworkingState.ShowLoading(Pair(moviesParam, true))
        GlobalScope.launch {
            val response = iteractor.getListNews(country,apiKey)
            (view.networkState !is NetworkingState.Destroy).apply {
                view.networkState = NetworkingState.ShowLoading(Pair(moviesParam, false))
                when (response.first) {
                    ResponseCode.OK -> {
                        val json = JSONObject(response.second.toString())
                        val listData = Gson().fromJson(json.getJSONArray("articles").toString() , Array<MoviesModels>::class.java).toList()
                        view.networkState =
                            NetworkingState.ResponseSuccess(Pair(moviesParam, listData))
                    }
                    else -> view.networkState = NetworkingState.ResponseFailure(
                        Pair(
                            moviesParam,
                            Pair(response.first, response.second)
                        )
                    )
                }
            }
        }
    }
    override fun getListCategory(country:String,category:String,apiKey:String) {
        view.networkState = NetworkingState.ShowLoading(Pair(moviesListParam, true))
        GlobalScope.launch {
            val response = iteractor.getListCategory(country,category,apiKey)
            (view.networkState !is NetworkingState.Destroy).apply {
                view.networkState = NetworkingState.ShowLoading(Pair(moviesListParam, false))
                when (response.first) {
                    ResponseCode.OK -> {
                        val json = JSONObject(response.second.toString())
                        val listData = Gson().fromJson(json.getJSONArray("articles").toString() , Array<MoviesModels>::class.java).toList()
                        view.networkState =
                            NetworkingState.ResponseSuccess(Pair(moviesListParam, listData))
                    }
                    else -> view.networkState = NetworkingState.ResponseFailure(
                        Pair(
                            moviesListParam,
                            Pair(response.first, response.second)
                        )
                    )
                }
            }
        }
    }

}