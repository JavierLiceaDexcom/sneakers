package com.xavidev.testessential.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "purchase")
data class Purchase(
    @PrimaryKey
    val id: String,
    val sneakerId: String,
    val cardId: String,
    val addressId: String,
    val cost: Double,
    val discount: Double,
) : BaseEntity()
