package com.xavidev.testessential.data.repository

import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.source.local.entity.Purchase
import kotlinx.coroutines.flow.Flow

interface PurchaseRepository {
    suspend fun insertPurchase(purchase: Purchase): Flow<Result<Boolean>>
    suspend fun insertPurchases(purchases: List<Purchase>): Flow<Result<Unit>>
    suspend fun getAllPurchases(): Flow<Result<List<Purchase>>>
    suspend fun getNonDeletedPurchases(): Flow<Result<List<Purchase>>>
    suspend fun getDeletedPurchases(): Flow<Result<List<Purchase>>>
}