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

    fun getAddressList() = listOf(
        Address(
            id = "1",
            name = "Javier Licea",
            street = "Street 1",
            zip = 65461,
            state = "Jalisco 1",
            municipality = "Zapopan 1",
            suburb = "Atemajac 1",
            isDefault = true,
            contactNumber = "3317847415",
            extNumber = "351",
            intNumber = null
        ),
        Address(
            id = "2",
            name = "Fco Javier",
            street = "Street 2",
            zip = 65462,
            state = "Jalisco 2",
            municipality = "Zapopan 2",
            suburb = "Atemajac 2",
            isDefault = false,
            contactNumber = "3317847412",
            extNumber = "352",
            intNumber = null
        ),
        Address(
            id = "3",
            name = "Fco Javier",
            street = "Street 3",
            zip = 65463,
            state = "Jalisco 3",
            municipality = "Zapopan 3",
            suburb = "Atemajac 3",
            isDefault = false,
            contactNumber = "3317847415",
            extNumber = "353",
            intNumber = null
        )
    ).sortedBy { it.id }
}