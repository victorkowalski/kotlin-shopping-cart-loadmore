package com.victor.ko.scart.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiAdapter {

    private val httpClient = OkHttpClient.Builder().build()

    val apiClient: ApiClient = Retrofit.Builder()
        .baseUrl("https://rstestapi.redsoftdigital.com/api/v1/")
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiClient::class.java)
}