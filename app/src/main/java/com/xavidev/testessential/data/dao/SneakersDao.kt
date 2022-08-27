package com.xavidev.testessential.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xavidev.testessential.data.entity.Sneaker

@Dao
interface SneakersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(sneaker: Sneaker)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(sneakers: List<Sneaker>)
}