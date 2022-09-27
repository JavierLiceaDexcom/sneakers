package com.xavidev.testessential.data.source.local.dao

import androidx.room.*
import com.xavidev.testessential.data.source.local.entity.Card

@Dao
interface CardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(card: Card): Long

    @Delete
    suspend fun deleteCard(card: Card): Int

    @Query("SELECT * FROM card")
    suspend fun getAllCards(): List<Card>

    @Query("SELECT * FROM card WHERE id =:id")
    suspend fun getCard(id: String): Card

    @Query("UPDATE card SET is_default =:value WHERE id =:id")
    suspend fun setDefaultCard(id: String, value: Boolean): Int

    @Query("SELECT * FROM card WHERE is_default = :value")
    suspend fun getDefaultCard(value: Boolean = true): Card?

    @Transaction
    suspend fun updateDefaultCard(newCardId: String) {
        val oldCardId = getDefaultCard()?.id
        if (oldCardId == newCardId) return
        oldCardId?.let { setDefaultCard(it, false) }
        setDefaultCard(newCardId, true)
    }
}