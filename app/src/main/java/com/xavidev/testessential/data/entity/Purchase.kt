package com.xavidev.testessential.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "purchase")
data class Purchase(
    @PrimaryKey
    val id: String,
    val sneakerId: String,
    val cardId: String,
    val addressId: String,
    val cost: Double,
    val discount: Double,
    @ColumnInfo(name = "created_at") override val createdAt: Long = Date().time,
    @ColumnInfo(name = "updated_at") override val updatedAt: Long = Date().time,
    @ColumnInfo(name = "deleted_at") override val deletedAt: Long = Date().time,
) : BaseEntity
