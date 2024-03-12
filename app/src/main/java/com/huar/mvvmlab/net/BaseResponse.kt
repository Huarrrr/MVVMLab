package com.huar.mvvmlab.net

import com.huar.mvvmlab.model.ItunesData

data class ItunesResponse (
    val resultCount: Int, val results: MutableList<ItunesData>
)