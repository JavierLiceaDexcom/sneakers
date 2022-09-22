package com.xavidev.testessential.data.resources

import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.source.local.dao.KeyValueDao
import com.xavidev.testessential.data.source.local.entity.KeyValue
import com.xavidev.testessential.data.repository.KeyValueRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class KeyValueResource internal constructor(private val keyValueDao: KeyValueDao) :
    KeyValueRepository {
    override suspend fun insertKeyValue(keyValue: KeyValue): Flow<Result<Boolean>> = flow {
        emit(Result.Loading)
        try {
            val response = keyValueDao.insertKeyValue(keyValue)
            emit(Result.Success(response == 1L))
        } catch (ex: IOException) {
            emit(Result.Error(ex))
        }

    }

    override suspend fun getKeyValue(key: String): Flow<Result<KeyValue?>> = flow {
        emit(Result.Loading)
        try {
            val response = keyValueDao.getKeyValue(key)
            emit(Result.Success(response))
        } catch (ex: IOException) {
            emit(Result.Error(ex))
        }
    }
}