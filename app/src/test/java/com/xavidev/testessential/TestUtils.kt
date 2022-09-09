package com.xavidev.testessential

import com.xavidev.testessential.data.entity.Sneaker
import java.util.*

object TestUtils {

    fun getSneaker() = Sneaker(
        id = UUID.randomUUID().toString(),
        "",
        sizes = listOf(25.3, 26.0),
        brandId = "",
        typeId = "",
        colors = listOf("", "", ""),
        thumbnail = "",
        photosId = "",
        price = 0.0,
        currencyId = "",
        discountPercentage = 0,
        favorite = false,
        inCart = false,
        createdAt = Date().time,
        updatedAt = Date().time,
        deletedAt = Date().time
    )
}