package com.xavidev.testessential.data.entity

import java.util.*

data class Card(
    val id: String = UUID.randomUUID().toString(),
    val institutionName: String,
    val expirationDate: String,
    val ownerName: String,
    val cardNumber: String,
    val cardCVV: Int
)
