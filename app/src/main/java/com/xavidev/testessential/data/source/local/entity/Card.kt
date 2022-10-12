package com.xavidev.testessential.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "card")
data class Card(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "institution_name") val institutionName: String,
    @ColumnInfo(name = "expiration_date") val expirationDate: String,
    @ColumnInfo(name = "owner_name") val ownerName: String,
    @ColumnInfo(name = "card_number") val cardNumber: String,
    @ColumnInfo(name = "card_cvv") val cardCVV: Int,
    @ColumnInfo(name = "is_default") val isDefault: Boolean,
) : BaseEntity() {
    val lastDigits: String
        get() = "End with ${this.cardNumber.takeLast(4)}"

    val expirationDateFormatted: String
        get() = "${this.expirationDate.take(2)}/${this.expirationDate.takeLast(2)}"
}
