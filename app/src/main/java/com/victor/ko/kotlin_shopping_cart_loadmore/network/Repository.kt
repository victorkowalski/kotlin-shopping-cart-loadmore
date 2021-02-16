package com.victor.ko.kotlin_shopping_cart_loadmore.network

import com.victor.ko.kotlin_shopping_cart_loadmore.models.Product
import com.victor.ko.scart.network.ApiAdapter

object Repository {

    suspend fun getProducts(): ArrayList<Product> {
        //val list = arrayListOf<DataModel>()

        val response = ApiAdapter.apiClient.getProducts()
        print(response)
        // Check if response was successful
        /*if (response.isSuccessful && response.body() != null) {
            // Retrieve data.
            val data = response.body()!!
            print(data)

        return list
    }*/
        return arrayListOf()
    }
}
