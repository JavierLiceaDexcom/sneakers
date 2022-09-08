package com.xavidev.testessential.repository

import com.xavidev.testessential.data.Response
import com.xavidev.testessential.data.entity.Purchase
import kotlinx.coroutines.flow.Flow

interface PurchaseRepository {
    suspend fun insertPurchase(purchase: Purchase): Flow<Response<Boolean>>
    suspend fun insertPurchases(purchases: List<Purchase>): Flow<Response<Unit>>
    suspend fun getAllPurchases(): Flow<Response<List<Purchase>>>
    suspend fun getNonDeletedPurchases(): Flow<Response<List<Purchase>>>
    suspend fun getDeletedPurchases(): Flow<Response<List<Purchase>>>
}