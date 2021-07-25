package com.dev.nbassignment.views

import com.dev.nbassignment.local.ItemsEntity

interface ItemClickListener {
    fun onItemClick(itemsEntity: ItemsEntity, position: Int)
}