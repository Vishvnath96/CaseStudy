package com.target.targetcasestudy.network.repository

import com.target.targetcasestudy.data.remote.model.Products
import com.target.targetcasestudy.data.remote.model.Response
import com.target.targetcasestudy.network.OperationCallback


interface NetworkDataSource {
    fun retrieveData(callback: OperationCallback<List<Products>>)
    fun cancel()
}
