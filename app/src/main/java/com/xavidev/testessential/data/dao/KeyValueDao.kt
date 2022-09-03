package com.xavidev.testessential.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xavidev.testessential.data.entity.KeyValue

@Dao
interface KeyValueDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKeyValue(keyValue: KeyValue): Long

    @Query("SELECT * FROM key_value WHERE key =:key")
    suspend fun getKeyValue(key: String): KeyValue

    companion object {
        const val INTRO_PASSED = "intro_passed"
        const val TRUE_VALUE = "true"
        const val FALSE_VALUE = "false"
    }
}