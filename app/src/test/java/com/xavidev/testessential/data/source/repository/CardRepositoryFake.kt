package com.xavidev.testessential.data.source.repository

import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.Result.*
import com.xavidev.testessential.data.repository.CardRepository
import com.xavidev.testessential.data.source.local.entity.Card
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CardRepositoryFake : CardRepository {

    val cardList = mutableListOf<Card>()

    override suspend fun insertCard(card: Card): Flow<Result<Boolean>> = flow {
        emit(Loading)
        try {
            cardList.add(card)
            emit(Success(true))
        } catch (ex: Exception) {
            emit(Error(ex))
        }
    }

    override suspend fun deleteCard(card: Card): Flow<Result<Boolean>> = flow {
        emit(Loading)
        try {
            cardList.remove(card)
            emit(Success(true))
        } catch (ex: Exception) {
            emit(Error(ex))
        }
    }

    override suspend fun deleteCardById(cardId: String): Flow<Result<Unit>> = flow {
        emit(Loading)
        try {
            val card = cardList.first { it.id == cardId }
            cardList.remove(card)
            emit(Success(Unit))
        } catch (ex: Exception) {
            emit(Error(ex))
        }
    }

    override suspend fun getAllCards(): Flow<Result<List<Card>>> = flow {
        emit(Loading)
        try {
            emit(Success(cardList))
        } catch (ex: Exception) {
            emit(Error(ex))
        }
    }

    override suspend fun getCardById(cardId: String): Flow<Result<Card>> = flow {
        emit(Loading)
        try {
            val card = cardList.first { it.id == cardId }
            emit(Success(card))
        } catch (ex: Exception) {
            emit(Error(ex))
        }
    }

    override suspend fun updateDefaultCard(cardId: String): Flow<Result<Unit>> = flow {
        emit(Loading)
        try {
            val card = cardList.find { it.id == cardId }
            val newCard = card?.copy(isDefault = true)
            cardList.remove(card)
            newCard?.let { cardList.add(it) }
            emit(Success(Unit))
        } catch (ex: Exception) {
            emit(Error(ex))
        }
    }
}