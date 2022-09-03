package com.xavidev.testessential.data.dao

import androidx.room.*
import com.xavidev.testessential.data.entity.Brand

@Dao
interface BrandsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBrands(brands: List<Brand>)

    @Query("DELETE FROM brand")
    suspend fun clearBrandsTable()

    @Transaction
    suspend fun populateBrandsTable(brands: List<Brand>){
        clearBrandsTable()
        insertBrands(brands)
    }

    @Query("SELECT * FROM brand")
    suspend fun getAllBrands(): List<Brand>
}