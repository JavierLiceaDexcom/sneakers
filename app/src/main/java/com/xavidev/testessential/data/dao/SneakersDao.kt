package com.xavidev.testessential.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xavidev.testessential.data.entity.Sneaker

@Dao
interface SneakersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(sneakers: List<Sneaker>)

    @Query("SELECT * FROM sneakers")
    suspend fun getAllSneakers(): List<Sneaker>

    @Query("SELECT * FROM sneaker WHERE brandId =:id")
    suspend fun getSneakersByBrand(id: String): List<Sneaker>

    @Query("SELECT * FROM sneaker WHERE id =:id")
    suspend fun getSneaker(id: String): Sneaker
}