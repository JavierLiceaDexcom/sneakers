package com.xavidev.testessential.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.repository.SneakersRepository
import com.xavidev.testessential.data.source.local.entity.SneakerComplete
import com.xavidev.testessential.utils.NavigationViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SearchViewModel(private val sneakersRepository: SneakersRepository) : NavigationViewModel() {

    private val _sneakers = MutableLiveData<List<SneakerComplete>?>()
    val sneakers: LiveData<List<SneakerComplete>?> get() = _sneakers

    private val _isEmpty = MutableLiveData(true)
    val isEmpty: LiveData<Boolean> get() = _isEmpty

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun searchSneakersByName(query: String) = viewModelScope.launch {
        sneakersRepository.searchSneakerByName(query).flowOn(Dispatchers.IO)
            .catch { e -> e.printStackTrace() }.collect { result ->
                when (result) {
                    is Result.Loading -> _isLoading.postValue(true)
                    is Result.Success -> {
                        _isEmpty.postValue(result.data.isEmpty())
                        _sneakers.postValue(result.data)
                        _isLoading.postValue(false)
                    }
                    is Result.Error -> {
                        _isEmpty.postValue(true)
                        _isLoading.postValue(false)
                    }
                }
            }
    }

    fun getSneakersByBrand(brandId: String) = viewModelScope.launch {
        sneakersRepository.getSneakersByBrand(brandId).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response) {
                    is Result.Loading -> _isLoading.postValue(true)
                    is Result.Success -> {
                        _isLoading.postValue(false)
                        val result = response.data
                        _isEmpty.postValue(result.isEmpty())
                        _sneakers.postValue(result)
                    }
                    is Result.Error -> _isLoading.postValue(false)
                }
            }
    }
}