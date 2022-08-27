package com.xavidev.testessential.data.entity

import androidx.room.Entity

@Entity(tableName = "currency")
data class Currency(val id: String, val name: CurrencyType, val region: String, val icon: String)

enum class CurrencyType(name: String) {
    MXN("mxn"), DOLLAR("usd")
}
