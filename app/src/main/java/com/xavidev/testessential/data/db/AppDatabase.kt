package com.xavidev.testessential.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xavidev.testessential.data.dao.*
import com.xavidev.testessential.data.entity.*

const val DB_NAME = "sneakers_db"
const val DB_VERSION = 1
const val DB_EXPORT_SCHEMA = false

@Database(
    version = DB_VERSION,
    entities = [Brand::class, Type::class, Sneaker::class, KeyValue::class, Address::class, Card::class, Currency::class, Cart::class],
    exportSchema = DB_EXPORT_SCHEMA
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sneakerDao(): SneakersDao
    abstract fun brandsDao(): BrandsDao
    abstract fun imagesDao(): ImagesDao
    abstract fun cartDao(): CartDao
    abstract fun cardDao(): CardDao
    abstract fun keyValueDao(): KeyValueDao
    abstract fun userDao(): UserDao
}