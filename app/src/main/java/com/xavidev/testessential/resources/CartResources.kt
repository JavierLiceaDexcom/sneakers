package com.xavidev.testessential.resources

import com.xavidev.testessential.data.Response
import com.xavidev.testessential.data.dao.Action
import com.xavidev.testessential.data.dao.CartDao
import com.xavidev.testessential.data.entity.Cart
import com.xavidev.testessential.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class CartResources(private val cartDao: CartDao) : CartRepository {
    override suspend fun insertCartItem(cart: Cart): Flow<Response<Boolean>> = flow {
        emit(Response.Loading())
        try {
            val response = cartDao.insertItem(cart)
            emit(Response.Success(response == 1))
        } catch (ex: IOException) {
            emit(Response.Error(ex.localizedMessage))
        }
    }

    override suspend fun deleteCartItem(cart: Cart): Flow<Response<Boolean>> = flow {
        emit(Response.Loading())
        try {
            val response = cartDao.deleteItem(cart)
            emit(Response.Success(response == 1))
        } catch (ex: IOException) {
            emit(Response.Error(ex.localizedMessage))
        }
    }

    override suspend fun getCartItems(): Flow<Response<List<Cart>>> = flow {
        emit(Response.Loading())
        try {
            val response = cartDao.getAllItems()
            emit(Response.Success(response))
        } catch (ex: IOException) {
            emit(Response.Error(ex.localizedMessage))
        }
    }

    override suspend fun updateCartItemQuantity(
        cartId: String,
        action: Action
    ): Flow<Response<Unit>> = flow {
        emit(Response.Loading())
        try {
            val response = cartDao.updateCartQuantity(cartId, action)
            emit(Response.Success(response))
        } catch (ex: IOException) {
            emit(Response.Error(ex.localizedMessage))
        }
    }
}