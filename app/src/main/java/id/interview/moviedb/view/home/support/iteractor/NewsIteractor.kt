package id.interview.moviedb.view.home.support.iteractor

import id.interview.moviedb.repository.api.MainApi
import id.interview.moviedb.repository.api.ResponseCode
import id.interview.moviedb.support.ERROR_MESSAGE
import id.interview.moviedb.support.FAILED_LOGOUT
import id.interview.moviedb.support.FAILED_MESSAGE
import id.interview.moviedb.support.showLog
import id.interview.moviedb.view.home.support.IMoviesIteractor

class NewsIteractor(var api: MainApi) : IMoviesIteractor {

    override fun getListNews(country:String,apiKey:String): Pair<Int, String?> {
        return try {
            val response = api.getListNews(country,apiKey).execute()
            when (response.isSuccessful) {
                true -> Pair(ResponseCode.OK, response.body())
                else -> {
                    val message = when (response.code()) {
                        401 -> FAILED_LOGOUT
                        else -> ERROR_MESSAGE
                    }

                    Pair(response.code(), message)
                }
            }

        } catch (e: Exception) {
            showLog("failed get movie : ${e.message}")
            Pair(ResponseCode.TIME_OUT, FAILED_MESSAGE)
        }
    }
    override fun getListCategory(country:String,category:String,apiKey:String): Pair<Int, String?> {
        return try {
            val response = api.getListCategory(country,category,apiKey).execute()
            when (response.isSuccessful) {
                true -> Pair(ResponseCode.OK, response.body())
                else -> {
                    val message = when (response.code()) {
                        401 -> FAILED_LOGOUT
                        else -> ERROR_MESSAGE
                    }

                    Pair(response.code(), message)
                }
            }

        } catch (e: Exception) {
            showLog("failed get movie : ${e.message}")
            Pair(ResponseCode.TIME_OUT, FAILED_MESSAGE)
        }
    }
}