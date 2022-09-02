package com.xavidev.testessential.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xavidev.testessential.data.entity.Images

@Dao
interface ImagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages(images: List<Images>): Int

    @Query("SELECT * FROM images WHERE id =:imagesId")
    suspend fun getSneakerImages(imagesId: String)
}