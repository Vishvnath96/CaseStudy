package com.target.targetcasestudy.network.repository

import com.target.targetcasestudy.data.remote.model.Products
import com.target.targetcasestudy.network.OperationCallback

class NetworkRepository(private val dataSource: NetworkDataSource) {

    fun fetchData(callback: OperationCallback<List<Products>>) {
        dataSource.retrieveData(callback)
    }
}