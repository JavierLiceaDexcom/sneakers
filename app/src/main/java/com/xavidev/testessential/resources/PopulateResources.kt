package com.xavidev.testessential.resources

import com.xavidev.testessential.data.Response
import com.xavidev.testessential.data.dao.BrandsDao
import com.xavidev.testessential.data.dao.ImagesDao
import com.xavidev.testessential.data.dao.SneakersDao
import com.xavidev.testessential.data.entity.*
import com.xavidev.testessential.repository.PopulateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class PopulateResources(
    private val brandsDao: BrandsDao,
    private val sneakersDao: SneakersDao,
    private val imagesDao: ImagesDao,
) : PopulateRepository {

    override suspend fun insertBrands(brands: List<Brand>): Flow<Response<Unit>> = flow {
        emit(Response.Loading())
        try {
            val result = brandsDao.populateBrandsTable(brands)
            emit(Response.Success(result))
        } catch (ex: Exception) {
            emit(Response.Error(ex.localizedMessage))
        }
    }

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

    override suspend fun insertImages(images: List<Images>): Flow<Response<Unit>> = flow {
        emit(Response.Loading())
        try {
            val response = imagesDao.populateImages(images)
            emit(Response.Success(response))
        } catch (ex: IOException) {
            emit(Response.Error(ex.localizedMessage))
        }
    }

    override suspend fun populateCurrenciesTable(currencies: List<Currency>): Flow<Response<Unit>> =
        flow {
            emit(Response.Loading())
            try {
                val response = sneakersDao.populateCurrencyTable(currencies)
                emit(Response.Success(response))
            } catch (ex: IOException) {
                emit(Response.Error(ex.localizedMessage))
            }
        }

    override suspend fun populateTypesTable(types: List<Type>): Flow<Response<Unit>> =
        flow {
            emit(Response.Loading())
            try {
                val response = sneakersDao.populateTypesTable(types)
                emit(Response.Success(response))
            } catch (ex: IOException) {
                emit(Response.Error(ex.localizedMessage))
            }
        }
}