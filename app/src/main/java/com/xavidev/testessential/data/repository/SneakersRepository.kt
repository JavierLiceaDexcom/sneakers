package com.xavidev.testessential.data.repository

import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.source.local.entity.Images
import com.xavidev.testessential.data.source.local.entity.Sneaker
import com.xavidev.testessential.data.source.local.entity.SneakerComplete
import kotlinx.coroutines.flow.Flow

interface SneakersRepository {
    suspend fun populateSneakersTable(sneakers: List<Sneaker>): Flow<Result<Unit>>
    suspend fun getAllCompleteSneakers(): Flow<Result<List<SneakerComplete>>>
    suspend fun getSneakersByBrand(brandId: String): Flow<Result<List<SneakerComplete>>>
    suspend fun getSneakersByType(typeId: String): Flow<Result<List<Sneaker>>>
    suspend fun getSneaker(sneakerId: String): Flow<Result<SneakerComplete>>
    suspend fun setFavorite(sneakerId: String, favorite: Boolean): Flow<Result<Boolean>>
    suspend fun updateSneakerInCart(sneakerId: String, value: Boolean): Flow<Result<Boolean>>
    suspend fun getSneakerImages(imagesId: String): Flow<Result<Images>>
}