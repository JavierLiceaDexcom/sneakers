package com.xavidev.testessential.data.source.local.entity

import androidx.room.*
import com.squareup.moshi.JsonClass
import java.io.Serializable
import java.util.*

@JsonClass(generateAdapter = true)
@Entity(tableName = "sneaker")
data class Sneaker(
    @PrimaryKey
    val id: String,
    val model: String,
    val sizes: List<Double>,
    @ColumnInfo(name = "brand_id") val brandId: String,
    @ColumnInfo(name = "type_id") val typeId: String,
    val colors: List<String>,
    val thumbnail: String,
    @ColumnInfo(name = "photos_id") val photosId: String,
    val price: Double,
    @ColumnInfo(name = "currency_id") val currencyId: String,
    @ColumnInfo(name = "discount_percentage") val discountPercentage: Int,
    var favorite: Boolean = false,
    @ColumnInfo(name = "in_cart") var inCart: Boolean = false,
) : Serializable, BaseEntity()

data class SneakerComplete(
    val id: String,
    val model: String,
    val brand: String,
    val type: String,
    val colors: List<String>,
    val thumbnail: String,
    val price: Double,
    val currency: String,
    @ColumnInfo(name = "photos_id") val photosId: String,
    val sizes: List<Double>,
    @ColumnInfo(name = "discount_percentage") val discountPercentage: Int,
    var favorite: Boolean = false,
    @ColumnInfo(name = "in_cart") var inCart: Boolean = false
)

fun SneakerComplete.toCart(): Cart {
    return Cart(
        id = UUID.randomUUID().toString(),
        sneakerId = id,
        purchaseDate = Date().time,
        sneakerThumbnail = thumbnail,
        quantity = 1,
    )
}

fun Sneaker.getPriceByCurrency(currency: Currency): Double {
    return when (currency.name) {
        CurrencyType.USD.name -> price
        CurrencyType.MXN.name -> price * 20.2
        else -> price
    }
}
