package com.xavidev.testessential.ui.sneakers

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import com.xavidev.testessential.data.State
import com.xavidev.testessential.data.db.DatabaseBuilder
import com.xavidev.testessential.data.entity.Brand
import com.xavidev.testessential.data.entity.Sneaker
import com.xavidev.testessential.repository.BrandsRepository
import com.xavidev.testessential.repository.SneakersRepository
import com.xavidev.testessential.resources.SneakersResources
import com.xavidev.testessential.utils.NavigationViewModel
import com.xavidev.testessential.utils.startNewActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SneakersViewModel(
    private val sneakersRepository: SneakersRepository,
    private val brandsRepository: BrandsRepository
) : NavigationViewModel() {

    private val _sneakersListLoading = MutableLiveData(false)
    val sneakersListLoading: LiveData<Boolean> get() = _sneakersListLoading

    private val _sneakersList = MutableLiveData<List<Sneaker>>()
    val sneakersList: LiveData<List<Sneaker>> get() = _sneakersList

    private val _sneakerLoading = MutableLiveData(false)
    val sneakerLoading: LiveData<Boolean> get() = _sneakerLoading

    private val _sneaker = MutableLiveData<Sneaker>()
    val sneaker: LiveData<Sneaker> get() = _sneaker

    private val _brandsListLoading = MutableLiveData(false)
    val brandsListLoading: LiveData<Boolean> get() = _brandsListLoading

    private val _brandsList = MutableLiveData<List<Brand>>()
    val brandsList: LiveData<List<Brand>> get() = _brandsList

    fun setSneaker(sneaker: Sneaker) {
        _sneaker.value = sneaker
    }

    fun getAllSneakers() = viewModelScope.launch {
        sneakersRepository.getAllSneakers().flowOn(Dispatchers.IO)
            .collect { response ->
                when (response.status!!) {
                    State.LOADING -> _sneakersListLoading.postValue(true)
                    State.SUCCESS -> {
                        _sneakersListLoading.postValue(false)
                        _sneakersList.postValue(response.data ?: listOf())
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
                        _sneakersList.postValue(response.data ?: listOf())
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

    fun onBuySneaker(fragment: FragmentActivity, destiny: AppCompatActivity) {
        fragment.startNewActivity(targetActivity = destiny, finish = false)
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
                    )
                ) as T
            }
            throw Exception("Class not supported")
        }
    }
}