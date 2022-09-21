package com.xavidev.testessential.data.resources

import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.source.local.dao.BrandsDao
import com.xavidev.testessential.data.source.local.dao.ImagesDao
import com.xavidev.testessential.data.source.local.dao.SneakersDao
import com.xavidev.testessential.data.source.local.entity.*
import com.xavidev.testessential.data.repository.PopulateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class PopulateResources(
    private val brandsDao: BrandsDao,
    private val sneakersDao: SneakersDao,
    private val imagesDao: ImagesDao,
) : PopulateRepository {
    override suspend fun getSneakersCount(): Flow<Result<Int>> = flow {
        emit(Result.Loading)
        try {
            val result = sneakersDao.getSneakersCount()
            emit(Result.Success(result))
        } catch (ex: Exception) {
            emit(Result.Error(ex))
        }
    }

    override suspend fun insertBrands(brands: List<Brand>): Flow<Result<Unit>> = flow {
        emit(Result.Loading)
        try {
            val result = brandsDao.populateBrandsTable(brands)
            emit(Result.Success(result))
        } catch (ex: Exception) {
            emit(Result.Error(ex))
        }
    }

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

    override suspend fun insertImages(images: List<Images>): Flow<Result<Unit>> = flow {
        emit(Result.Loading)
        try {
            val response = imagesDao.populateImages(images)
            emit(Result.Success(response))
        } catch (ex: IOException) {
            emit(Result.Error(ex))
        }
    }

    override suspend fun populateCurrenciesTable(currencies: List<Currency>): Flow<Result<Unit>> =
        flow {
            emit(Result.Loading)
            try {
                val response = sneakersDao.populateCurrencyTable(currencies)
                emit(Result.Success(response))
            } catch (ex: IOException) {
                emit(Result.Error(ex))
            }
        }

    override suspend fun populateTypesTable(types: List<Type>): Flow<Result<Unit>> =
        flow {
            emit(Result.Loading)
            try {
                val response = sneakersDao.populateTypesTable(types)
                emit(Result.Success(response))
            } catch (ex: IOException) {
                emit(Result.Error(ex))
            }
        }
}