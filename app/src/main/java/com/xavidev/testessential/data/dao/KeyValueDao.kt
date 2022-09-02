package com.xavidev.testessential.data.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xavidev.testessential.data.entity.KeyValue

interface KeyValueDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKeyValue(keyValue: KeyValue): Int

    @Query("SELECT * FROM key_value WHERE key =: key")
    suspend fun getKeyValue(key: String): KeyValue

    companion object {
        const val INTRO_PASSED = "intro_passed"
    }
}