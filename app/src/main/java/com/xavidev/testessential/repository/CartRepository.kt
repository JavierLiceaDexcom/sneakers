package com.xavidev.testessential.repository

import com.xavidev.testessential.data.Response
import com.xavidev.testessential.data.dao.Action
import com.xavidev.testessential.data.entity.Cart
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun insertCartItem(cart: Cart): Flow<Response<Boolean>>
    suspend fun deleteCartItem(cart: Cart): Flow<Response<Boolean>>
    suspend fun getCartItems(): Flow<Response<List<Cart>>>
    suspend fun updateCartItemQuantity(cartId: String, action: Action): Flow<Response<Unit>>
}