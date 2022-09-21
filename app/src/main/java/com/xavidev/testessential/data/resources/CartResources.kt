package com.xavidev.testessential.data.resources

import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.source.local.dao.Action
import com.xavidev.testessential.data.source.local.dao.CartDao
import com.xavidev.testessential.data.source.local.entity.Cart
import com.xavidev.testessential.data.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class CartResources internal constructor(private val cartDao: CartDao) : CartRepository {
    override suspend fun insertCartItem(cart: Cart): Flow<Result<Boolean>> = flow {
        emit(Result.Loading)
        try {
            val response = cartDao.insertItem(cart)
            emit(Result.Success(response == 1L))
        } catch (ex: IOException) {
            emit(Result.Error(ex))
        }
    }

    override suspend fun deleteCartItem(cart: Cart): Flow<Result<Boolean>> = flow {
        emit(Result.Loading)
        try {
            val response = cartDao.deleteItem(cart)
            emit(Result.Success(response == 1))
        } catch (ex: IOException) {
            emit(Result.Error(ex))
        }
    }

    override suspend fun deleteSneakerFromCart(sneakerId: String): Flow<Result<Boolean>> = flow {
        emit(Result.Loading)
        try {
            val response = cartDao.removeSneakerFromCart(sneakerId)
            emit(Result.Success(response == 1))
        } catch (ex: IOException) {
            emit(Result.Error(ex))
        }
    }

    override suspend fun getCartItems(): Flow<Result<List<Cart>>> = flow {
        emit(Result.Loading)
        try {
            val response = cartDao.getAllItems()
            emit(Result.Success(response))
        } catch (ex: IOException) {
            emit(Result.Error(ex))
        }
    }

    override suspend fun updateCartItemQuantity(
        cartId: String,
        action: Action
    ): Flow<Result<Unit>> = flow {
        emit(Result.Loading)
        try {
            val response = cartDao.updateCartQuantity(cartId, action)
            emit(Result.Success(response))
        } catch (ex: IOException) {
            emit(Result.Error(ex))
        }
    }
}