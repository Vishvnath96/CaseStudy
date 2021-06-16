package com.target.targetcasestudy.viewmodel

import androidx.lifecycle.ViewModel
import com.target.targetcasestudy.data.remote.model.Products

class DealItemFragmentViewModel(val item: Products): ViewModel() {

    val title = item.title

    val productPrice = item.sale_price?.display_string?:""

    val regularPrice =  item.regular_price?.display_string?:""

    val description = item.description

    val imgUrl = item.image_url

    fun isItemChecked() : Boolean {
        return !item.sale_price?.display_string.isNullOrEmpty()
    }

}