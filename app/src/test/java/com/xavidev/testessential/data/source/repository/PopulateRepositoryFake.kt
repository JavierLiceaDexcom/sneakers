package com.xavidev.testessential.data.source.repository

import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.Result.Loading
import com.xavidev.testessential.data.Result.Success
import com.xavidev.testessential.data.Result.Error
import com.xavidev.testessential.data.repository.PopulateRepository
import com.xavidev.testessential.data.source.local.entity.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PopulateRepositoryFake : PopulateRepository {

    private val brandsList = mutableListOf<Brand>()
    private val imagesList = mutableListOf<Images>()
    private val sneakersList = mutableListOf<Sneaker>()
    private val currenciesList = mutableListOf<Currency>()
    private val typesList = mutableListOf<Type>()

    override suspend fun getSneakersCount(): Flow<Result<Int>> = flow {
        emit(Loading)
        try {
            emit(Success(sneakersList.size))
        } catch (ex: Exception) {
            emit(Error(ex))
        }
    }

    override suspend fun insertBrands(brands: List<Brand>): Flow<Result<Unit>> = flow {
        emit(Loading)
        try {
            brandsList.addAll(brands)
            emit(Success(Unit))
        } catch (ex: Exception) {
            emit(Error(ex))
        }
    }

    override suspend fun insertImages(images: List<Images>): Flow<Result<Unit>> = flow {
        emit(Loading)
        try {
            imagesList.addAll(images)
            emit(Success(Unit))
        } catch (ex: Exception) {
            emit(Error(ex))
        }
    }

    override suspend fun populateSneakersTable(sneakers: List<Sneaker>): Flow<Result<Unit>> = flow {
        emit(Loading)
        try {
            sneakersList.addAll(sneakers)
            emit(Success(Unit))
        } catch (ex: Exception) {
            emit(Error(ex))
        }
    }

    override suspend fun populateCurrenciesTable(currencies: List<Currency>): Flow<Result<Unit>> =
        flow {
            emit(Loading)
            try {
                currenciesList.addAll(currencies)
                emit(Success(Unit))
            } catch (ex: Exception) {
                emit(Error(ex))
            }
        }

    override suspend fun populateTypesTable(types: List<Type>): Flow<Result<Unit>> = flow {
        emit(Loading)
        try {
            typesList.addAll(types)
            emit(Success(Unit))
        } catch (ex: Exception) {
            emit(Error(ex))
        }
    }
}