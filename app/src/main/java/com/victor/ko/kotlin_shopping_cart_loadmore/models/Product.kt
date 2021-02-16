package com.victor.ko.kotlin_shopping_cart_loadmore.models

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("short_description")
    val shortDescription: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("amount")
    val amount: Int,
    @SerializedName("price")
    val price: Double,
    @SerializedName("producer")
    val producer: String,
    @SerializedName("categories")
    val categories: List<Category>
)