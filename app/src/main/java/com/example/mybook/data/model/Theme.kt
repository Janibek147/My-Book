package com.example.mybook.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Book(
    @PrimaryKey val id: Int,
    val name : String,
    val text: String,
    val favorite : Int,
)