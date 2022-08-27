package com.xavidev.testessential.data.entity

import androidx.room.Entity
import java.io.Serializable
import java.util.*

@Entity(tableName = "sneaker")
data class Sneaker(
    val id: String = UUID.randomUUID().toString(),
    val model: String,
    val sizes: List<Double>,
    val brand: Brand,
    val type: Type,
    val colors: List<Int>,
    val thumbnail: String,
    val photos: List<String>,
    val price: Double,
    val currency: Currency,
    val relatedIds: List<String>,
    val discountPercentage: Int,
    val favorite: Boolean = false
) : Serializable {
    fun List<Sneaker>.filterWithPercentage(percentage: Int = 0): List<Sneaker> =
        this.filter { it.discountPercentage == percentage }.sortedBy { it.id }

    fun List<Sneaker>.filterByColor(color: Int): List<Sneaker> =
        this.filter { it.colors.contains(color) }

    fun List<Sneaker>.getAvailablePercentages(): List<Int> =
        this.filter { it.discountPercentage > 0.0 }.map { it.discountPercentage }

    fun List<Sneaker>.filterByType(type: Type): List<Sneaker> = this.filter { it.type == type }

    fun List<Sneaker>.getRelatedSneakers() = this.map { this.contains(it) }

    fun Sneaker.getPriceByCurrency(currency: Currency): Double {
        return when (currency.name) {
            CurrencyType.DOLLAR -> price
            CurrencyType.MXN -> price * 20.2
        }
    }
}
