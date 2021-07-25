package com.dev.nbassignment.views

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev.nbassignment.local.ItemsEntity
import kotlinx.android.synthetic.main.item_layout.view.*

/**
 * ViewHolder class for recyclerview
 */
class ItemsViewHolder(private val itemView: View, var onClickListener: ItemClickListener) : RecyclerView.ViewHolder(itemView) {

    fun setData(responseModelItem: ItemsEntity) {
        itemView.apply {
            Glide.with(ivImage).load(responseModelItem.image).into(ivImage)
            tvTitle.text = responseModelItem.title
            rLayout.setOnClickListener {
                onClickListener.onItemClick(responseModelItem, adapterPosition)
            }
        }

    }
}