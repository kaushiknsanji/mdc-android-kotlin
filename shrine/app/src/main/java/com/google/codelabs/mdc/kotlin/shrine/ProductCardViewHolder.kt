package com.google.codelabs.mdc.kotlin.shrine

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.codelabs.mdc.kotlin.shrine.network.ImageRequester
import com.google.codelabs.mdc.kotlin.shrine.network.ProductEntry
import kotlinx.android.synthetic.main.shr_product_card.view.*

class ProductCardViewHolder(itemView: View)
    : RecyclerView.ViewHolder(itemView) {

    /**
     * Method that binds the Card [itemView] at position with the corresponding [productEntry] data.
     */
    fun bind(productEntry: ProductEntry) {
        itemView.apply {
            // Download and set the Image from the Product URL
            ImageRequester.setImageFromUrl(product_image, productEntry.url)
            // Set the Product Title
            product_title.text = productEntry.title
            // Set the Product Price
            product_price.text = productEntry.price
        }
    }
}
