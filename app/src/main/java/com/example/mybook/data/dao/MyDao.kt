package com.example.mybook.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.mybook.data.model.Theme
import io.reactivex.rxjava3.core.Maybe


@Dao
interface MyDao {

    @Query("SELECT * FROM Theme")
    fun getAllTheme() : List<Theme>

    @Query("SELECT * FROM Theme WHERE idchapter=:id ")
    fun getTextByChapterId(id:Int) : List<Theme>

    @Query("SELECT * FROM Theme WHERE id=:id")
    fun getTextById(id: Int) : Maybe<Theme>

    @Query( "SELECT * FROM Theme WHERE favorite =1")
    fun getFavoriteTheme(): List<Theme>

    @Query("SELECT * FROM Theme WHERE id=:id and text like :word")
    fun searchThemeByName(id: Int, word: String): List<Theme>

    @Update
    fun updateTheme( theme: Theme)
}