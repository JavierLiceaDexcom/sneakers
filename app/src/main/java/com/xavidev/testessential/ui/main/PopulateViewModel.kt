package com.xavidev.testessential.ui.main

import androidx.lifecycle.*
import com.xavidev.testessential.SneakersApplication
import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.repository.PopulateRepository
import com.xavidev.testessential.data.source.local.entity.*
import com.xavidev.testessential.utils.JsonParserUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class PopulateViewModel(
    private val populateRepository: PopulateRepository,
) : ViewModel() {

    private val _sneakersCount = MutableLiveData<Int>()
    val sneakersCount: LiveData<Int> get() = _sneakersCount

    fun getSneakersCount() = viewModelScope.launch {
        populateRepository.getSneakersCount().flowOn(Dispatchers.IO)
            .collect { response ->
                when (response) {
                    is Result.Loading -> {}
                    is Result.Success -> {
                        response.data.let { count -> _sneakersCount.postValue(count) }
                    }
                    is Result.Error -> {}
                }
            }
    }

    private fun insertBrands(brands: List<Brand>) = viewModelScope.launch {
        populateRepository.insertBrands(brands).flowOn(Dispatchers.IO).collect()
    }

    private fun insertImages(images: List<Images>) = viewModelScope.launch {
        populateRepository.insertImages(images).flowOn(Dispatchers.IO).collect()
    }

    private fun insertSneakers(sneakers: List<Sneaker>) = viewModelScope.launch {
        populateRepository.populateSneakersTable(sneakers).flowOn(Dispatchers.IO).collect()
    }

    private fun insertSneakerTypes(types: List<Type>) = viewModelScope.launch {
        populateRepository.populateTypesTable(types).flowOn(Dispatchers.IO).collect()
    }

    private fun insertSneakerCurrencies(currencies: List<Currency>) = viewModelScope.launch {
        populateRepository.populateCurrenciesTable(currencies).flowOn(Dispatchers.IO).collect()
    }

    fun populateDatabase() {
        val sneakerTypes = JsonParserUtils.getObjectListFromJSON(
            Type::class.java,
            "types",
            SneakersApplication.getContext()
        )
        val currencies = JsonParserUtils.getObjectListFromJSON(
            Currency::class.java,
            "currency",
            SneakersApplication.getContext()
        )
        val brands = JsonParserUtils.getObjectListFromJSON(
            Brand::class.java,
            "brands",
            SneakersApplication.getContext()
        )
        val images = JsonParserUtils.getObjectListFromJSON(
            Images::class.java,
            "images",
            SneakersApplication.getContext()
        )
        val sneakers = JsonParserUtils.getObjectListFromJSON(
            Sneaker::class.java,
            "sneakers",
            SneakersApplication.getContext()
        )

        insertSneakerCurrencies(currencies)
        insertSneakerTypes(sneakerTypes)
        insertBrands(brands)
        insertSneakers(sneakers)
        insertImages(images)
    }
}