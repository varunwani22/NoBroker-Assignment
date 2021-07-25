package com.dev.nbassignment.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.nbassignment.R
import com.dev.nbassignment.local.ItemsEntity
import com.dev.nbassignment.model.ResponseModelItem

/**
 * Adapter class for recyclerview
 */
class ItemsAdapter(private var itemList: List<ItemsEntity>, var onClickListener: ItemClickListener) :
    RecyclerView.Adapter<ItemsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemsViewHolder(view, onClickListener)
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val items = itemList[position]
        holder.setData(items)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun updateData(itemList: List<ItemsEntity>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }


}