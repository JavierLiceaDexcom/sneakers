package com.xavidev.testessential.repository

import com.xavidev.testessential.data.Response
import com.xavidev.testessential.data.entity.Brand
import kotlinx.coroutines.flow.Flow

interface BrandsRepository {
    suspend fun insertBrands(brands: List<Brand>): Flow<Response<Unit>>
    suspend fun getBrands(): Flow<Response<List<Brand>>>
}