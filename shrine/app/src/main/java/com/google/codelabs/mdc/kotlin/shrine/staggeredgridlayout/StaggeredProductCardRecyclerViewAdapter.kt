package com.google.codelabs.mdc.kotlin.shrine.staggeredgridlayout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.codelabs.mdc.kotlin.shrine.R
import com.google.codelabs.mdc.kotlin.shrine.network.ProductEntry

/**
 * Adapter used to show an asymmetric grid of products, with 2 items in the first column, and 1
 * item in the second column, and so on.
 */
class StaggeredProductCardRecyclerViewAdapter(private val productList: List<ProductEntry>?) : RecyclerView.Adapter<StaggeredProductCardViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return position % 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaggeredProductCardViewHolder {
        val layoutId = when (viewType) {
            1 -> R.layout.shr_staggered_product_card_second
            2 -> R.layout.shr_staggered_product_card_third
            else -> R.layout.shr_staggered_product_card_first
        }

        val layoutView = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return StaggeredProductCardViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: StaggeredProductCardViewHolder, position: Int) {
        if (productList != null && position < productList.size) {
            // When the position is valid, bind the corresponding ItemView with its data
            holder.bind(productList[position])
        }
    }

    override fun getItemCount(): Int {
        return productList?.size ?: 0
    }
}
