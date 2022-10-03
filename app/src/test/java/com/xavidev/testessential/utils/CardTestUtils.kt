package com.xavidev.testessential.utils

import com.xavidev.testessential.data.source.local.entity.Card
import java.util.*

object CardTestUtils {

    fun getSingleCard() = Card(
        id = "4",
        institutionName = "Citibanamex",
        expirationDate = "23/26",
        ownerName = "Francisco Javier Licea",
        cardNumber = "451276524314176253",
        cardCVV = 932,
        isDefault = false
    )

    fun getListOfCards() = listOf(
        Card(
            id = "1",
            institutionName = "Citibanamex",
            expirationDate = "23/26",
            ownerName = "Francisco Javier Licea",
            cardNumber = "451276524314176253",
            cardCVV = 932,
            isDefault = false
        ),
        Card(
            id = "2",
            institutionName = "BBVA",
            expirationDate = "12/23",
            ownerName = "Francisco Javier Licea",
            cardNumber = "451276524314172253",
            cardCVV = 909,
            isDefault = true
        ),
        Card(
            id = "3",
            institutionName = "Citibanamex",
            expirationDate = "01/26",
            ownerName = "Francisco Javier Licea",
            cardNumber = "876276524314176253",
            cardCVV = 112,
            isDefault = false
        )
    )
}