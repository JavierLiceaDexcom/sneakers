package com.xavidev.testessential.data.entity

import androidx.room.Entity

@Entity(tableName = "images")
data class Images(
    val id: String,
    val images: List<String>
) {
    fun List<Images>.getSneakerImages(sneakerId: String): List<String> {
        return this.filter { it.id == sneakerId }.flatMap { it.images }
    }
}
