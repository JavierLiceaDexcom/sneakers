package com.xavidev.testessential.data.repository

import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.source.local.dao.Action
import com.xavidev.testessential.data.source.local.entity.Cart
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun insertCartItem(cart: Cart): Flow<Result<Boolean>>

    suspend fun deleteCartItem(cart: Cart): Flow<Result<Boolean>>

    suspend fun deleteSneakerFromCart(sneakerId: String): Flow<Result<Boolean>>

    suspend fun getCartItems(): Flow<Result<List<Cart>>>

    suspend fun updateCartItemQuantity(cartId: String, action: Action): Flow<Result<Unit>>
}