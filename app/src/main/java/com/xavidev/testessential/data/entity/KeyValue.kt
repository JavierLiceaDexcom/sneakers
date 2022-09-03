package com.xavidev.testessential.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "key_value")
data class KeyValue(
    @PrimaryKey
    val key: String,
    val value: String
)
