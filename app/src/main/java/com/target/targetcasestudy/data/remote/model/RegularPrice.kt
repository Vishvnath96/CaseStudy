package com.target.targetcasestudy.data.remote.model



import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegularPrice (

	@SerializedName("amount_in_cents")
	val amount_in_cents : Int?,
	@SerializedName("currency_symbol")
	val currency_symbol : String?,
	@SerializedName("display_string")
	val display_string : String?
): Parcelable