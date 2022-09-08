package com.xavidev.testessential.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "card")
data class Card(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "institution_name") val institutionName: String,
    @ColumnInfo(name = "expiration_date") val expirationDate: String,
    @ColumnInfo(name = "owner_name") val ownerName: String,
    @ColumnInfo(name = "card_number") val cardNumber: String,
    @ColumnInfo(name = "card_cvv") val cardCVV: Int,
    @ColumnInfo(name = "is_default") val isDefault: Boolean,
    @ColumnInfo(name = "created_at") override val createdAt: Long?,
    @ColumnInfo(name = "updated_at") override val updatedAt: Long?,
    @ColumnInfo(name = "deleted_at") override val deletedAt: Long?,
) : BaseEntity
