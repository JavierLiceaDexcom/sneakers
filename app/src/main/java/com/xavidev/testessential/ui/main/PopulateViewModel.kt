package com.xavidev.testessential.ui.main

import androidx.lifecycle.*
import com.xavidev.testessential.data.State
import com.xavidev.testessential.data.db.DatabaseBuilder
import com.xavidev.testessential.data.entity.*
import com.xavidev.testessential.repository.PopulateRepository
import com.xavidev.testessential.resources.PopulateResources
import com.xavidev.testessential.utils.App
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
                when (response.status!!) {
                    State.LOADING -> {}
                    State.SUCCESS -> {
                        response.data?.let { count -> _sneakersCount.postValue(count) }
                    }
                    State.ERROR -> {}
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
        val sneakerTypes = JsonParserUtils.getObjectListFromJSON(Type::class.java, "types", App.getContext())
        val currencies = JsonParserUtils.getObjectListFromJSON(Currency::class.java, "currency", App.getContext())
        val brands = JsonParserUtils.getObjectListFromJSON(Brand::class.java, "brands", App.getContext())
        val images = JsonParserUtils.getObjectListFromJSON(Images::class.java, "images", App.getContext())
        val sneakers = JsonParserUtils.getObjectListFromJSON(Sneaker::class.java, "sneakers", App.getContext())

        insertSneakerCurrencies(currencies)
        insertSneakerTypes(sneakerTypes)
        insertBrands(brands)
        insertSneakers(sneakers)
        insertImages(images)
    }

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PopulateViewModel::class.java)) {
                return PopulateViewModel(
                    PopulateResources(
                        DatabaseBuilder.instance.database.brandsDao(),
                        DatabaseBuilder.instance.database.sneakerDao(),
                        DatabaseBuilder.instance.database.imagesDao()
                    )
                ) as T
            }
            throw Exception("Class not supported")
        }
    }
}