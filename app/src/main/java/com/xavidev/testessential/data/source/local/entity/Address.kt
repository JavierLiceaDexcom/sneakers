package com.xavidev.testessential.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "address")
data class Address(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val street: String,
    val zip: Int,
    val state: String,
    val municipality: String,
    val suburb: String,
    @ColumnInfo(name = "is_default") val isDefault: Boolean = false,
    @ColumnInfo(name = "contact_number") val contactNumber: String,
    @ColumnInfo(name = "ext_number") val extNumber: String,
    @ColumnInfo(name = "int_number") val intNumber: String? = null
) : BaseEntity() {

    val completeAddress: String
        get() = "${this.street}, ${this.zip}, ${this.suburb}, ${this.municipality}"
}
