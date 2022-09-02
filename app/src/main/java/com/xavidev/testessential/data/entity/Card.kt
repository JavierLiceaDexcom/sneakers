package com.xavidev.testessential.data.entity

import androidx.room.Entity
import java.util.*

@Entity(tableName = "card")
data class Card(
    val id: String = UUID.randomUUID().toString(),
    val institutionName: String,
    val expirationDate: String,
    val ownerName: String,
    val cardNumber: String,
    val cardCVV: Int,
    val isDefault: Boolean
)
