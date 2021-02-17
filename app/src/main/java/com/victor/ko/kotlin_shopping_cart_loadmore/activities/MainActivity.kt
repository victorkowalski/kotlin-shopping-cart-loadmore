package com.victor.ko.kotlin_shopping_cart_loadmore.activities

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.victor.ko.kotlin_shopping_cart_loadmore.adapters.ItemsLinearAdapter
import com.victor.ko.kotlin_shopping_cart_loadmore.databinding.ActivitySearchBinding
import com.victor.ko.kotlin_shopping_cart_loadmore.models.ResponseData
import com.victor.ko.scart.network.ApiAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var bnd: ActivitySearchBinding

    lateinit var itemsCells: ArrayList<String?>
    lateinit var loadMoreItemsCells: ArrayList<String?>
    lateinit var adapterLinear: ItemsLinearAdapter
    lateinit var scrollListener: RecyclerViewLoadMoreScroll
    lateinit var mLayoutManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bnd = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(bnd.root)

        setItemsData()

        setAdapter()

        setLayoutManager()

        setScrollListener()
    }


    private fun setItemsData() {

        /*itemsCells = ArrayList()
        for (i in 0..40) {
            itemsCells.add("Item $i")
        }*/

        CoroutineScope(Dispatchers.Main).launch() {
            try {
                val response = ApiAdapter.apiClient.getProducts()
                if (response.isSuccessful) {
                    val responseData: ResponseData? = response.body()
                    adapterLinear.items = responseData?.data ?: listOf()
                    adapterLinear.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@MainActivity, "Error loading data", Toast.LENGTH_SHORT)
                        .show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@MainActivity, "Error loading data", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun setAdapter() {
        adapterLinear = ItemsLinearAdapter(itemsCells)
        adapterLinear.notifyDataSetChanged()
        bnd.myRecycleView.adapter = adapterLinear
    }

    private fun setLayoutManager() {
        mLayoutManager = LinearLayoutManager(this)
        bnd.myRecycleView.layoutManager = mLayoutManager
        bnd.myRecycleView.setHasFixedSize(true)
    }

    private  fun setScrollListener() {
        mLayoutManager = LinearLayoutManager(this)
        scrollListener = RecyclerViewLoadMoreScroll(mLayoutManager as LinearLayoutManager)
        scrollListener.setOnLoadMoreListener(object :
            OnLoadMoreListener {
            override fun onLoadMore() {
                LoadMoreData()
            }
        })
        bnd.myRecycleView.addOnScrollListener(scrollListener)
    }

    private fun LoadMoreData() {
        //Add the Loading View
        adapterLinear.addLoadingView()
        //Create the loadMoreItemsCells Arraylist
        loadMoreItemsCells = ArrayList()
        //Get the number of the current Items of the main Arraylist
        val start = adapterLinear.itemCount
        //Load 16 more items
        val end = start + 16
        //Use Handler if the items are loading too fast.
        //If you remove it, the data will load so fast that you can't even see the LoadingView
        Handler().postDelayed({
            for (i in start..end) {
                //Get data and add them to loadMoreItemsCells ArrayList
                loadMoreItemsCells.add("Item $i")
            }
            //Remove the Loading View
            adapterLinear.removeLoadingView()
            //We adding the data to our main ArrayList
            adapterLinear.addData(loadMoreItemsCells)
            //Change the boolean isLoading to false
            scrollListener.setLoaded()
            //Update the recyclerView in the main thread
            bnd.myRecycleView.post {
                adapterLinear.notifyDataSetChanged()
            }
        }, 3000)

    }

}
