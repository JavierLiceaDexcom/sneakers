package com.xavidev.testessential.data.dao

import androidx.room.*
import com.xavidev.testessential.data.entity.*

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

    @Query(
        "SELECT sneaker.id, sneaker.model, sneaker.colors, sneaker.thumbnail, sneaker.price, sneaker.sizes, sneaker.photos_id, " +
                "sneaker.discount_percentage, sneaker.favorite, brand.name AS brand, type.name AS type, currency.abbreviation AS currency " +
                "FROM sneaker INNER JOIN brand ON sneaker.brand_id = brand.id INNER JOIN type ON sneaker.type_id = type.id " +
                "INNER JOIN currency ON sneaker.currency_id = currency.id"
    )
    suspend fun getAllCompleteSneakers(): List<SneakerComplete>

    @Query("SELECT count(*) FROM sneaker")
    suspend fun getSneakersCount(): Int

    @Query("SELECT sneaker.id, sneaker.model, sneaker.colors, sneaker.thumbnail, sneaker.price, sneaker.sizes, sneaker.photos_id, " +
            "sneaker.discount_percentage, sneaker.favorite, brand.name AS brand, type.name AS type, currency.abbreviation AS currency " +
            "FROM sneaker INNER JOIN brand ON sneaker.brand_id = brand.id INNER JOIN type ON sneaker.type_id = type.id " +
            "INNER JOIN currency ON sneaker.currency_id = currency.id WHERE sneaker.brand_id =:id")
    suspend fun getSneakersByBrand(id: String): List<SneakerComplete>

    @Query("SELECT * FROM sneaker WHERE type_id =:id")
    suspend fun getSneakersByType(id: String): List<Sneaker>

    @Query("SELECT sneaker.id, sneaker.model, sneaker.colors, sneaker.thumbnail, sneaker.price, sneaker.sizes, sneaker.photos_id, " +
            "sneaker.discount_percentage, sneaker.favorite, brand.name AS brand, type.name AS type, currency.abbreviation AS currency " +
            "FROM sneaker INNER JOIN brand ON sneaker.brand_id = brand.id INNER JOIN type ON sneaker.type_id = type.id " +
            "INNER JOIN currency ON sneaker.currency_id = currency.id WHERE sneaker.id =:id")
    suspend fun getSneaker(id: String): SneakerComplete

    @Query("UPDATE sneaker SET favorite =:value WHERE id =:id")
    suspend fun updateFavoriteValue(id: String, value: Boolean): Int

    @Query("SELECT * FROM images WHERE id =:id")
    suspend fun getSneakerImages(id: String): Images

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