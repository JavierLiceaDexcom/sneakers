package com.xavidev.testessential.ui.cart

import androidx.lifecycle.*
import com.xavidev.testessential.data.State
import com.xavidev.testessential.data.dao.Action
import com.xavidev.testessential.data.db.DatabaseManager
import com.xavidev.testessential.data.entity.Cart
import com.xavidev.testessential.repository.CartRepository
import com.xavidev.testessential.resources.CartResources
import com.xavidev.testessential.utils.NavigationViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class CartViewModel(private val cartRepository: CartRepository) : NavigationViewModel() {

    private val _loadingInsert = MutableLiveData(false)
    val loadingInsert: LiveData<Boolean> get() = _loadingInsert

    private val _loadingDelete = MutableLiveData(false)
    val loadingDelete: LiveData<Boolean> get() = _loadingDelete

    private val _loadingCartItems = MutableLiveData(false)
    val loadingCartItems: LiveData<Boolean> get() = _loadingCartItems

    private val _loadingCartItemUpdate = MutableLiveData(false)
    val loadingCartItemUpdate: LiveData<Boolean> get() = _loadingCartItemUpdate

    private val _cartItems = MutableLiveData<List<Cart>>()
    val cartItems: LiveData<List<Cart>> get() = _cartItems

    fun insertCartItem(cart: Cart) = viewModelScope.launch {
        cartRepository.insertCartItem(cart).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response.status!!) {
                    State.LOADING -> _loadingInsert.postValue(true)
                    State.SUCCESS -> _loadingInsert.postValue(false)
                    State.ERROR -> _loadingInsert.postValue(false)
                }
            }
    }

    fun deleteCardItem(cart: Cart) = viewModelScope.launch {
        cartRepository.deleteCartItem(cart).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response.status!!) {
                    State.LOADING -> _loadingDelete.postValue(true)
                    State.SUCCESS -> _loadingDelete.postValue(false)
                    State.ERROR -> _loadingDelete.postValue(false)
                }
            }
    }

    fun getCartItems() = viewModelScope.launch {
        cartRepository.getCartItems().flowOn(Dispatchers.IO)
            .collect { response ->
                when (response.status!!) {
                    State.LOADING -> _loadingCartItems.postValue(true)
                    State.SUCCESS -> {
                        _loadingCartItems.postValue(false)
                        _cartItems.postValue(response.data ?: listOf())
                    }
                    State.ERROR -> _loadingCartItems.postValue(false)
                }
            }
    }

    fun updateCartItemQuantity(cartId: String, action: Action) = viewModelScope.launch {
        cartRepository.updateCartItemQuantity(cartId, action).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response.status!!) {
                    State.LOADING -> _loadingCartItemUpdate.postValue(true)
                    State.SUCCESS -> _loadingCartItemUpdate.postValue(false)
                    State.ERROR -> _loadingCartItemUpdate.postValue(false)
                }
            }
    }

    class Factory() : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
                return CartResources(DatabaseManager.instance.database.cartDao()) as T
            }
            throw Exception("No class supported")
        }
    }
}
