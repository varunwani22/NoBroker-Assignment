package com.dev.nbassignment.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ItemsEntity(
    /**
     * Column of tables
     */

    @ColumnInfo(name = "image") var image: String?,
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "subTitle") var subTitle: String?
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}