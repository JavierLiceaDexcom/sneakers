package com.xavidev.testessential.ui.sneakerDetail

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xavidev.testessential.R
import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.repository.CartRepository
import com.xavidev.testessential.data.repository.SneakersRepository
import com.xavidev.testessential.data.source.local.entity.Cart
import com.xavidev.testessential.data.source.local.entity.Images
import com.xavidev.testessential.data.source.local.entity.SneakerComplete
import com.xavidev.testessential.data.source.local.entity.toCart
import com.xavidev.testessential.utils.Event
import com.xavidev.testessential.utils.NavigationViewModel
import com.xavidev.testessential.utils.startNewActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SneakerDetailDialogFragmentViewModel(
    private val sneakersRepository: SneakersRepository,
    private val cartRepository: CartRepository
) : NavigationViewModel() {

    private val _sneakerLoading = MutableLiveData(false)
    val sneakerLoading: LiveData<Boolean> get() = _sneakerLoading

    private val _sneaker = MutableLiveData<SneakerComplete>()
    val sneaker: LiveData<SneakerComplete> get() = _sneaker

    private val _sneakerImages = MutableLiveData<Images>()
    val sneakerImages: LiveData<Images> get() = _sneakerImages

    private val _sneakerHasDiscount = MutableLiveData<Boolean>()
    val sneakerHasDiscount: LiveData<Boolean> get() = _sneakerHasDiscount

    private val _sneakerDiscount = MutableLiveData<String>()
    val sneakerDiscount: LiveData<String> get() = _sneakerDiscount

    private val _sneakerInCart = MutableLiveData(false)
    val sneakerInCart: LiveData<Boolean> get() = _sneakerInCart

    private val _sneakerFavorite = MutableLiveData(false)
    val sneakerFavorite: LiveData<Boolean> get() = _sneakerFavorite

    //Validations
    private val _errorMessage = MutableLiveData<Event<Int>>()
    val errorMessages: LiveData<Event<Int>> get() = _errorMessage

    private val _addedToCartMessage = MutableLiveData<Event<Int>>()
    val addedToCartMessage: LiveData<Event<Int>> get() = _addedToCartMessage

    private val _sizeSelected = MutableLiveData(false)
    private val sizeSelected: LiveData<Boolean> get() = _sizeSelected

    private val _colorSelected = MutableLiveData(false)
    private val colorSelected: LiveData<Boolean> get() = _colorSelected

    private val _buySneakerEvent = MutableLiveData<Event<Unit>>()
    val buySneakerEvent: LiveData<Event<Unit>> get() = _buySneakerEvent

    fun getSneaker(sneakerId: String) = viewModelScope.launch {
        sneakersRepository.getSneaker(sneakerId).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response) {
                    is Result.Loading -> _sneakerLoading.postValue(true)
                    is Result.Success -> {
                        _sneakerLoading.postValue(false)
                        response.data.let { sneaker ->
                            _sneaker.postValue(sneaker)
                            val discount = sneaker.discountPercentage
                            _sneakerHasDiscount.postValue(discount > 0)
                            _sneakerDiscount.postValue("- $discount%")
                            _sneakerInCart.postValue(sneaker.inCart)
                            _sneakerFavorite.postValue(sneaker.favorite)
                        }
                    }
                    is Result.Error -> _sneakerLoading.postValue(false)
                }
            }
    }

    private fun setFavorite(sneakerId: String, favorite: Boolean) = viewModelScope.launch {
        sneakersRepository.setFavorite(sneakerId, favorite).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response) {
                    is Result.Loading -> {}
                    is Result.Success -> _sneakerFavorite.postValue(favorite)
                    is Result.Error -> {}
                }
            }
    }

    private fun addSneakerToCart(cart: Cart) = viewModelScope.launch {
        cartRepository.insertCartItem(cart).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response) {
                    is Result.Loading -> {}
                    is Result.Success -> {}
                    is Result.Error -> {}
                }
            }
    }

    private fun removeSneakerFromCart(sneakerId: String) = viewModelScope.launch {
        cartRepository.deleteSneakerFromCart(sneakerId).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response) {
                    is Result.Loading -> {}
                    is Result.Success -> {}
                    is Result.Error -> {}
                }
            }
    }

    fun getSneakerImages(imagesId: String) = viewModelScope.launch {
        sneakersRepository.getSneakerImages(imagesId).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response) {
                    is Result.Loading -> {}
                    is Result.Success -> response.data?.let { images ->
                        _sneakerImages.postValue(
                            images
                        )
                    }
                    is Result.Error -> {}
                }
            }
    }

    private fun updateSneakerInCart(sneakerId: String, value: Boolean) = viewModelScope.launch {
        sneakersRepository.updateSneakerInCart(sneakerId, value).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response) {
                    is Result.Loading -> {}
                    is Result.Success -> _sneakerInCart.postValue(value)
                    is Result.Error -> {}
                }
            }
    }

    fun onSneakerFavorite() {
        val isFavorite = sneakerFavorite.value!!
        val sneakerId = sneaker.value?.id!!
        setFavorite(sneakerId, !isFavorite)
    }

    fun onSneakerToCart() {
        sneakerInCart.value?.let { inCart ->
            if (inCart) {
                _addedToCartMessage.value = Event(R.string.text_removed_from_cart)
                addSneakerToCart(inCart)
            } else {
                _addedToCartMessage.value = Event(R.string.text_sneaker_added_to_cart)
                if (!validateSizeAndColor()) return
                addSneakerToCart(inCart)
            }
        }
    }

    private fun addSneakerToCart(isAdded: Boolean) {
        val cartItem = sneaker.value?.toCart()!!
        updateSneakerInCart(cartItem.sneakerId, !isAdded)
        if (isAdded) removeSneakerFromCart(cartItem.sneakerId) else addSneakerToCart(cartItem)
    }

    fun onBuySneaker() {
        _buySneakerEvent.value = Event(Unit)
        validateSizeAndColor()
    }

    fun validateSizeAndColor(): Boolean {
        if (!sizeSelected.value!!) {
            _errorMessage.value = Event(R.string.text_select_size)
        }

        if (!colorSelected.value!!) {
            _errorMessage.value = Event(R.string.text_select_color)
        }
        return sizeSelected.value!! && colorSelected.value!!
    }

    fun startBuySneaker(fragment: FragmentActivity, destiny: AppCompatActivity) {
        fragment.startNewActivity(targetActivity = destiny, finish = false)
    }

    fun setColorSelected() {
        _colorSelected.postValue(true)
    }

    fun setSizeSelected() {
        _sizeSelected.postValue(true)
    }
}