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

    /**
     * getItemsList is use to insert the retrofit response into RoomDatabase
     **/
    suspend fun getItemsList() {
        val result = apiClient.getAllItems()

        for (i in result.indices) {
            /**
             * As the response is fetched, the user will navigate back to view model as this callback is being implemented
             * in the ViewModel class
             **/
            itemsEntity =
                ItemsEntity(result[i].image, result[i].title, result[i].subTitle)
            itemsDao.insert(itemsEntity)
        }
    }

    /**
     *getting list from room database
     */

    fun getItemList(): LiveData<List<ItemsEntity>> {
        return itemsDao.getAllItems()
    }

    /**
     * searchTitle is use for search the query from RoomDatabase
     **/
    fun searchTitle(searchQuery: String): Flow<List<ItemsEntity>> {
        return itemsDao.searchTitle(searchQuery)
    }
}