package com.xavidev.testessential.ui.sneakerDetail

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import com.xavidev.testessential.R
import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.source.local.entity.Cart
import com.xavidev.testessential.data.source.local.entity.Images
import com.xavidev.testessential.data.source.local.entity.SneakerComplete
import com.xavidev.testessential.data.source.local.entity.toCart
import com.xavidev.testessential.data.repository.CartRepository
import com.xavidev.testessential.data.repository.SneakersRepository
import com.xavidev.testessential.SneakersApplication
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
    private val _errorMessage = MutableLiveData<MutableList<String>>(mutableListOf())
    val errorMessages: LiveData<MutableList<String>> get() = _errorMessage

    private val _sizeSelected = MutableLiveData(false)
    private val sizeSelected: LiveData<Boolean> get() = _sizeSelected

    private val _colorSelected = MutableLiveData(false)
    private val colorSelected: LiveData<Boolean> get() = _colorSelected

    private val _isValid = MutableLiveData(false)
    val isValid: LiveData<Boolean> get() = _isValid

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
        Log.i("JAVI", "Set fav: ${!isFavorite}")
        setFavorite(sneakerId, !isFavorite)
    }

    fun onSneakerToCart() {
        val cartItem = sneaker.value?.toCart()!!
        sneakerInCart.value?.let { inCart ->
            updateSneakerInCart(cartItem.sneakerId, !inCart)
            if (inCart) removeSneakerFromCart(cartItem.sneakerId) else addSneakerToCart(cartItem)
        }
    }

    fun validateSizeAndColor() {
        _errorMessage.value?.clear()
        if (!sizeSelected.value!!) {
            _errorMessage.value?.add(
                SneakersApplication.getContext().getString(R.string.text_select_size)
            )
            _errorMessage.postValue(_errorMessage.value)
        }

        if (!colorSelected.value!!) {
            _errorMessage.value?.add(
                SneakersApplication.getContext().getString(R.string.text_select_color)
            )
            _errorMessage.postValue(_errorMessage.value)
        }
        _isValid.postValue(errorMessages.value?.isEmpty())
    }

    fun onBuySneaker(fragment: FragmentActivity, destiny: AppCompatActivity) {
        fragment.startNewActivity(targetActivity = destiny, finish = false)
    }

    fun setColorSelected() {
        _colorSelected.postValue(true)
    }

    fun setSizeSelected() {
        _sizeSelected.postValue(true)
    }
}