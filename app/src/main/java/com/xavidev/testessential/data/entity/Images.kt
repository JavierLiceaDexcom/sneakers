package com.xavidev.testessential.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.squareup.moshi.JsonClass
import com.xavidev.testessential.data.ImagesTypeConverter

@JsonClass(generateAdapter = true)
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
