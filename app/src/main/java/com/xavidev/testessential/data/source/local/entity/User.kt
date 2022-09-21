package com.xavidev.testessential.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey val id: String,
    val name: String,
    @ColumnInfo(name = "currency_id") val currencyId: String,
    val size: Double,
    @ColumnInfo(name = "created_at") override val createdAt: Long?,
    @ColumnInfo(name = "updated_at") override val updatedAt: Long?,
    @ColumnInfo(name = "deleted_at") override val deletedAt: Long?,
) : BaseEntity
