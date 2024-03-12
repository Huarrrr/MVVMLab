package com.huar.mvvmlab.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.huar.mvvmlab.di.ItunesRepository
import com.huar.mvvmlab.model.ItunesData
import com.huar.mvvmlab.net.NetCallback

class MainViewModel(private val repository: ItunesRepository) : ViewModel() {

    private val musics = MutableLiveData<MutableList<ItunesData>>().apply { value = arrayListOf() }
    private val musicsPrice = MutableLiveData<MutableList<ItunesData>>().apply { value = arrayListOf() }
    val itunesList: LiveData<MutableList<ItunesData>> = musics
    val itunesListPrice: LiveData<MutableList<ItunesData>> = musicsPrice

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList


    fun loadItunesList() {
        _isViewLoading.value = true
        repository.fetchItunesData(object : NetCallback<ItunesData> {
            override fun onError(error: String?) {
                _isViewLoading.value = false
                _onMessageError.value = error!!
            }

            override fun onSuccess(data: MutableList<ItunesData>?) {
                _isViewLoading.value = false
                if (data.isNullOrEmpty()) {
                    _isEmptyList.value = true

                } else {
                    musics.value = data
                    musicsPrice.value = data.sortedBy  { it.trackPrice } as MutableList<ItunesData>
                }
            }
        })
    }

}