package com.example.mybook.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mybook.data.dao.MyDao
import com.example.mybook.data.model.Theme


@Database(entities = [Theme::class], version = 1)
abstract class ThemeDatabase : RoomDatabase() {

    companion object {
        private lateinit var INSTANCE: ThemeDatabase

        fun getInstance(context: Context): ThemeDatabase =
            Room.databaseBuilder(
                context,
                ThemeDatabase::class.java,
                "MyBook"
            )
                .createFromAsset("MyBook")
                .build()
    }


    abstract fun dao(): MyDao
}