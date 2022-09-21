package com.xavidev.testessential.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
@Entity(tableName = "images")
data class Images(
    @PrimaryKey
    val id: String,
    val images: List<String>,
    @ColumnInfo(name = "created_at") override val createdAt: Long = Date().time,
    @ColumnInfo(name = "updated_at") override val updatedAt: Long = Date().time,
    @ColumnInfo(name = "deleted_at") override val deletedAt: Long = Date().time,
) : BaseEntity
