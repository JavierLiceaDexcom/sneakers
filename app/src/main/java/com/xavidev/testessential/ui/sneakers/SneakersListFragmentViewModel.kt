package com.xavidev.testessential.ui.sneakers

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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SneakersListFragmentViewModel(
    private val sneakersRepository: SneakersRepository,
    private val brandsRepository: BrandsRepository,
) : NavigationViewModel() {

    private val _sneakersListLoading = MutableLiveData(false)
    val sneakersListLoading: LiveData<Boolean> get() = _sneakersListLoading

    private val _sneakersList = MutableLiveData<List<SneakerComplete>>()
    val sneakersList: LiveData<List<SneakerComplete>> get() = _sneakersList

    private val _brandsList = MutableLiveData<List<Brand>>()
    val brandsList: LiveData<List<Brand>> get() = _brandsList

    private val _clearResults = MutableLiveData(false)
    val clearResults: LiveData<Boolean> get() = _clearResults

    private val _showEmptyState = MutableLiveData(false)
    val showEmptyState: LiveData<Boolean> get() = _showEmptyState

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
                    State.LOADING -> {}
                    State.SUCCESS -> response.data?.let { brands -> _brandsList.postValue(brands) }
                    State.ERROR -> {}
                }
            }
    }

    fun onClearResult() {
        getAllSneakersComplete()
    }

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SneakersListFragmentViewModel::class.java)) {
                return SneakersListFragmentViewModel(
                    SneakersResources(
                        DatabaseBuilder.instance.database.sneakerDao(),
                        DatabaseBuilder.instance.database.brandsDao(),
                    ),
                    SneakersResources(
                        DatabaseBuilder.instance.database.sneakerDao(),
                        DatabaseBuilder.instance.database.brandsDao(),
                    ),
                ) as T
            }
            throw Exception("Class not supported")
        }
    }
}