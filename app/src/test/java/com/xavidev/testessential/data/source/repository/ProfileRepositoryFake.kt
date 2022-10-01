package com.xavidev.testessential.data.source.repository

import android.content.Context
import com.xavidev.testessential.SneakersApplication
import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.Result.Loading
import com.xavidev.testessential.data.Result.Success
import com.xavidev.testessential.data.Result.Error
import com.xavidev.testessential.data.repository.UserRepository
import com.xavidev.testessential.data.source.local.entity.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProfileRepositoryFake : UserRepository {
    private var currentUser: User? = null

    override suspend fun insertUser(user: User): Flow<Result<Boolean>> = flow {
        emit(Loading)
        try {
            currentUser = user
            emit(Success(true))
        } catch (ex: Exception) {
            emit(Error(ex))
        }
    }

    override suspend fun getUser(id: String): Flow<Result<User>> = flow {
        emit(Loading)
        try {
            currentUser?.let { emit(Success(it)) }
        } catch (ex: Exception) {
            emit(Error(ex))
        }
    }

    override suspend fun updateUser(user: User): Flow<Result<Boolean>> = flow {
        emit(Loading)
        try {
            currentUser = user
            emit(Success(true))
        } catch (ex: Exception) {
            emit(Error(ex))
        }
    }

    override suspend fun signOut(context: Context): Flow<Result<Unit>> = flow {
        emit(Loading)
        try {
            currentUser = null
            val database = (context.applicationContext as SneakersApplication).database
            database?.clearAllTables()
            emit(Success(Unit))
        } catch (ex: Exception) {
            emit(Error(ex))
        }
    }
}