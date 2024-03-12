package com.huar.mvvmlab.net

import com.huar.mvvmlab.model.ItunesData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object ApiClient {

    private const val API_BASE_URL = "https://itunes.apple.com/"

    private var apiService: ApiService? = null

    fun build(): ApiService {
        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor())

        val retrofit: Retrofit = builder.client(httpClient.build()).build()
       apiService = retrofit.create(
            ApiService::class.java
        )

        return apiService as ApiService
    }

    private fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    interface ApiService {
        @GET("search?term=歌&limit=200&country=HK")
        fun getMusicList(): Call<ItunesResponse>
    }
}