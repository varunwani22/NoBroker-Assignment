package com.dev.nbassignment.repository

import androidx.lifecycle.LiveData
import com.dev.nbassignment.data.ApiClient
import com.dev.nbassignment.data.Network
import com.dev.nbassignment.local.ItemsDao
import com.dev.nbassignment.local.ItemsEntity

import kotlinx.coroutines.flow.Flow

class ItemsRepository(private val itemsDao: ItemsDao) {

    private val apiClient = Network.getInstance().create(ApiClient::class.java)
    private lateinit var itemsEntity: ItemsEntity

    suspend fun getItemsList() {
        val result = apiClient.getAllItems()

        for (i in result.indices) {
            itemsEntity =
                ItemsEntity(result[i].image, result[i].title, result[i].subTitle)
            itemsDao.insert(itemsEntity)
        }
    }

    fun getItemList(): LiveData<List<ItemsEntity>> {
        return itemsDao.getAllItems()
    }

    fun searchTitle(searchQuery: String): Flow<List<ItemsEntity>> {
        return itemsDao.searchTitle(searchQuery)
    }
}