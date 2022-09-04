package com.xavidev.testessential.data

import androidx.room.TypeConverter
import com.google.gson.Gson

object SizesTypeConverter {
    @TypeConverter
    @JvmStatic
    fun toSizesType(value: String): List<Double> =
        Gson().fromJson(value, Array<Double>::class.java).toList()

    @TypeConverter
    @JvmStatic
    fun fromSizesType(value: List<Double>): String = Gson().toJson(value)
}

object ColorsTypeConverter {
    @TypeConverter
    @JvmStatic
    fun jsonToList(value: String): List<String> =
        Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    @JvmStatic
    fun listToJson(value: List<String>): String = Gson().toJson(value)
}