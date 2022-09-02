package com.xavidev.testessential.data.dao

import androidx.room.*
import com.xavidev.testessential.data.entity.Images

@Dao
interface ImagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages(images: List<Images>): Int

    @Query("DELETE FROM images")
    suspend fun deleteImages(): Int

    @Transaction
    suspend fun populateImages(images: List<Images>) {
        deleteImages()
        insertImages(images)
    }

    @Query("SELECT * FROM images WHERE id =:imagesId")
    suspend fun getSneakerImages(imagesId: String): List<Images>
}