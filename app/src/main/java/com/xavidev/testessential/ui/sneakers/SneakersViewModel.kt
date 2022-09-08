package com.xavidev.testessential.ui.sneakers

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import com.xavidev.testessential.data.State
import com.xavidev.testessential.data.db.DatabaseBuilder
import com.xavidev.testessential.data.entity.*
import com.xavidev.testessential.repository.BrandsRepository
import com.xavidev.testessential.repository.CartRepository
import com.xavidev.testessential.repository.SneakersRepository
import com.xavidev.testessential.resources.CartResources
import com.xavidev.testessential.resources.SneakersResources
import com.xavidev.testessential.utils.NavigationViewModel
import com.xavidev.testessential.utils.startNewActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SneakersViewModel(
    private val sneakersRepository: SneakersRepository,
    private val brandsRepository: BrandsRepository,
    private val cartRepository: CartRepository
) : NavigationViewModel() {

    private val _sneakersListLoading = MutableLiveData(false)
    val sneakersListLoading: LiveData<Boolean> get() = _sneakersListLoading

    private val _sneakersList = MutableLiveData<List<SneakerComplete>>()
    val sneakersList: LiveData<List<SneakerComplete>> get() = _sneakersList

    private val _sneakerLoading = MutableLiveData(false)
    val sneakerLoading: LiveData<Boolean> get() = _sneakerLoading

    private val _sneakerComplete = MutableLiveData<SneakerComplete>()
    val sneakerComplete: LiveData<SneakerComplete> get() = _sneakerComplete

    private val _sneaker = MutableLiveData<SneakerComplete>()
    val sneaker: LiveData<SneakerComplete> get() = _sneaker

    private val _brandsList = MutableLiveData<List<Brand>>()
    val brandsList: LiveData<List<Brand>> get() = _brandsList

    private val _clearResults = MutableLiveData(false)
    val clearResults: LiveData<Boolean> get() = _clearResults

    private val _showEmptyState = MutableLiveData(false)
    val showEmptyState: LiveData<Boolean> get() = _showEmptyState

    //Sneaker images
    private val _sneakerImages = MutableLiveData<Images>()
    val sneakerImages: LiveData<Images> get() = _sneakerImages

    //Sneaker images
    private val _sneakerHasDiscount = MutableLiveData<Boolean>()
    val sneakerHasDiscount: LiveData<Boolean> get() = _sneakerHasDiscount

    private val _sneakerDiscount = MutableLiveData<String>()
    val sneakerDiscount: LiveData<String> get() = _sneakerDiscount

    fun setSneakerComplete(sneaker: SneakerComplete) {
        _sneakerComplete.value = sneaker
    }

    private val _sneakerInCart = MutableLiveData(false)
    val sneakerInCart: LiveData<Boolean> get() = _sneakerInCart

    private fun setClearResults(value: Boolean) {
        _clearResults.value = value
    }

    fun getAllSneakersComplete() = viewModelScope.launch {
        sneakersRepository.getAllCompleteSneakers().flowOn(Dispatchers.IO).collect { response ->
            when (response.status!!) {
                State.LOADING -> _sneakersListLoading.postValue(true)
                State.SUCCESS -> {
                    _sneakersListLoading.postValue(false)
                    val result = response.data ?: listOf()
                    _showEmptyState.postValue(result.isEmpty())
                    _sneakersList.postValue(result)
                    setClearResults(false)
                }
                State.ERROR -> _sneakersListLoading.postValue(false)
            }
        }
    }

    fun getSneakersByBrand(brandId: String) = viewModelScope.launch {
        sneakersRepository.getSneakersByBrand(brandId).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response.status!!) {
                    State.LOADING -> _sneakersListLoading.postValue(true)
                    State.SUCCESS -> {
                        _sneakersListLoading.postValue(false)
                        val result = response.data ?: listOf()
                        _showEmptyState.postValue(result.isEmpty())
                        _sneakersList.postValue(result)
                        setClearResults(true)
                    }
                    State.ERROR -> _sneakersListLoading.postValue(false)
                }
            }
    }

    fun getSneakersByType(typeId: String) = viewModelScope.launch {
        sneakersRepository.getSneakersByType(typeId).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response.status!!) {
                    State.LOADING -> _sneakersListLoading.postValue(true)
                    State.SUCCESS -> {
                        _sneakersListLoading.postValue(false)
                        //_sneakersList.postValue(response.data ?: listOf())
                    }
                    State.ERROR -> _sneakersListLoading.postValue(false)
                }
            }
    }

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
                        }
                    }
                    State.ERROR -> _sneakerLoading.postValue(false)
                }
            }
    }

    fun setFavorite(sneakerId: String, favorite: Boolean) = viewModelScope.launch {
        sneakersRepository.setFavorite(sneakerId, favorite).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response.status!!) {
                    State.LOADING -> {}
                    State.SUCCESS -> sneaker.value?.id?.let { id -> getSneaker(id) }
                    State.ERROR -> {}
                }
            }
    }

    fun getBrands() = viewModelScope.launch {
        brandsRepository.getBrands().flowOn(Dispatchers.IO)
            .collect { response ->
                when (response.status!!) {
                    State.LOADING -> {}
                    State.SUCCESS -> response.data?.let { brands -> _brandsList.postValue(brands) }
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


    fun onBuySneaker(fragment: FragmentActivity, destiny: AppCompatActivity) {
        fragment.startNewActivity(targetActivity = destiny, finish = false)
    }

    fun onClearResult() {
        getAllSneakersComplete()
    }

    fun onSneakerFavorite() {
        val isFavorite = sneaker.value?.favorite
        setFavorite(sneaker.value?.id!!, isFavorite!!.not())
    }

    fun onSneakerToCart() {
        val cartItem = sneaker.value?.toCart()!!
        sneakerInCart.value?.let { inCart ->
            updateSneakerInCart(cartItem.sneakerId, !inCart)
            if (inCart) removeSneakerFromCart(cartItem.sneakerId) else addSneakerToCart(cartItem)
        }
    }

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SneakersViewModel::class.java)) {
                return SneakersViewModel(
                    SneakersResources(
                        DatabaseBuilder.instance.database.sneakerDao(),
                        DatabaseBuilder.instance.database.brandsDao(),
                    ),
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