package com.victor.ko.scart.network

import com.victor.ko.kotlin_shopping_cart_loadmore.models.ResponseData
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {

    @GET("products")
    suspend fun getProducts(): Response<ResponseData>
    //suspend fun getProducts(): Response<List<Product>>

}