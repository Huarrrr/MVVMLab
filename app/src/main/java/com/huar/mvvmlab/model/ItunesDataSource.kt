package com.huar.mvvmlab.model

import com.huar.mvvmlab.net.NetCallback

interface ItunesDataSource {

    fun retrieveItunes(callback: NetCallback<ItunesData>)
    fun cancel()
}