package com.xavidev.testessential.data.entity

import java.util.*

data class Cart(
    val id: String = UUID.randomUUID().toString(),
    val sneakerId: String,
    val purchaseDate: Long,
    val sneakerThumbnail: String,
    val quantity: Int = 1
)