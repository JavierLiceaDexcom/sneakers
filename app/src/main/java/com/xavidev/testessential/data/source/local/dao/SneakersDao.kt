package com.xavidev.testessential.data.source.local.dao

import androidx.room.*
import com.xavidev.testessential.data.source.local.entity.*

@Dao
interface SneakersDao {

    //TODO: Implement Paging 3 library
    companion object {
        private const val SNEAKER_JOIN =
            "SELECT sneaker.id, sneaker.model, sneaker.colors, sneaker.thumbnail, sneaker.price, sneaker.sizes, sneaker.photos_id, " +
                    "sneaker.discount_percentage, sneaker.favorite, sneaker.in_cart, brand.name AS brand, type.name AS type, currency.abbreviation AS currency " +
                    "FROM sneaker INNER JOIN brand ON sneaker.brand_id = brand.id INNER JOIN type ON sneaker.type_id = type.id " +
                    "INNER JOIN currency ON sneaker.currency_id = currency.id"
    }

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

    @Query(SNEAKER_JOIN)
    suspend fun getAllCompleteSneakers(): List<SneakerComplete>

    @Query("$SNEAKER_JOIN WHERE sneaker.id IN (:ids)")
    suspend fun getSneakersByIds(ids: List<String>): List<SneakerComplete>

    @Query("SELECT count(*) FROM sneaker")
    suspend fun getSneakersCount(): Int

    @Query("$SNEAKER_JOIN WHERE sneaker.brand_id =:id")
    suspend fun getSneakersByBrand(id: String): List<SneakerComplete>

    @Query("SELECT * FROM sneaker WHERE type_id =:id")
    suspend fun getSneakersByType(id: String): List<Sneaker>

    @Query("$SNEAKER_JOIN WHERE sneaker.id =:id")
    suspend fun getSneaker(id: String): SneakerComplete

    @Query("UPDATE sneaker SET favorite =:value WHERE id =:id")
    suspend fun updateFavoriteValue(id: String, value: Boolean): Int

    @Query("UPDATE sneaker SET in_cart =:value WHERE id =:id")
    suspend fun updateSneakerInCart(id: String, value: Boolean): Int

    @Query("SELECT * FROM images WHERE id =:id")
    suspend fun getSneakerImages(id: String): Images

    @Query("$SNEAKER_JOIN WHERE sneaker.model LIKE '%' || :query || '%'")
    suspend fun searchSneakersByName(query: String): List<SneakerComplete>

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

    @Query("SELECT * FROM currency")
    suspend fun getCurrencies(): List<Currency>

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

    @Query("SELECT * FROM type")
    suspend fun getTypes(): List<Type>
}