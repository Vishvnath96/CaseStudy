package com.target.targetcasestudy.network

import com.target.targetcasestudy.data.remote.model.Products


interface OperationCallback<T> {
    fun onSuccess(data: List<Products>?)
    fun onError(error: String?)
}