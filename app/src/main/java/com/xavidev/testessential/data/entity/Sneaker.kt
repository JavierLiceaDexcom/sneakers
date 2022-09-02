package com.xavidev.testessential.data.entity

import androidx.room.Entity
import java.io.Serializable

@Entity(tableName = "sneaker")
data class Sneaker(
    val id: String,
    val model: String,
    val sizes: List<Double>,
    val brandId: String,
    val typeId: String,
    val colors: List<String>,
    val thumbnail: String,
    val photosId: String,
    val price: Double,
    val currencyId: String,
    val discountPercentage: Int,
    val favorite: Boolean = false
) : Serializable {
    fun List<Sneaker>.filterWithPercentage(percentage: Int = 0): List<Sneaker> =
        this.filter { it.discountPercentage == percentage }.sortedBy { it.id }

    fun List<Sneaker>.filterByColor(color: String): List<Sneaker> =
        this.filter { it.colors.contains(color) }

    fun List<Sneaker>.getAvailablePercentages(): List<Int> =
        this.filter { it.discountPercentage > 0.0 }.map { it.discountPercentage }

    fun List<Sneaker>.filterByType(typeId: String): List<Sneaker> =
        this.filter { it.typeId == typeId }

    fun List<Sneaker>.getRelatedSneakers() = this.map { this.contains(it) }

    fun Sneaker.getPriceByCurrency(currency: Currency): Double {
        return when (currency.name) {
            CurrencyType.USD -> price
            CurrencyType.MXN -> price * 20.2
        }
    }
}
