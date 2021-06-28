package com.example.mybook.data.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mybook.data.dao.MyDao


@Database(entities = [Theme::class], version = 2)
abstract class ThemeDatabase : RoomDatabase() {

    companion object {
        private lateinit var INSTANCE: ThemeDatabase

        fun getInstance(context: Context): ThemeDatabase {
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    ThemeDatabase::class.java,
                    "My Book.db"
                )
                    .createFromAsset("My Book.db")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }

    }
    abstract fun dao(): MyDao
}