package com.xavidev.testessential.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.xavidev.testessential.data.ColorsTypeConverter
import com.xavidev.testessential.data.SizesTypeConverter
import com.xavidev.testessential.data.dao.*
import com.xavidev.testessential.data.entity.*

const val DB_NAME = "sneakers_db"
const val DB_VERSION = 1
const val DB_EXPORT_SCHEMA = false

@TypeConverters(SizesTypeConverter::class, ColorsTypeConverter::class)

@Database(
    version = DB_VERSION,
    entities = [
        Brand::class,
        Type::class,
        Sneaker::class,
        KeyValue::class,
        Address::class,
        Card::class,
        Currency::class,
        Cart::class,
        Images::class,
        User::class,
        Purchase::class
    ],
    exportSchema = DB_EXPORT_SCHEMA
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sneakerDao(): SneakersDao
    abstract fun brandsDao(): BrandsDao
    abstract fun cartDao(): CartDao
    abstract fun cardDao(): CardDao
    abstract fun imagesDao(): ImagesDao
    abstract fun keyValueDao(): KeyValueDao
    abstract fun userDao(): UserDao
    abstract fun purchasesDao(): PurchasesDao
}