package com.xavidev.testessential.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "images")
data class Images(
    @PrimaryKey
    val id: String,
    val images: List<String>
)
