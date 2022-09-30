package com.xavidev.testessential.utils

import com.xavidev.testessential.data.source.local.entity.Card
import java.util.*

object CardTestUtils {

    fun getSingleCard() = Card(
        id = UUID.randomUUID().toString(),
        institutionName = "Citibanamex",
        expirationDate = "23/26",
        ownerName = "Francisco Javier Licea",
        cardNumber = "451276524314176253",
        cardCVV = 932,
        isDefault = false
    )
}