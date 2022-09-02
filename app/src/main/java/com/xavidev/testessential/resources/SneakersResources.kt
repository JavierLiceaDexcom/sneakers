package com.xavidev.testessential.resources

import com.xavidev.testessential.data.Response
import com.xavidev.testessential.data.dao.SneakersDao
import com.xavidev.testessential.data.entity.Sneaker
import com.xavidev.testessential.repository.SneakersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class SneakersResources(private val sneakersDao: SneakersDao) : SneakersRepository {
    override suspend fun populateSneakersTable(sneakers: List<Sneaker>): Flow<Response<Unit>> =
        flow {
            emit(Response.Loading())
            try {
                val response = sneakersDao.insertSneakers(sneakers)
                emit(Response.Success(response))
            } catch (ex: IOException) {
                emit(Response.Error(ex.localizedMessage))
            }
        }

    override suspend fun getAllSneakers(): Flow<Response<List<Sneaker>>> = flow {
        emit(Response.Loading())
        try {
            val response = sneakersDao.getAllSneakers()
            emit(Response.Success(response))
        } catch (ex: IOException) {
            emit(Response.Error(ex.localizedMessage))
        }
    }

    override suspend fun getSneakersByBrand(brandId: String): Flow<Response<List<Sneaker>>> = flow {
        emit(Response.Loading())
        try {
            val response = sneakersDao.getSneakersByBrand(brandId)
            emit(Response.Success(response))
        } catch (ex: IOException) {
            emit(Response.Error(ex.localizedMessage))
        }
    }

    override suspend fun getSneakersByType(typeId: String): Flow<Response<List<Sneaker>>> = flow {
        emit(Response.Loading())
        try {
            val response = sneakersDao.getSneakersByType(typeId)
            emit(Response.Success(response))
        } catch (ex: IOException) {
            emit(Response.Error(ex.localizedMessage))
        }
    }

    override suspend fun getSneaker(sneakerId: String): Flow<Response<Sneaker>> = flow {
        emit(Response.Loading())
        try {
            val response = sneakersDao.getSneaker(sneakerId)
            emit(Response.Success(response))
        } catch (ex: IOException) {
            emit(Response.Error(ex.localizedMessage))
        }
    }
}