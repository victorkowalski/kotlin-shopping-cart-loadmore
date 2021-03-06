package com.victor.ko.kotlin_shopping_cart_loadmore.adapters

import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.victor.ko.kotlin_shopping_cart_loadmore.Constant
import com.victor.ko.kotlin_shopping_cart_loadmore.R
import com.victor.ko.kotlin_shopping_cart_loadmore.models.Product


class ItemsLinearAdapter(/*private var itemsCells: ArrayList<String?>*/) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itemsCells: ArrayList<String?> = arrayListOf<String?>()

    lateinit var mcontext: Context

    var items: ArrayList<Product?> = arrayListOf<Product?>()

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun addData(dataViews: ArrayList<Product?>) {
        //this.itemsCells.addAll(dataViews)
        this.items.addAll(dataViews)
        notifyDataSetChanged()
    }

    fun getItemAtPosition(position: Int): /*String?*/Product? {
        //return itemsCells[position]
        return items[position]
    }

    fun addLoadingView() {
        //add loading item
        Handler().post {
            //itemsCells.add(null)
            items.add(null)
            //notifyItemInserted(itemsCells.size - 1)
            notifyItemInserted(items.size - 1)
        }
    }

    fun removeLoadingView() {
        //Remove loading item
        if (items.size != 0) {
            items.removeAt(items.size - 1)
            notifyItemRemoved(items.size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mcontext = parent.context
        return if (viewType == Constant.VIEW_TYPE_ITEM) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_product_item, parent, false)
            ItemViewHolder(view)
        } else {
            val view = LayoutInflater.from(mcontext).inflate(R.layout.progress_loading, parent, false)
            val progressBar: ProgressBar = view.findViewById(R.id.progressbar)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                progressBar.indeterminateDrawable.colorFilter = BlendModeColorFilter(Color.WHITE, BlendMode.SRC_ATOP)
            } else {
                progressBar.indeterminateDrawable.setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY)
            }
            LoadingViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position] == null) {
            Constant.VIEW_TYPE_LOADING
        } else {
            Constant.VIEW_TYPE_ITEM
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder.itemViewType == Constant.VIEW_TYPE_ITEM) {
            //holder.itemView.itemtextview.text = itemsCells[position]
            val textView: TextView = holder.itemView.findViewById(R.id.textView)
            textView?.text = items[position]?.title
        }

    }


}