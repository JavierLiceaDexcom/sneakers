package com.xavidev.testessential.resources

import com.xavidev.testessential.data.Response
import com.xavidev.testessential.data.dao.BrandsDao
import com.xavidev.testessential.data.dao.SneakersDao
import com.xavidev.testessential.data.entity.Brand
import com.xavidev.testessential.data.entity.Sneaker
import com.xavidev.testessential.data.entity.SneakerComplete
import com.xavidev.testessential.repository.BrandsRepository
import com.xavidev.testessential.repository.SneakersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

const val INSERTED = 1

class SneakersResources(private val sneakersDao: SneakersDao, private val brandsDao: BrandsDao) :
    SneakersRepository, BrandsRepository {
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

    override suspend fun getAllCompleteSneakers(): Flow<Response<List<SneakerComplete>>> = flow {
        emit(Response.Loading())
        try {
            val response = sneakersDao.getAllCompleteSneakers()
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

    override suspend fun setFavorite(sneakerId: String, favorite: Boolean) = flow {
        emit(Response.Loading())
        try {
            val response = sneakersDao.updateFavoriteValue(sneakerId, favorite)
            emit(Response.Success(response == INSERTED))
        } catch (ex: IOException) {
            emit(Response.Error(ex.localizedMessage))
        }
    }

    override suspend fun insertBrands(brands: List<Brand>): Flow<Response<Unit>> = flow {
        emit(Response.Loading())
        try {
            val result = brandsDao.populateBrandsTable(brands)
            emit(Response.Success(result))
        } catch (ex: Exception) {
            emit(Response.Error(ex.localizedMessage))
        }
    }

    override suspend fun getBrands(): Flow<Response<List<Brand>>> = flow {
        emit(Response.Loading())
        try {
            val result = brandsDao.getAllBrands()
            emit(Response.Success(result))
        } catch (ex: Exception) {
            emit(Response.Error(ex.localizedMessage))
        }
    }
}