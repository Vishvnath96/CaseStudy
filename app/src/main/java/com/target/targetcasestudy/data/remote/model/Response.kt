package com.target.targetcasestudy.data.remote.model




import com.google.gson.annotations.SerializedName


data class Response (

	@SerializedName("products")
	val products : List<Products>?
)