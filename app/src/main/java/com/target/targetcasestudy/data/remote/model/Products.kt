package com.target.targetcasestudy.data.remote.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Products(

	@SerializedName("id")
	val id: Int,
	@SerializedName("title")
	val title: String,
	@SerializedName("aisle")
	val aisle: String,
	@SerializedName("description")
	val description: String,
	@SerializedName("image_url")
	val image_url: String,
	@SerializedName("regular_price")
	val regular_price: RegularPrice?,
	@SerializedName("sale_price")
	val sale_price: RegularPrice?
): Parcelable