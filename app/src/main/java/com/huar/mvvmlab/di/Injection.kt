package com.huar.mvvmlab.di

import com.huar.mvvmlab.model.ItunesDataSource
import com.huar.mvvmlab.net.ApiClient

object Injection {
    private var itunesDataSource: ItunesDataSource? = null
    private var itunesRepository: ItunesRepository? = null
    private var itunesViewModelFactory: ViewModelFactory? = null

    private fun createItunesDataSource(): ItunesDataSource {
        val dataSource = ItunesRemoteDataSource(ApiClient)
        itunesDataSource = dataSource
        return dataSource
    }

    private fun createItunesRepository(): ItunesRepository {
        val repository = ItunesRepository(provideDataSource())
        itunesRepository = repository
        return repository
    }

    private fun createFactory(): ViewModelFactory {
        val factory = ViewModelFactory(providerRepository())
        itunesViewModelFactory = factory
        return factory
    }

    private fun provideDataSource() = itunesDataSource ?: createItunesDataSource()
    private fun providerRepository() = itunesRepository ?: createItunesRepository()

    fun provideViewModelFactory() = itunesViewModelFactory ?: createFactory()

    fun destroy() {
        itunesDataSource = null
        itunesRepository = null
        itunesViewModelFactory = null
    }
}