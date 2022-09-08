package com.xavidev.testessential.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
@Entity(tableName = "brand")
data class Brand(
    @PrimaryKey val id: String,
    val name: String,
    val logo: String,
    @ColumnInfo(name = "created_at") override val createdAt: Long?,
    @ColumnInfo(name = "updated_at") override val updatedAt: Long?,
    @ColumnInfo(name = "deleted_at") override val deletedAt: Long?,
) : BaseEntity