package com.xavidev.testessential.resources

import com.xavidev.testessential.data.Response
import com.xavidev.testessential.data.dao.CardDao
import com.xavidev.testessential.data.entity.Card
import com.xavidev.testessential.repository.CardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class CardResources(private val cardDao: CardDao) : CardRepository {
    override suspend fun insertCard(card: Card): Flow<Response<Boolean>> = flow {
        emit(Response.Loading())
        try {
            val response = cardDao.insertCard(card)
            emit(Response.Success(response == 1L))
        } catch (ex: IOException) {
            emit(Response.Error(ex.localizedMessage))
        }
    }

    override suspend fun deleteCard(card: Card): Flow<Response<Boolean>> = flow {
        emit(Response.Loading())
        try {
            val response = cardDao.deleteCard(card)
            emit(Response.Success(response == 1))
        } catch (ex: IOException) {
            emit(Response.Error(ex.localizedMessage))
        }
    }

    override suspend fun getAllCards(): Flow<Response<List<Card>>> = flow {
        emit(Response.Loading())
        try {
            val response = cardDao.getAllCards()
            emit(Response.Success(response))
        } catch (ex: IOException) {
            emit(Response.Error(ex.localizedMessage))
        }
    }

    override suspend fun getCardById(cardId: String): Flow<Response<Card>> = flow {
        emit(Response.Loading())
        try {
            val response = cardDao.getCard(cardId)
            emit(Response.Success(response))
        } catch (ex: IOException) {
            emit(Response.Error(ex.localizedMessage))
        }
    }

    override suspend fun updateDefaultCard(oldCard: Card, newCard: Card): Flow<Response<Unit>> =
        flow {
            emit(Response.Loading())
            try {
                val response = cardDao.updateDefaultCard(oldCard, newCard)
                emit(Response.Success(response))
            } catch (ex: IOException) {
                emit(Response.Error(ex.localizedMessage))
            }
        }
}