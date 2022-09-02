package com.xavidev.testessential.utils

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.xavidev.testessential.data.entity.Brand
import com.xavidev.testessential.data.entity.Images
import java.io.FileNotFoundException
import java.nio.charset.Charset

object JsonParserUtils {

    private fun loadJSONFormAssets(fileName: String): String? {
        return try {
            val inputStream = App.getContext().assets.open("$fileName.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charset.forName("UTF-8"))
        } catch (ex: FileNotFoundException) {
            ex.printStackTrace()
            null
        }
    }

    fun getBrandListFromJSON(): List<Brand> {
        val resultType = Types.newParameterizedType(List::class.java, Brand::class.java)
        val json = loadJSONFormAssets("brands") ?: ""
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<List<Brand>> = moshi.adapter(resultType)
        return jsonAdapter.fromJson(json) ?: listOf()
    }

    fun getImagesListFromJSON(): List<Images> {
        val resultType = Types.newParameterizedType(List::class.java, Images::class.java)
        val json = loadJSONFormAssets("images") ?: ""
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<List<Images>> = moshi.adapter(resultType)
        return jsonAdapter.fromJson(json) ?: listOf()
    }

    fun <T: Any> getObjectListFromJSON(objectType: T, fileName: String): List<T> {
        val resultType = Types.newParameterizedType(List::class.java, objectType::class.java)
        val json = loadJSONFormAssets(fileName) ?: ""
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<List<T>> = moshi.adapter(resultType)
        return jsonAdapter.fromJson(json) ?: listOf()
    }

}
