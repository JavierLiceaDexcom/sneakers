package com.xavidev.testessential.utils

import com.xavidev.testessential.data.source.local.entity.Address
import java.util.*

object AddressTestUtils {

    fun getSingleAddress() = Address(
        id = UUID.randomUUID().toString(),
        name = "Fco Javier",
        street = "Street",
        zip = 65465,
        state = "Jalisco",
        municipality = "Zapopan",
        suburb = "Atemajac",
        isDefault = false,
        contactNumber = "3317847415",
        extNumber = "355",
        intNumber = null
    )
}