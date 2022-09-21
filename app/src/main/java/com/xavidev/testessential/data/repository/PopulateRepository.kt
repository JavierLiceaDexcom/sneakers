package com.xavidev.testessential.data.repository

import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.source.local.entity.*
import kotlinx.coroutines.flow.Flow

interface PopulateRepository {
    suspend fun getSneakersCount(): Flow<Result<Int>>

    suspend fun insertBrands(brands: List<Brand>): Flow<Result<Unit>>

    suspend fun insertImages(images: List<Images>): Flow<Result<Unit>>

    suspend fun populateSneakersTable(sneakers: List<Sneaker>): Flow<Result<Unit>>

    suspend fun populateCurrenciesTable(currencies: List<Currency>): Flow<Result<Unit>>

    suspend fun populateTypesTable(types: List<Type>): Flow<Result<Unit>>
}