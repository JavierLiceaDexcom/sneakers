package com.xavidev.testessential.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xavidev.testessential.data.entity.Brand

@Dao
interface BrandsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBrands(brands: List<Brand>): Int

    @Query("SELECT * FROM brand")
    suspend fun getAllBrands(): List<Brand>
}