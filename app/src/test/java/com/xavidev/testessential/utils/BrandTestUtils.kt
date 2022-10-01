package com.xavidev.testessential.utils

import com.xavidev.testessential.data.source.local.entity.Brand

object BrandTestUtils {

    fun getListOfBrands() = listOf(
        Brand(
            id = "1",
            name = "Adidas",
            logo = "logo"
        ),
        Brand(
            id = "2",
            name = "Nike",
            logo = "logo"
        ),
        Brand(
            id = "3",
            name = "Under Armour",
            logo = "logo"
        ),
        Brand(
            id = "4",
            name = "Reebok",
            logo = "logo"
        ),
        Brand(
            id = "5",
            name = "Vans",
            logo = "logo"
        )
    )
}