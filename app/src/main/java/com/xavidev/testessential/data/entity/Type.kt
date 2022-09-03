package com.xavidev.testessential.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.squareup.moshi.JsonClass
import com.xavidev.testessential.data.SneakerTypeConverter

@JsonClass(generateAdapter = true)
@TypeConverters(SneakerTypeConverter::class)
@Entity(tableName = "type")
data class Type(@PrimaryKey val id: String, val name: SneakerType)

enum class SneakerType(type: String) {
    SPORT("sport"), CASUAL("casual"), SKATE("skate"), TRAINING("training"), FORMAL("formal")
}
