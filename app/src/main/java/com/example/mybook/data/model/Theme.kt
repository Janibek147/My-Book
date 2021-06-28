package com.example.mybook.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Theme")
data class Theme(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "text")
    val text: String,
    @ColumnInfo(name = "idchapter")
    val idchapter: Int,
    @ColumnInfo(name = "favorite")
    var favorite: Int = 0

)