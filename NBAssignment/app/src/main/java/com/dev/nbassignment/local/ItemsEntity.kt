package com.dev.nbassignment.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ItemsEntity(
    @ColumnInfo(name = "image") var image: String?,
    @ColumnInfo(name = "image") var title: String?,
    @ColumnInfo(name = "image") var subTitle: String?
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}