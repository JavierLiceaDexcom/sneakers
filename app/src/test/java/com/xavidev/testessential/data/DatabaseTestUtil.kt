package com.xavidev.testessential.data

import com.xavidev.testessential.data.entity.Brand
import java.util.*

object DatabaseTestUtil {

    fun createBrand(name: String, logo: String) =
        Brand(
            id = UUID.randomUUID().toString(),
            name = name,
            logo = logo,
            createdAt = Date().time,
            updatedAt = Date().time,
            deletedAt = null
        )

    fun createBrandList() = listOf(
        Brand(
            id = UUID.randomUUID().toString(),
            name = "Nike",
            logo = "https://nikecompanyblog.files.wordpress.com/2015/05/nike1.jpg",
            createdAt = Date().time,
            updatedAt = Date().time,
            deletedAt = null
        ),
        Brand(
            id = UUID.randomUUID().toString(),
            name = "Adidas",
            logo = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/20/Adidas_Logo.svg/2560px-Adidas_Logo.svg.png",
            createdAt = Date().time,
            updatedAt = Date().time,
            deletedAt = null
        ),
    )

}