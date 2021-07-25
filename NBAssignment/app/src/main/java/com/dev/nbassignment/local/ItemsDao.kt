package com.dev.nbassignment.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface ItemsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(itemsEntity: ItemsEntity)

    @Query("SELECT * FROM items")
    fun getAllItems(): LiveData<List<ItemsEntity>>

    @Query("SELECT * FROM items WHERE title LIKE :searchQuery")
    fun searchTitle(searchQuery: String): Flow<List<ItemsEntity>>
}