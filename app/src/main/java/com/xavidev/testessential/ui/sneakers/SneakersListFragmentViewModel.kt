package com.xavidev.testessential.ui.sneakers

import androidx.lifecycle.*
import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.source.local.entity.*
import com.xavidev.testessential.data.repository.SneakersRepository
import com.xavidev.testessential.utils.Event
import com.xavidev.testessential.utils.NavigationViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SneakersListFragmentViewModel(private val sneakersRepository: SneakersRepository) :
    NavigationViewModel() {

    private val _sneakersListLoading = MutableLiveData(false)
    val sneakersListLoading: LiveData<Boolean> get() = _sneakersListLoading

    private val _sneakersList = MutableLiveData<List<SneakerComplete>>()
    val sneakersList: LiveData<List<SneakerComplete>> get() = _sneakersList

    private val _clearResults = MutableLiveData<Event<Boolean>>()
    val clearResults: LiveData<Event<Boolean>> get() = _clearResults

    private val _showEmptyState = MutableLiveData(false)
    val showEmptyState: LiveData<Boolean> get() = _showEmptyState

    private fun setClearResults(value: Boolean) {
        _clearResults.value = Event(value)
    }

    fun getAllSneakersComplete() = viewModelScope.launch {
        sneakersRepository.getAllCompleteSneakers().flowOn(Dispatchers.IO).collect { response ->
            when (response) {
                is Result.Loading -> _sneakersListLoading.postValue(true)
                is Result.Success -> {
                    _sneakersListLoading.postValue(false)
                    val result = response.data
                    _showEmptyState.postValue(result.isEmpty())
                    _sneakersList.postValue(result)
                    setClearResults(false)
                }
                is Result.Error -> _sneakersListLoading.postValue(false)
            }
        }
    }

    fun getSneakersByBrand(brandId: String) = viewModelScope.launch {
        sneakersRepository.getSneakersByBrand(brandId).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response) {
                    is Result.Loading -> _sneakersListLoading.postValue(true)
                    is Result.Success -> {
                        _sneakersListLoading.postValue(false)
                        val result = response.data
                        _showEmptyState.postValue(result.isEmpty())
                        _sneakersList.postValue(result)
                        setClearResults(true)
                    }
                    is Result.Error -> _sneakersListLoading.postValue(false)
                }
            }
    }

    fun setFavorite(sneakerId: String, favorite: Boolean) = viewModelScope.launch {
        sneakersRepository.setFavorite(sneakerId, favorite).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response) {
                    is Result.Loading -> {}
                    is Result.Success -> {}
                    is Result.Error -> {}
                }
            }
    }

    fun onClearResult() {
        getAllSneakersComplete()
    }
}