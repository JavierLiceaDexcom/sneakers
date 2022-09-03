package com.xavidev.testessential.data.dao

import androidx.room.*
import com.xavidev.testessential.data.entity.Currency
import com.xavidev.testessential.data.entity.Sneaker
import com.xavidev.testessential.data.entity.Type

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

    // Queries for currency
    @Transaction
    suspend fun populateCurrencyTable(currencies: List<Currency>) {
        clearCurrencyTable()
        insertCurrencies(currencies)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencies(currencies: List<Currency>)

    @Query("DELETE FROM currency")
    suspend fun clearCurrencyTable()

    // Queries for sneaker types
    @Transaction
    suspend fun populateTypesTable(types: List<Type>) {
        clearTypesTable()
        insertTypes(types)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTypes(currencies: List<Type>)

    @Query("DELETE FROM type")
    suspend fun clearTypesTable()
}