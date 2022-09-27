package com.xavidev.testessential.data.resources

import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.source.local.dao.CardDao
import com.xavidev.testessential.data.source.local.entity.Card
import com.xavidev.testessential.data.repository.CardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class CardResources internal constructor(private val cardDao: CardDao) : CardRepository {
    override suspend fun insertCard(card: Card): Flow<Result<Boolean>> = flow {
        emit(Result.Loading)
        try {
            val response = cardDao.insertCard(card)
            if (card.isDefault) cardDao.updateDefaultCard(card.id)
            emit(Result.Success(response == 1L))
        } catch (ex: IOException) {
            emit(Result.Error(ex))
        }
    }

    override suspend fun deleteCard(card: Card): Flow<Result<Boolean>> = flow {
        emit(Result.Loading)
        try {
            val response = cardDao.deleteCard(card)
            emit(Result.Success(response == 1))
        } catch (ex: IOException) {
            emit(Result.Error(ex))
        }
    }

    override suspend fun getAllCards(): Flow<Result<List<Card>>> = flow {
        emit(Result.Loading)
        try {
            val response = cardDao.getAllCards()
            emit(Result.Success(response))
        } catch (ex: IOException) {
            emit(Result.Error(ex))
        }
    }

    override suspend fun getCardById(cardId: String): Flow<Result<Card>> = flow {
        emit(Result.Loading)
        try {
            val response = cardDao.getCard(cardId)
            emit(Result.Success(response))
        } catch (ex: IOException) {
            emit(Result.Error(ex))
        }
    }

    override suspend fun updateDefaultCard( cardId: String): Flow<Result<Unit>> =
        flow {
            emit(Result.Loading)
            try {
                val response = cardDao.updateDefaultCard(cardId)
                emit(Result.Success(response))
            } catch (ex: IOException) {
                emit(Result.Error(ex))
            }
        }
}