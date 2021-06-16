package com.target.targetcasestudy.di

import androidx.lifecycle.ViewModelProvider
import com.target.targetcasestudy.network.ApiClient
import com.target.targetcasestudy.network.RemoteDataSource
import com.target.targetcasestudy.network.repository.NetworkDataSource
import com.target.targetcasestudy.network.repository.NetworkRepository
import com.target.targetcasestudy.viewmodel.ViewModelFactory


object Injection {

    private val movieDataSource: NetworkDataSource =
        RemoteDataSource(ApiClient)
    private val movieRepository =
        NetworkRepository(movieDataSource)
    private val movieViewModelFactory =
        ViewModelFactory(movieRepository)

    fun providerRepository(): NetworkDataSource {
        return movieDataSource
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return movieViewModelFactory
    }
}