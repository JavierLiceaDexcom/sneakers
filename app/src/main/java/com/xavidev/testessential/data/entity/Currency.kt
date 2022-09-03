package com.xavidev.testessential.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.squareup.moshi.JsonClass
import com.xavidev.testessential.data.CurrencyTypeConverter

@JsonClass(generateAdapter = true)
@TypeConverters(CurrencyTypeConverter::class)
@Entity(tableName = "currency")
data class Currency(
    @PrimaryKey val id: String,
    val name: CurrencyType,
    val region: String,
    val icon: String
)

enum class CurrencyType(name: String) {
    MXN("mxn"), USD("usd")
}
