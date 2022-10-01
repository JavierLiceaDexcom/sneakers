package com.xavidev.testessential.data.source.repository

import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.Result.*
import com.xavidev.testessential.data.repository.BrandsRepository
import com.xavidev.testessential.data.source.local.entity.Brand
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BrandsRepositoryFake : BrandsRepository {
    val brandsList = mutableListOf<Brand>()

    fun insertBrands(brands: List<Brand>) {
        brandsList.addAll(brands)
    }

    override suspend fun getBrands(): Flow<Result<List<Brand>>> = flow {
        emit(Loading)
        try {
            emit(Success(brandsList))
        } catch (ex: Exception) {
            emit(Error(ex))
        }
    }
}