package com.xavidev.testessential.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "brand")
data class Brand(
    @PrimaryKey val id: String,
    val name: String,
    val logo: String,
    var selected: Boolean = false
)