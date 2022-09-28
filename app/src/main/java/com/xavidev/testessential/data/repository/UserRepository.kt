package com.xavidev.testessential.data.repository

import android.content.Context
import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.source.local.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insertUser(user: User): Flow<Result<Boolean>>

    suspend fun getUser(id: String): Flow<Result<User>>

    suspend fun updateUser(user: User): Flow<Result<Boolean>>

    suspend fun signOut(context: Context): Flow<Result<Unit>>
}