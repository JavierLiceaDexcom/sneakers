package com.xavidev.testessential.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "type")
data class Type(@PrimaryKey val id: String, val name: String)
