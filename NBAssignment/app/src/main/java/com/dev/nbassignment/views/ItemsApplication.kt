package com.dev.nbassignment.views

import android.app.Application
import com.dev.nbassignment.local.ItemsDatabase
import com.dev.nbassignment.repository.ItemsRepository

class ItemsApplication : Application() {
    private val itemsDao by lazy {
        val roomDatabase = ItemsDatabase.getDatabase(this)
        roomDatabase.getItemsDao()
    }

    val repository by lazy {
        ItemsRepository(itemsDao)
    }
}