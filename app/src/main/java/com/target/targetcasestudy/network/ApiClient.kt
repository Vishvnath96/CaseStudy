package com.target.targetcasestudy.network


import com.target.targetcasestudy.data.remote.model.Products
import com.target.targetcasestudy.data.remote.model.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


object ApiClient {

    private const val API_BASE_URL = "https://api.target.com"

    fun build(): ServicesApiInterface? {
        val requestInterface = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return requestInterface.create(ServicesApiInterface::class.java)
    }


    interface ServicesApiInterface {//https://api.target.com/mobile_case_study_deals/v1/deals

        @GET("/mobile_case_study_deals/v1/deals")
        fun getFashionDetails(@Query("state")  state: String): Call<Response>
    }
}