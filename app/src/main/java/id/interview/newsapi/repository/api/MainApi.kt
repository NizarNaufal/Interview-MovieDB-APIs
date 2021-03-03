package id.interview.newsapi.repository.api

import retrofit2.Call
import retrofit2.http.*


interface MainApi {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("/v2/top-headlines")
    fun getListNews(
        @Query("country") country: String?,
        @Query("apiKey") apiKey: String?
    ): Call<String>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("/v2/top-headlines")
    fun getListCategory(
        @Query("country") country: String?,
        @Query("category") category:String?,
        @Query("apiKey") apiKey: String?
    ): Call<String>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("/v2/sources")
    fun getStoriesNews(
        @Query("apiKey") apiKey: String?
    ): Call<String>
}