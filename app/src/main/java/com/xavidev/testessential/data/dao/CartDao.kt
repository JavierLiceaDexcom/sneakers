package com.xavidev.testessential.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xavidev.testessential.data.entity.Sneaker

interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(Sneaker: Sneaker)

    @Delete
    suspend fun deleteCard(sneaker: Sneaker): Int

    @Query("SELECT * FROM cart")
    suspend fun getAllItems(): List<Sneaker>

    @Query("SELECT * FROM cart WHERE id :=id")
    suspend fun getCartItem(id: String): Sneaker
}