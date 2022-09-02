package com.xavidev.testessential.repository

import com.xavidev.testessential.data.Response
import com.xavidev.testessential.data.entity.KeyValue
import kotlinx.coroutines.flow.Flow

interface KeyValueRepository {
    suspend fun insertKeyValue(keyValue: KeyValue): Flow<Response<Boolean>>
    suspend fun getKeyValue(key: String): Flow<Response<KeyValue>>
}