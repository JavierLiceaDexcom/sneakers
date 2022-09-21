package com.xavidev.testessential.data.repository

import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.source.local.entity.Brand
import kotlinx.coroutines.flow.Flow

interface BrandsRepository {
    suspend fun getBrands(): Flow<Result<List<Brand>>>
}