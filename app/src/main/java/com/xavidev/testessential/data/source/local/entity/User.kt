package com.xavidev.testessential.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey val id: String,
    val name: String,
    @ColumnInfo(name = "currency_id") val currencyId: String,
    val size: Double,
) : BaseEntity()
