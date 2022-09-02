package com.xavidev.testessential.data.entity

import androidx.room.Entity

@Entity(tableName = "key_value")
data class KeyValue(
    val key: String,
    val value: String
)
