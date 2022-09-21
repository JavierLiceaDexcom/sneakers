package com.xavidev.testessential.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.repository.CartRepository
import com.xavidev.testessential.data.source.local.dao.Action
import com.xavidev.testessential.data.source.local.entity.Cart
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
                when (response) {
                    is Result.Loading -> _loadingInsert.postValue(true)
                    is Result.Success -> _loadingInsert.postValue(false)
                    is Result.Error -> _loadingInsert.postValue(false)
                }
            }
    }

    fun deleteCardItem(cart: Cart) = viewModelScope.launch {
        cartRepository.deleteCartItem(cart).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response) {
                    is Result.Loading -> _loadingDelete.postValue(true)
                    is Result.Success -> _loadingDelete.postValue(false)
                    is Result.Error -> _loadingDelete.postValue(false)
                }
            }
    }

    fun getCartItems() = viewModelScope.launch {
        cartRepository.getCartItems().flowOn(Dispatchers.IO)
            .collect { response ->
                when (response) {
                    is Result.Loading -> _loadingCartItems.postValue(true)
                    is Result.Success -> {
                        _loadingCartItems.postValue(false)
                        _cartItems.postValue(response.data ?: listOf())
                    }
                    is Result.Error -> _loadingCartItems.postValue(false)
                }
            }
    }

    fun updateCartItemQuantity(cartId: String, action: Action) = viewModelScope.launch {
        cartRepository.updateCartItemQuantity(cartId, action).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response) {
                    is Result.Loading -> _loadingCartItemUpdate.postValue(true)
                    is Result.Success -> _loadingCartItemUpdate.postValue(false)
                    is Result.Error -> _loadingCartItemUpdate.postValue(false)
                }
            }
    }
}
