package com.xavidev.testessential.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xavidev.testessential.data.entity.Purchase

@Dao
interface PurchasesDao {

    //Insert purchases
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPurchase(purchase: Purchase): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPurchases(purchases: List<Purchase>)

    //Get purchases
    @Query("SELECT * FROM purchase")
    suspend fun getAllPurchases(): List<Purchase>

    @Query("SELECT * FROM purchase WHERE deleted_at IS NOT NULL")
    suspend fun getNonDeletedPurchases(): List<Purchase>

    @Query("SELECT * FROM purchase WHERE deleted_at IS NULL")
    suspend fun getDeletedPurchases(): List<Purchase>
}