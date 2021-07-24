package com.dev.nbassignment.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dev.nbassignment.model.ResponseModel


@Dao
interface ItemsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: List<ResponseModel>)

    @Query("SELECT * FROM items")
    fun getAllItems(): LiveData<List<ResponseModel>>
}