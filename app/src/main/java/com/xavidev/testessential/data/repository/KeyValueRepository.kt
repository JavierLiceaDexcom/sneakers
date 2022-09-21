package com.xavidev.testessential.data.repository

import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.source.local.entity.KeyValue
import kotlinx.coroutines.flow.Flow

interface KeyValueRepository {
    suspend fun insertKeyValue(keyValue: KeyValue): Flow<Result<Boolean>>

    suspend fun getKeyValue(key: String): Flow<Result<KeyValue>>
}