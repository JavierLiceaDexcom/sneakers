package com.xavidev.testessential.data.source.local.dao

import androidx.room.*
import com.xavidev.testessential.data.source.local.entity.Images

@Dao
interface ImagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages(images: List<Images>)

    @Query("DELETE FROM images")
    suspend fun deleteImages(): Int

    @Transaction
    suspend fun populateImages(images: List<Images>) {
        deleteImages()
        insertImages(images)
    }

    @Query("SELECT * FROM images")
    suspend fun getAllImages(): List<Images>
}