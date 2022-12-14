package com.xavidev.testessential.data.repository

import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.source.local.entity.Card
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    suspend fun insertCard(card: Card): Flow<Result<Boolean>>

    suspend fun deleteCard(card: Card): Flow<Result<Boolean>>

    suspend fun deleteCardById(cardId: String): Flow<Result<Unit>>

    suspend fun getAllCards(): Flow<Result<List<Card>>>

    suspend fun getCardById(cardId: String): Flow<Result<Card>>

    suspend fun getDefaultCard(): Flow<Result<Card?>>

    suspend fun updateDefaultCard(cardId: String): Flow<Result<Unit>>
}