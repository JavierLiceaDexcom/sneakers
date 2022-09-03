package com.xavidev.testessential.repository

import com.xavidev.testessential.data.Response
import com.xavidev.testessential.data.entity.Currency
import com.xavidev.testessential.data.entity.Sneaker
import com.xavidev.testessential.data.entity.Type
import kotlinx.coroutines.flow.Flow

interface SneakersRepository {
    suspend fun populateSneakersTable(sneakers: List<Sneaker>): Flow<Response<Unit>>
    suspend fun getAllSneakers(): Flow<Response<List<Sneaker>>>
    suspend fun getSneakersByBrand(brandId: String): Flow<Response<List<Sneaker>>>
    suspend fun getSneakersByType(typeId: String): Flow<Response<List<Sneaker>>>
    suspend fun getSneaker(sneakerId: String): Flow<Response<Sneaker>>
}