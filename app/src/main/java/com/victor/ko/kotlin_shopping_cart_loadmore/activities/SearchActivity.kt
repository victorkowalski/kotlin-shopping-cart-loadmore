package com.victor.ko.kotlin_shopping_cart_loadmore.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.victor.ko.kotlin_shopping_cart_loadmore.adapters.AutoFitGridLayoutManager
import com.victor.ko.kotlin_shopping_cart_loadmore.adapters.RecyclerViewAdapter
import com.victor.ko.kotlin_shopping_cart_loadmore.databinding.ActivitySearchBinding
import com.victor.ko.kotlin_shopping_cart_loadmore.models.Product
import com.victor.ko.kotlin_shopping_cart_loadmore.models.ResponseData
import com.victor.ko.scart.network.ApiAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SearchActivity : AppCompatActivity() {

    private lateinit var bnd: ActivitySearchBinding
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bnd = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(bnd.root)

        val recyclerView = bnd.myRecycleView

        adapter = RecyclerViewAdapter(/*listOf(),*/ ::adapterOnClick)

        recyclerView.adapter = adapter

        //val layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

        val layoutManager = AutoFitGridLayoutManager(this, 500)
        recyclerView.layoutManager = layoutManager

        //loadProducts()
    }
/*
    private fun loadProducts() {
        //GlobalScope.launch(Dispatchers.Main)
        CoroutineScope(Dispatchers.Main).launch() {
            try {
                val response = ApiAdapter.apiClient.getProducts()
                if (response.isSuccessful) {
                    val responseData: ResponseData? = response.body()
                    adapter.items = responseData?.data ?: listOf()
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@SearchActivity, "Error loading data", Toast.LENGTH_SHORT)
                        .show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@SearchActivity, "Error loading data", Toast.LENGTH_SHORT).show()
            }

        }
    }
*/
    private fun adapterOnClick(item: Product) {
        Toast.makeText(this, /*item.text.toString() +*/ " is clicked", Toast.LENGTH_SHORT)
            .show()
    }
}