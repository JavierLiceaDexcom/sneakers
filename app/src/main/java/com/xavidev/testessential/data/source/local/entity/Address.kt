package com.xavidev.testessential.data.source.local.entity

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
    @ColumnInfo(name = "created_at") override val createdAt: Long?,
    @ColumnInfo(name = "updated_at") override val updatedAt: Long?,
    @ColumnInfo(name = "deleted_at") override val deletedAt: Long?,
) : BaseEntity
