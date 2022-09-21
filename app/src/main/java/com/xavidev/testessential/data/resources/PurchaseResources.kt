package com.xavidev.testessential.data.resources

import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.source.local.dao.PurchasesDao
import com.xavidev.testessential.data.source.local.entity.Purchase
import com.xavidev.testessential.data.repository.PurchaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException


class PurchaseResources(private val purchasesDao: PurchasesDao) : PurchaseRepository {
    override suspend fun insertPurchase(purchase: Purchase): Flow<Result<Boolean>> = flow {
        emit(Result.Loading)
        try {
            val response = purchasesDao.insertPurchase(purchase)
            emit(Result.Success(response == INSERTED.toLong()))
        } catch (ex: IOException) {
            emit(Result.Error(ex))
        }
    }

    override suspend fun insertPurchases(purchases: List<Purchase>): Flow<Result<Unit>> =
        flow {
            emit(Result.Loading)
            try {
                val response = purchasesDao.insertPurchases(purchases)
                emit(Result.Success(response))
            } catch (ex: IOException) {
                emit(Result.Error(ex))
            }
        }

    override suspend fun getAllPurchases(): Flow<Result<List<Purchase>>> = flow {
        emit(Result.Loading)
        try {
            val response = purchasesDao.getAllPurchases()
            emit(Result.Success(response))
        } catch (ex: IOException) {
            emit(Result.Error(ex))
        }
    }

    override suspend fun getNonDeletedPurchases(): Flow<Result<List<Purchase>>> = flow {
        emit(Result.Loading)
        try {
            val response = purchasesDao.getNonDeletedPurchases()
            emit(Result.Success(response))
        } catch (ex: IOException) {
            emit(Result.Error(ex))
        }
    }

    override suspend fun getDeletedPurchases(): Flow<Result<List<Purchase>>> = flow {
        emit(Result.Loading)
        try {
            val response = purchasesDao.getDeletedPurchases()
            emit(Result.Success(response))
        } catch (ex: IOException) {
            emit(Result.Error(ex))
        }
    }
}