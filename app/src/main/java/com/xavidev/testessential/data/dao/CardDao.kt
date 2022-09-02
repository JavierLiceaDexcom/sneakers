package com.xavidev.testessential.data.dao

import androidx.room.*
import com.xavidev.testessential.data.entity.Card

@Dao
interface CardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(card: Card): Int

    @Delete
    suspend fun deleteCard(card: Card): Int

    @Query("SELECT * FROM card")
    suspend fun getAllCards(): List<Card>

    @Query("SELECT * FROM card WHERE id :=id")
    suspend fun getCard(id: String): Card

    @Query("UPDATE card SET isDefault =:value")
    suspend fun setDefaultCard(card: Card, value: Boolean): Int

    @Transaction
    suspend fun updateDefaultCard(oldCard: Card, newCard: Card) {
        setDefaultCard(oldCard, false)
        setDefaultCard(newCard, true)
    }
}