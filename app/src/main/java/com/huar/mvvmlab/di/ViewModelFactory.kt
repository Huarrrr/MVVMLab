package com.huar.mvvmlab.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.huar.mvvmlab.vm.MainViewModel

class ViewModelFactory(private val repository: ItunesRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}