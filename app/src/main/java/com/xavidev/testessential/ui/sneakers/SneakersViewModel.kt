package com.xavidev.testessential.ui.sneakers

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import com.xavidev.testessential.data.State
import com.xavidev.testessential.data.db.DatabaseBuilder
import com.xavidev.testessential.data.entity.Brand
import com.xavidev.testessential.data.entity.Cart
import com.xavidev.testessential.data.entity.Sneaker
import com.xavidev.testessential.data.entity.SneakerComplete
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

    private val _sneaker = MutableLiveData<Sneaker>()
    val sneaker: LiveData<Sneaker> get() = _sneaker

    private val _brandsListLoading = MutableLiveData(false)
    val brandsListLoading: LiveData<Boolean> get() = _brandsListLoading

    private val _brandsList = MutableLiveData<List<Brand>>()
    val brandsList: LiveData<List<Brand>> get() = _brandsList

    private val _clearResults = MutableLiveData(false)
    val clearResults: LiveData<Boolean> get() = _clearResults

    fun setSneakerComplete(sneaker: SneakerComplete) {
        _sneakerComplete.value = sneaker
    }

    private fun setClearResults(value: Boolean){
        _clearResults.value = value
    }

    fun getAllSneakersComplete() = viewModelScope.launch {
        sneakersRepository.getAllCompleteSneakers().flowOn(Dispatchers.IO).collect { response ->
            when (response.status!!) {
                State.LOADING -> _sneakersListLoading.postValue(true)
                State.SUCCESS -> {
                    _sneakersListLoading.postValue(false)
                    _sneakersList.postValue(response.data ?: listOf())
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
                        _sneakersList.postValue(response.data ?: listOf())
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
                        response.data?.let { sneaker -> _sneaker.postValue(sneaker) }
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
                    State.SUCCESS -> {}
                    State.ERROR -> {}
                }
            }
    }

    fun getBrands() = viewModelScope.launch {
        brandsRepository.getBrands().flowOn(Dispatchers.IO)
            .collect { response ->
                when (response.status!!) {
                    State.LOADING -> _brandsListLoading.postValue(true)
                    State.SUCCESS -> {
                        _brandsListLoading.postValue(false)
                        response.data?.let { brands -> _brandsList.postValue(brands) }
                    }
                    State.ERROR -> _brandsListLoading.postValue(false)
                }
            }
    }

    fun addSneakerToCart(cart: Cart) = viewModelScope.launch {
        cartRepository.insertCartItem(cart).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response.status!!) {
                    State.LOADING -> {}
                    State.SUCCESS -> {}
                    State.ERROR -> {}
                }
            }
    }

    fun removeSneakerFromCart(cart: Cart) = viewModelScope.launch {
        cartRepository.deleteCartItem(cart).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response.status!!) {
                    State.LOADING -> {}
                    State.SUCCESS -> {}
                    State.ERROR -> {}
                }
            }
    }


    fun onBuySneaker(fragment: FragmentActivity, destiny: AppCompatActivity) {
        fragment.startNewActivity(targetActivity = destiny, finish = false)
    }

    fun onClearResult(){
        getAllSneakersComplete()
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