package com.xavidev.testessential.data.dao

import androidx.room.*
import com.xavidev.testessential.data.entity.Cart

interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(cart: Cart): Int

    @Delete
    suspend fun deleteItem(cart: Cart): Int

    @Query("SELECT * FROM cart")
    suspend fun getAllItems(): List<Cart>

    @Query("UPDATE cart SET quantity =:quantity")
    suspend fun setQuantity(quantity: Int): Int

    @Query("SELECT quantity WHERE id =:id")
    suspend fun getCartItemQuantity(id: String): Int

    @Transaction
    suspend fun updateCartQuantity(cartId: String, action: Action) {
        val quantity = getCartItemQuantity(cartId)
        if (quantity <= 1) return
        val value = when (action) {
            Action.DECREMENT -> -1
            Action.INCREMENT -> 1
        }
        val difference = quantity + value
        setQuantity(difference)
    }
}

enum class Action {
    INCREMENT, DECREMENT
}