package com.victor.ko.kotlin_shopping_cart_loadmore.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseData {
    @SerializedName("data")
    @Expose
    var data: List<Product>? = null
}
