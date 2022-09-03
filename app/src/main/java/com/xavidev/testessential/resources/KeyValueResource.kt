package com.xavidev.testessential.resources

import com.xavidev.testessential.data.Response
import com.xavidev.testessential.data.dao.KeyValueDao
import com.xavidev.testessential.data.entity.KeyValue
import com.xavidev.testessential.repository.KeyValueRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class KeyValueResource(private val keyValueDao: KeyValueDao) : KeyValueRepository {
    override suspend fun insertKeyValue(keyValue: KeyValue): Flow<Response<Boolean>> = flow {
        emit(Response.Loading())
        try {
            val response = keyValueDao.insertKeyValue(keyValue)
            emit(Response.Success(response == 1L))
        } catch (ex: IOException) {
            emit(Response.Error(ex.localizedMessage))
        }

    }

    override suspend fun getKeyValue(key: String): Flow<Response<KeyValue>> = flow {
        emit(Response.Loading())
        try {
            val response = keyValueDao.getKeyValue(key)
            emit(Response.Success(response))
        } catch (ex: IOException) {
            emit(Response.Error(ex.localizedMessage))
        }
    }
}