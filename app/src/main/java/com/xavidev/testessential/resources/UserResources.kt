package com.xavidev.testessential.resources

import com.xavidev.testessential.data.Response
import com.xavidev.testessential.data.dao.UserDao
import com.xavidev.testessential.data.entity.User
import com.xavidev.testessential.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class UserResources(private val userDao: UserDao) : UserRepository {
    override suspend fun insertUser(user: User): Flow<Response<Boolean>> = flow {
        emit(Response.Loading())
        try {
            val response = userDao.insertUser(user)
            emit(Response.Success(response == 1))
        } catch (ex: IOException) {
            emit(Response.Error(ex.localizedMessage))
        }
    }

    override suspend fun getUser(id: String): Flow<Response<User>> = flow {
        emit(Response.Loading())
        try {
            val response = userDao.getUser(id)
            emit(Response.Success(response))
        } catch (ex: IOException) {
            emit(Response.Error(ex.localizedMessage))
        }
    }

    override suspend fun updateUser(user: User): Flow<Response<Boolean>> = flow {
        emit(Response.Loading())
        try {
            val response = userDao.updateUser(user)
            emit(Response.Success(response == 1))
        } catch (ex: IOException) {
            emit(Response.Error(ex.localizedMessage))
        }
    }
}