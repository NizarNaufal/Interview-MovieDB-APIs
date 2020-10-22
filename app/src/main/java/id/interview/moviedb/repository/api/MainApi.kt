package id.interview.moviedb.repository.api

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface MainApi {


    @POST("api/profile-retail")
    fun addStore(
        @Header("Authorization") auth: String,
        @Body body: RequestBody
    ): Call<String>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("/v2/top-headlines")
    fun getListNews(
        @Query("country") country: String?,
        @Query("apiKey") apiKey: String?
    ): Call<String>

}