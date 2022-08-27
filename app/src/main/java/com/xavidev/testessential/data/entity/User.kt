package com.xavidev.testessential.data.entity

import androidx.room.Entity

@Entity(tableName = "user")
data class User(val id: String, val name: String, val currency: Currency, val size: Double)
