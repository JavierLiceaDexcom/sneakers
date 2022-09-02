package com.xavidev.testessential.repository

import com.xavidev.testessential.data.Response
import com.xavidev.testessential.data.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insertUser(user: User): Flow<Response<Boolean>>
    suspend fun getUser(id: String): Flow<Response<User>>
    suspend fun updateUser(user: User): Flow<Response<Boolean>>
}