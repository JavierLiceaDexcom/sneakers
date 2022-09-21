package com.xavidev.testessential.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class Cart(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "sneaker_id") val sneakerId: String,
    @ColumnInfo(name = "purchase_date") val purchaseDate: Long,
    @ColumnInfo(name = "sneaker_thumbnail") val sneakerThumbnail: String,
    val quantity: Int = 1,
) : BaseEntity()