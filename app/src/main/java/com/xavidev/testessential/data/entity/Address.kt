package com.xavidev.testessential.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "address")
data class Address(
    @PrimaryKey
    val id: String,
    val street: String,
    val zip: Int,
    val city: String,
    val state: String,
    @ColumnInfo(name = "ext_number") val extNumber: String,
    @ColumnInfo(name = "int_number") val intNumber: String?,
)
