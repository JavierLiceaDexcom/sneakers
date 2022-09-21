package com.xavidev.testessential.data.resources

import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.repository.BrandsRepository
import com.xavidev.testessential.data.source.local.dao.BrandsDao
import com.xavidev.testessential.data.source.local.entity.Brand
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BrandsResources internal constructor(private val brandsDao: BrandsDao) : BrandsRepository {
    override suspend fun getBrands(): Flow<Result<List<Brand>>> = flow {
        emit(Result.Loading)
        try {
            emit(Result.Success(brandsDao.getAllBrands()))
        } catch (ex: Exception) {
            emit(Result.Error(ex))
        }
    }
}