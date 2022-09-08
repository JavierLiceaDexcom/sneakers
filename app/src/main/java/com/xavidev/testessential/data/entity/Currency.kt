package com.xavidev.testessential.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
@Entity(tableName = "currency")
data class Currency(
    @PrimaryKey val id: String,
    val name: String,
    val region: String,
    val icon: String,
    val abbreviation: String,
    @ColumnInfo(name = "created_at") override val createdAt: Long?,
    @ColumnInfo(name = "updated_at") override val updatedAt: Long?,
    @ColumnInfo(name = "deleted_at") override val deletedAt: Long?,
) : BaseEntity

enum class CurrencyType(name: String) {
    MXN("mxn"), USD("usd")
}
