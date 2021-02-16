
package com.victor.ko.kotlin_shopping_cart_loadmore.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.victor.ko.kotlin_shopping_cart_loadmore.R
import com.victor.ko.kotlin_shopping_cart_loadmore.models.Product


class RecyclerViewAdapter(
    /*private val items: List<Product>, */private val onClick: (Product) -> Unit
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    var items: List<Product> = emptyList()
    //mutableListOf()

    class ViewHolder(itemView: View, val onClick: (Product) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        private val textView: TextView = itemView.findViewById(R.id.textView)
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val relativeLayout: RelativeLayout = itemView.findViewById(R.id.relativeLayout)

        //private val relativeLayout = itemView.findViewById(R.id.linearLayout) as RelativeLayout?
        private var currentDataModel: Product? = null

        init {
            itemView.setOnClickListener {
                currentDataModel?.let {
                    onClick(it)
                }
            }
        }

        fun bind(dataModel: Product) {
            currentDataModel = dataModel

            //textView.text = dataModel.text
            //imageView.setImageResource(dataModel.drawable)
            //relativeLayout.setBackgroundColor(Color.parseColor(dataModel.color))
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_product_item, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
