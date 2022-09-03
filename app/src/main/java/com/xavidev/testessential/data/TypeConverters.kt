package com.xavidev.testessential.data

import androidx.room.TypeConverter
import com.xavidev.testessential.data.entity.CurrencyType
import com.xavidev.testessential.data.entity.SneakerType

object SneakerTypeConverter {
    @TypeConverter
    @JvmStatic
    fun toSneakerType(value: String) = enumValueOf<SneakerType>(value)

    @TypeConverter
    @JvmStatic
    fun fromSneakerType(value: SneakerType) = value.name
}

object CurrencyTypeConverter {
    @TypeConverter
    @JvmStatic
    fun toCurrencyType(value: String) = enumValueOf<CurrencyType>(value)

    @TypeConverter
    @JvmStatic
    fun fromCurrencyType(value: CurrencyType) = value.name
}

object SizesTypeConverter {
    @TypeConverter
    @JvmStatic
    fun toSizesType(value: String): MutableList<Double> =
        value.split(", ").map { it.toDouble() }.toMutableList()

    @TypeConverter
    @JvmStatic
    fun fromSizesType(value: List<Double>): String = value.map { it }.joinToString { ", " }
}

object ColorsTypeConverter {
    @TypeConverter
    @JvmStatic
    fun toColorsType(value: String): MutableList<String> =
        value.split(", ").map { it }.toMutableList()

    @TypeConverter
    @JvmStatic
    fun fromColorsType(value: List<String>): String = value.map { it }.joinToString { ", " }
}

object ImagesTypeConverter {
    @TypeConverter
    @JvmStatic
    fun toImagessType(value: String): MutableList<String> =
        value.split(", ").map { it }.toMutableList()

    @TypeConverter
    @JvmStatic
    fun fromImagesType(value: List<String>): String = value.map { it }.joinToString { ", " }
}