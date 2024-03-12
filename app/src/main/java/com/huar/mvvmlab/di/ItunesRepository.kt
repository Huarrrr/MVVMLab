package com.huar.mvvmlab.di

import com.huar.mvvmlab.model.ItunesData
import com.huar.mvvmlab.model.ItunesDataSource
import com.huar.mvvmlab.net.NetCallback


class ItunesRepository(private val itunesDataSource: ItunesDataSource) {

    fun fetchItunesData(callback: NetCallback<ItunesData>) {
        itunesDataSource.retrieveItunes(callback)
    }

    fun cancel() {
        itunesDataSource.cancel()
    }
}