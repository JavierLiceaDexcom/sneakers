package com.xavidev.testessential.repository

import com.xavidev.testessential.data.Response
import com.xavidev.testessential.data.entity.Card
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    suspend fun insertCard(card: Card): Flow<Response<Boolean>>
    suspend fun deleteCard(card: Card): Flow<Response<Boolean>>
    suspend fun getAllCards(): Flow<Response<List<Card>>>
    suspend fun getCardById(cardId: String): Flow<Response<Card>>
    suspend fun updateDefaultCard(oldCard: Card, newCard: Card): Flow<Response<Unit>>
}