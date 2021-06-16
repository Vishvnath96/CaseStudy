package com.target.targetcasestudy.network



import com.target.targetcasestudy.data.remote.model.Products
import com.target.targetcasestudy.data.remote.model.Response
import com.target.targetcasestudy.network.repository.NetworkDataSource
import retrofit2.Call
import retrofit2.Callback


class RemoteDataSource(apiClient: ApiClient) : NetworkDataSource {

    private var call: Call<Response>? = null
    private val service = ApiClient.build()



    override fun retrieveData(callback: OperationCallback<List<Products>>) {
        call = service?.getFashionDetails("close")
        call?.enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                val res = response.body()
                if(res != null && res.products?.size?:0 > 0) {
                    callback.onSuccess(res.products)
                }
            }
            override fun onFailure(call: Call<Response>, t: Throwable) {
                callback.onError(t.localizedMessage)
            }
        })
    }

    override fun cancel() {
        call?.let {
            it.cancel()
        }
    }

}