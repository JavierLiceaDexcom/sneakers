package com.xavidev.testessential.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "currency")
data class Currency(
    @PrimaryKey val id: String,
    val name: String,
    val region: String,
    val icon: String,
    val abbreviation: String,
) : BaseEntity()

enum class CurrencyType(name: String) {
    MXN("mxn"), USD("usd")
}
