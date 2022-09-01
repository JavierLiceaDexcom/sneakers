package com.xavidev.testessential.data.entity

import androidx.room.Entity
import java.io.Serializable

@Entity(tableName = "currency")
data class Currency(val id: String, val name: CurrencyType, val region: String, val icon: String): Serializable

enum class CurrencyType(name: String) {
    MXN("mxn"), USD("usd")
}
