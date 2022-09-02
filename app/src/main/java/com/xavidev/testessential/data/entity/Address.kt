package com.xavidev.testessential.data.entity

import androidx.room.Entity
import java.util.*

@Entity(tableName = "address")
data class Address(
    val id: String = UUID.randomUUID().toString(),
    val street: String,
    val zip: Int,
    val city: String,
    val state: String,
    val extNumber: String,
    val intNumber: String?,
)
