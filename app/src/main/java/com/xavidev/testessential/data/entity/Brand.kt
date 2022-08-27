package com.xavidev.testessential.data.entity

import androidx.room.Entity

@Entity(tableName = "brand")
data class Brand(val id: String, val name: String, val logo: String, var selected: Boolean = false)