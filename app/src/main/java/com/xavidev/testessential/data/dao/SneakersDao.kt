package com.xavidev.testessential.data.dao

import androidx.room.*
import com.xavidev.testessential.data.entity.Sneaker

@Dao
interface SneakersDao {

    //TODO: Implement Paging 3 library

    //Transaction to clear table and populate it
    @Transaction
    suspend fun insertSneakers(sneakers: List<Sneaker>) {
        clearSneakerTable()
        insertAll(sneakers)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(sneakers: List<Sneaker>)

    @Query("DELETE FROM sneaker")
    suspend fun clearSneakerTable()

    @Query("SELECT * FROM sneaker")
    suspend fun getAllSneakers(): List<Sneaker>

    @Query("SELECT * FROM sneaker WHERE brand_id =:id")
    suspend fun getSneakersByBrand(id: String): List<Sneaker>

    @Query("SELECT * FROM sneaker WHERE type_id =:id")
    suspend fun getSneakersByType(id: String): List<Sneaker>

    @Query("SELECT * FROM sneaker WHERE id =:id")
    suspend fun getSneaker(id: String): Sneaker
}