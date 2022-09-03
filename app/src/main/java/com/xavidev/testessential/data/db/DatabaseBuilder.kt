package com.xavidev.testessential.data.db

import android.content.Context
import androidx.room.Room


class DatabaseBuilder {

    lateinit var database: AppDatabase

    companion object {
        val instance = DatabaseBuilder()
    }

    fun initDatabase(context: Context) {
        createDatabase(context)
    }

    private fun createDatabase(context: Context) {
        database = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}