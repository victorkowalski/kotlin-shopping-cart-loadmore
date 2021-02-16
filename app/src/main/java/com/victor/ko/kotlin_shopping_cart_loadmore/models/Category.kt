package com.victor.ko.kotlin_shopping_cart_loadmore.models

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("parent_id")
    val parentId: Int
)