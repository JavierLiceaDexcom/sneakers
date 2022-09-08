package com.xavidev.testessential.resources

import com.xavidev.testessential.data.Response
import com.xavidev.testessential.data.dao.PurchasesDao
import com.xavidev.testessential.data.entity.Purchase
import com.xavidev.testessential.repository.PurchaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException


class PurchaseResources(private val purchasesDao: PurchasesDao) : PurchaseRepository {
    override suspend fun insertPurchase(purchase: Purchase): Flow<Response<Boolean>> = flow {
        emit(Response.Loading())
        try {
            val response = purchasesDao.insertPurchase(purchase)
            emit(Response.Success(response == INSERTED.toLong()))
        } catch (ex: IOException) {
            emit(Response.Error(ex.localizedMessage))
        }
    }

    override suspend fun insertPurchases(purchases: List<Purchase>): Flow<Response<Unit>> =
        flow {
            emit(Response.Loading())
            try {
                val response = purchasesDao.insertPurchases(purchases)
                emit(Response.Success(response))
            } catch (ex: IOException) {
                emit(Response.Error(ex.localizedMessage))
            }
        }

    override suspend fun getAllPurchases(): Flow<Response<List<Purchase>>> = flow {
        emit(Response.Loading())
        try {
            val response = purchasesDao.getAllPurchases()
            emit(Response.Success(response))
        } catch (ex: IOException) {
            emit(Response.Error(ex.localizedMessage))
        }
    }

    override suspend fun getNonDeletedPurchases(): Flow<Response<List<Purchase>>> = flow {
        emit(Response.Loading())
        try {
            val response = purchasesDao.getNonDeletedPurchases()
            emit(Response.Success(response))
        } catch (ex: IOException) {
            emit(Response.Error(ex.localizedMessage))
        }
    }

    override suspend fun getDeletedPurchases(): Flow<Response<List<Purchase>>> = flow {
        emit(Response.Loading())
        try {
            val response = purchasesDao.getDeletedPurchases()
            emit(Response.Success(response))
        } catch (ex: IOException) {
            emit(Response.Error(ex.localizedMessage))
        }
    }
}