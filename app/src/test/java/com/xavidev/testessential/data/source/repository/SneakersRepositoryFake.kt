package com.xavidev.testessential.data.source.repository

import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.Result.*
import com.xavidev.testessential.data.repository.SneakersRepository
import com.xavidev.testessential.data.source.local.entity.Images
import com.xavidev.testessential.data.source.local.entity.Sneaker
import com.xavidev.testessential.data.source.local.entity.SneakerComplete
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SneakersRepositoryFake : SneakersRepository {

    private val sneakersList = mutableListOf<Sneaker>()
    private val sneakersListComplete = mutableListOf<SneakerComplete>()

    override suspend fun populateSneakersTable(sneakers: List<Sneaker>): Flow<Result<Unit>> = flow {
        emit(Loading)
        try {
            sneakersList.addAll(sneakers)
            emit(Success(Unit))
        } catch (ex: Exception) {
            emit(Error(ex))
        }
    }

    override suspend fun getAllCompleteSneakers(): Flow<Result<List<SneakerComplete>>> = flow {
        emit(Loading)
        try {
            emit(Success(sneakersListComplete))
        } catch (ex: Exception) {
            emit(Error(ex))
        }
    }

    override suspend fun getSneakersByBrand(brandId: String): Flow<Result<List<SneakerComplete>>> =
        flow {
            emit(Loading)
            try {
                val filteredList = sneakersList.filter { it.brandId == brandId }
                emit(Success(sneakersListComplete))
            } catch (ex: Exception) {
                emit(Error(ex))
            }
        }

    override suspend fun getSneakersByType(typeId: String): Flow<Result<List<Sneaker>>> = flow {
        emit(Loading)
        try {
            emit(Success(sneakersList))
        } catch (ex: Exception) {
            emit(Error(ex))
        }
    }

    override suspend fun getSneaker(sneakerId: String): Flow<Result<SneakerComplete>> {
        TODO("Not yet implemented")
    }

    override suspend fun setFavorite(sneakerId: String, favorite: Boolean): Flow<Result<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateSneakerInCart(
        sneakerId: String,
        value: Boolean
    ): Flow<Result<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun getSneakerImages(imagesId: String): Flow<Result<Images>> {
        TODO("Not yet implemented")
    }

    override fun searchSneakerByName(query: String): Flow<Result<List<SneakerComplete>>> = flow {
        emit(Loading)
        try {
            val filteredSneakers =
                sneakersListComplete.filter { it.model == query || it.brand == query }
            emit(Success(filteredSneakers))
        } catch (ex: Exception) {
            emit(Error(ex))
        }
    }
}