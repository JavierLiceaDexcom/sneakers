package com.xavidev.testessential.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "brand")
data class Brand(
    @PrimaryKey val id: String,
    val name: String,
    val logo: String,
) : BaseEntity()