package id.interview.moviedb.repository.api

import okhttp3.FormBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface MainApi {

    @POST("api/register")
    fun signUp(@Body body: FormBody): Call<String>

    @POST("api/login")
    fun signIn(@Body body: FormBody): Call<String>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("api/user/detail")
    fun getProfile(@Header("Authorization") auth: String): Call<String>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("api/user/pass")
    fun sendEmailPassword(
        @Query("email") email: String
    ): Call<String>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("api/logout")
    fun sendLogout(
        @Header("Authorization") auth: String
    ): Call<String>
    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("api/get_all_products_and_retail")
    fun getProduct(
        @Header("Authorization") auth: String
    ): Call<String>

    @POST("api/user/detail")
    fun updateProfile(
        @Header("Authorization") auth: String,
        @Body body: RequestBody
    ): Call<String>

    @GET("api/user/detail")
    fun infoProfile(
        @Header("Authorization") auth: String
    ): Call<String>

    @POST("api/profile-retail")
    fun addStore(
        @Header("Authorization") auth: String,
        @Body body: RequestBody
    ): Call<String>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("api/profile-retail/{id}")
    fun getStore(
        @Header("Authorization") auth: String,
        @Path("id") id: Int
    ): Call<String>

}