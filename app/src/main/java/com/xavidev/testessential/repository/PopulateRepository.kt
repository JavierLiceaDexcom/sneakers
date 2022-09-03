package com.xavidev.testessential.repository

import com.xavidev.testessential.data.Response
import com.xavidev.testessential.data.entity.*
import kotlinx.coroutines.flow.Flow

interface PopulateRepository {
    suspend fun insertBrands(brands: List<Brand>): Flow<Response<Unit>>
    suspend fun insertImages(images: List<Images>): Flow<Response<Unit>>
    suspend fun populateSneakersTable(sneakers: List<Sneaker>): Flow<Response<Unit>>
    suspend fun populateCurrenciesTable(currencies: List<Currency>): Flow<Response<Unit>>
    suspend fun populateTypesTable(types: List<Type>): Flow<Response<Unit>>
}