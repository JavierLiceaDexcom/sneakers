package com.xavidev.testessential.ui.sneakers

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import com.xavidev.testessential.R
import com.xavidev.testessential.data.State
import com.xavidev.testessential.data.db.DatabaseBuilder
import com.xavidev.testessential.data.entity.Cart
import com.xavidev.testessential.data.entity.Images
import com.xavidev.testessential.data.entity.SneakerComplete
import com.xavidev.testessential.data.entity.toCart
import com.xavidev.testessential.repository.CartRepository
import com.xavidev.testessential.repository.SneakersRepository
import com.xavidev.testessential.resources.CartResources
import com.xavidev.testessential.resources.SneakersResources
import com.xavidev.testessential.utils.App
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
                when (response.status!!) {
                    State.LOADING -> _sneakerLoading.postValue(true)
                    State.SUCCESS -> {
                        _sneakerLoading.postValue(false)
                        response.data?.let { sneaker ->
                            _sneaker.postValue(sneaker)
                            val discount = sneaker.discountPercentage
                            _sneakerHasDiscount.postValue(discount > 0)
                            _sneakerDiscount.postValue("- $discount%")
                            _sneakerInCart.postValue(sneaker.inCart)
                            _sneakerFavorite.postValue(sneaker.favorite)
                        }
                    }
                    State.ERROR -> _sneakerLoading.postValue(false)
                }
            }
    }

    private fun setFavorite(sneakerId: String, favorite: Boolean) = viewModelScope.launch {
        sneakersRepository.setFavorite(sneakerId, favorite).flowOn(Dispatchers.IO)
            .collect { response ->
                Log.i("JAVI", "Fav: $favorite")
                when (response.status!!) {
                    State.LOADING -> {}
                    State.SUCCESS -> _sneakerFavorite.postValue(favorite)
                    State.ERROR -> {}
                }
            }
    }

    private fun addSneakerToCart(cart: Cart) = viewModelScope.launch {
        cartRepository.insertCartItem(cart).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response.status!!) {
                    State.LOADING -> {}
                    State.SUCCESS -> {}
                    State.ERROR -> {}
                }
            }
    }

    private fun removeSneakerFromCart(sneakerId: String) = viewModelScope.launch {
        cartRepository.deleteSneakerFromCart(sneakerId).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response.status!!) {
                    State.LOADING -> {}
                    State.SUCCESS -> {}
                    State.ERROR -> {}
                }
            }
    }

    fun getSneakerImages(imagesId: String) = viewModelScope.launch {
        sneakersRepository.getSneakerImages(imagesId).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response.status!!) {
                    State.LOADING -> {}
                    State.SUCCESS -> response.data?.let { images -> _sneakerImages.postValue(images) }
                    State.ERROR -> {}
                }
            }
    }

    private fun updateSneakerInCart(sneakerId: String, value: Boolean) = viewModelScope.launch {
        sneakersRepository.updateSneakerInCart(sneakerId, value).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response.status!!) {
                    State.LOADING -> {}
                    State.SUCCESS -> _sneakerInCart.postValue(value)
                    State.ERROR -> {}
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
            _errorMessage.value?.add(App.getContext().getString(R.string.text_select_size))
            _errorMessage.postValue(_errorMessage.value)
        }

        if (!colorSelected.value!!) {
            _errorMessage.value?.add(App.getContext().getString(R.string.text_select_color))
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

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SneakerDetailDialogFragmentViewModel::class.java)) {
                return SneakerDetailDialogFragmentViewModel(
                    SneakersResources(
                        DatabaseBuilder.instance.database.sneakerDao(),
                        DatabaseBuilder.instance.database.brandsDao(),
                    ),
                    CartResources(DatabaseBuilder.instance.database.cartDao())
                ) as T
            }
            throw Exception("Class not supported")
        }
    }
}