package com.xavidev.testessential.data.resources

import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.source.local.dao.UserDao
import com.xavidev.testessential.data.source.local.entity.User
import com.xavidev.testessential.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class UserResources internal constructor(private val userDao: UserDao) : UserRepository {
    override suspend fun insertUser(user: User): Flow<Result<Boolean>> = flow {
        emit(Result.Loading)
        try {
            val response = userDao.insertUser(user)
            emit(Result.Success(response == 1L))
        } catch (ex: IOException) {
            emit(Result.Error(ex))
        }
    }

    override suspend fun getUser(id: String): Flow<Result<User>> = flow {
        emit(Result.Loading)
        try {
            val response = userDao.getUser(id)
            emit(Result.Success(response))
        } catch (ex: IOException) {
            emit(Result.Error(ex))
        }
    }

    override suspend fun updateUser(user: User): Flow<Result<Boolean>> = flow {
        emit(Result.Loading)
        try {
            val response = userDao.updateUser(user)
            emit(Result.Success(response == 1))
        } catch (ex: IOException) {
            emit(Result.Error(ex))
        }
    }
}