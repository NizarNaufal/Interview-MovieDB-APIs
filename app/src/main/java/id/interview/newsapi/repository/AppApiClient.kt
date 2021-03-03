package id.interview.newsapi.repository

import id.interview.newsapi.BuildConfig
import id.interview.newsapi.repository.api.MainApi
import id.interview.newsapi.support.baseUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object AppApiClient {
    private val log = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }
    private val client = OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG) addInterceptor(log)
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(ScalarsConverterFactory.create())
        .client(client.build())
        .build()

    fun mainClient(): MainApi = retrofit.create(
        MainApi::class.java
    )
}