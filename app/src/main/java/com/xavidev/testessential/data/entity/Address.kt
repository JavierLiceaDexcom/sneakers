package com.xavidev.testessential.data.entity

import java.util.*

data class Address(
    val id: String = UUID.randomUUID().toString(),
    val street: String,
    val zip: Int,
    val city: String,
    val state: String,
    val extNumber: String,
    val intNumber: String?,
)
