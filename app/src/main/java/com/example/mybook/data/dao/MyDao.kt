package com.example.mybook.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.mybook.data.model.Theme


@Dao
interface MyDao {

    @Query("SELECT * FROM theme")
    fun getAllTheme() : List<Theme>
}