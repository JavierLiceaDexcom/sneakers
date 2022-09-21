package com.xavidev.testessential.ui.sneakers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.repository.BrandsRepository
import com.xavidev.testessential.data.source.local.entity.Brand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class BrandsViewModel(private val brandsRepository: BrandsRepository): ViewModel() {

    private val _brandsList = MutableLiveData<List<Brand>>()
    val brandsList: LiveData<List<Brand>> get() = _brandsList

    fun getBrands() = viewModelScope.launch {
        brandsRepository.getBrands().flowOn(Dispatchers.IO)
            .collect { response ->
                when (response) {
                    is Result.Loading -> {}
                    is Result.Success -> response.data.let { brands -> _brandsList.postValue(brands) }
                    is Result.Error -> {}
                }
            }
    }
}