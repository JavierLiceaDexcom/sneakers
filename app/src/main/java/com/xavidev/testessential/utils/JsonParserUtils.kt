package com.xavidev.testessential.utils

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
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

    fun <T : Any> getObjectListFromJSON(objectType: Class<T>, fileName: String): List<T> {
        val resultType = Types.newParameterizedType(List::class.java, objectType)
        val json = loadJSONFormAssets(fileName) ?: ""
        val moshi = Moshi.Builder().add(com.squareup.moshi.KotlinJsonAdapterFactory()).build()
        val jsonAdapter: JsonAdapter<List<T>> = moshi.adapter(resultType)
        return jsonAdapter.fromJson(json) ?: listOf()
    }
}
