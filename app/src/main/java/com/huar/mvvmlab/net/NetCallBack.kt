package com.huar.mvvmlab.net

interface NetCallback<T> {
    fun onSuccess(data: MutableList<T>?)
    fun onError(error: String?)
}