package com.xavidev.testessential.data.resources

import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.source.local.dao.BrandsDao
import com.xavidev.testessential.data.source.local.dao.SneakersDao
import com.xavidev.testessential.data.source.local.entity.Brand
import com.xavidev.testessential.data.source.local.entity.Images
import com.xavidev.testessential.data.source.local.entity.Sneaker
import com.xavidev.testessential.data.source.local.entity.SneakerComplete
import com.xavidev.testessential.data.repository.BrandsRepository
import com.xavidev.testessential.data.repository.SneakersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

const val INSERTED = 1

class SneakersResources internal constructor(private val sneakersDao: SneakersDao) :
    SneakersRepository {
    override suspend fun populateSneakersTable(sneakers: List<Sneaker>): Flow<Result<Unit>> =
        flow {
            emit(Result.Loading)
            try {
                val response = sneakersDao.insertSneakers(sneakers)
                emit(Result.Success(response))
            } catch (ex: IOException) {
                emit(Result.Error(ex))
            }
        }

    override suspend fun getAllCompleteSneakers(): Flow<Result<List<SneakerComplete>>> = flow {
        emit(Result.Loading)
        try {
            val response = sneakersDao.getAllCompleteSneakers()
            emit(Result.Success(response))
        } catch (ex: IOException) {
            emit(Result.Error(ex))
        }
    }

    override suspend fun getSneakersByBrand(brandId: String): Flow<Result<List<SneakerComplete>>> =
        flow {
            emit(Result.Loading)
            try {
                val response = sneakersDao.getSneakersByBrand(brandId)
                emit(Result.Success(response))
            } catch (ex: IOException) {
                emit(Result.Error(ex))
            }
        }

    override suspend fun getSneakersByType(typeId: String): Flow<Result<List<Sneaker>>> = flow {
        emit(Result.Loading)
        try {
            val response = sneakersDao.getSneakersByType(typeId)
            emit(Result.Success(response))
        } catch (ex: IOException) {
            emit(Result.Error(ex))
        }
    }

    override suspend fun getSneaker(sneakerId: String): Flow<Result<SneakerComplete>> = flow {
        emit(Result.Loading)
        try {
            val response = sneakersDao.getSneaker(sneakerId)
            emit(Result.Success(response))
        } catch (ex: IOException) {
            emit(Result.Error(ex))
        }
    }

    override suspend fun setFavorite(sneakerId: String, favorite: Boolean) = flow {
        emit(Result.Loading)
        try {
            val response = sneakersDao.updateFavoriteValue(sneakerId, favorite)
            emit(Result.Success(response == INSERTED))
        } catch (ex: IOException) {
            emit(Result.Error(ex))
        }
    }

    override suspend fun updateSneakerInCart(
        sneakerId: String,
        value: Boolean
    ): Flow<Result<Boolean>> = flow {
        emit(Result.Loading)
        try {
            val response = sneakersDao.updateSneakerInCart(sneakerId, value)
            emit(Result.Success(response == INSERTED))
        } catch (ex: IOException) {
            emit(Result.Error(ex))
        }
    }

    override suspend fun getSneakerImages(imagesId: String): Flow<Result<Images>> = flow {
        emit(Result.Loading)
        try {
            val response = sneakersDao.getSneakerImages(imagesId)
            emit(Result.Success(response))
        } catch (ex: IOException) {
            emit(Result.Error(ex))
        }
    }
}