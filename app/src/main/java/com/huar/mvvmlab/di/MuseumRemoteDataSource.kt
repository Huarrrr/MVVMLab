package com.huar.mvvmlab.di

import com.huar.mvvmlab.model.ItunesData
import com.huar.mvvmlab.model.ItunesDataSource
import com.huar.mvvmlab.net.ApiClient
import com.huar.mvvmlab.net.ItunesResponse
import com.huar.mvvmlab.net.NetCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItunesRemoteDataSource(apiClient: ApiClient) : ItunesDataSource {

    private var call: Call<ItunesResponse>? = null
    private val service = apiClient.build()

    override fun retrieveItunes(callback: NetCallback<ItunesData>) {

        call = service?.getMusicList()
        call?.enqueue(object : Callback<ItunesResponse> {
            override fun onFailure(call: Call<ItunesResponse>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(
                call: Call<ItunesResponse>,
                response: Response<ItunesResponse>
            ) {
                response.body()?.let {
                    if (response.isSuccessful) {
                        callback.onSuccess(it.results)
                    } else {
                        callback.onError(it.resultCount.toString())
                    }
                }
            }
        })
    }


    override fun cancel() {
        call?.let {
            it.cancel()
        }
    }
}