package com.target.targetcasestudy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.data.remote.model.Products
import com.target.targetcasestudy.network.OperationCallback
import com.target.targetcasestudy.network.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataViewModel(private val repository: NetworkRepository) : ViewModel() {


    private val isViewLoading = MutableLiveData<Boolean>()
    val viewLoading: MutableLiveData<Boolean> = isViewLoading

    private val dataList = MutableLiveData<List<Products>>().apply { value = emptyList() }
    val dataListLiveData: LiveData<List<Products>> = dataList

    private val onMessageError = MutableLiveData<Any>()
    val onMessageErrorLiveData: LiveData<Any> = onMessageError

    private val isEmptyList = MutableLiveData<Boolean>()
    val isEmptyListLiveData: LiveData<Boolean> = this.isEmptyList

    var showError = MutableLiveData(false)



    init {
        loadData()
    }


    fun updateList() {
        val list = dataList.value
        val updatedList = list?.plus(list)
        dataList.value = updatedList
    }


    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            isViewLoading.postValue(true)
            repository.fetchData( object :
                OperationCallback<List<Products>> {

                override fun onError(error: String?) {
                    isViewLoading.postValue(false)
                    onMessageError.postValue(error ?: "")
                }

                override fun onSuccess(data: List<Products>?) {
                    isViewLoading.postValue(false)
                    if (data.isNullOrEmpty()) {
                        this@DataViewModel.isEmptyList.postValue(true)

                    } else {
                        dataList.postValue(data)
                        showError.postValue(false)
                    }
                }
            })
        }
    }

}