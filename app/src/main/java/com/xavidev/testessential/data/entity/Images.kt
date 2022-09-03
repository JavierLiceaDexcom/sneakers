package com.xavidev.testessential.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.xavidev.testessential.data.ImagesTypeConverter

@TypeConverters(ImagesTypeConverter::class)
@Entity(tableName = "images")
data class Images(
    @PrimaryKey
    val id: String,
    val images: List<String>
) {
    fun List<Images>.getSneakerImages(sneakerId: String): List<String> {
        return this.filter { it.id == sneakerId }.flatMap { it.images }
    }
}
