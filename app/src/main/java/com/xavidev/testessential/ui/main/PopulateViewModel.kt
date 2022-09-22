package com.xavidev.testessential.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.repository.PopulateRepository
import com.xavidev.testessential.data.source.local.entity.*
import com.xavidev.testessential.utils.Event
import com.xavidev.testessential.utils.JsonParserUtils
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

class PopulateViewModel(
    private val populateRepository: PopulateRepository,
) : ViewModel() {

    private val _sneakersCount = MutableLiveData<Int>()
    val sneakersCount: LiveData<Int> get() = _sneakersCount

    private val _databasePopulatedEvent = MutableLiveData<Event<Unit>>()
    val databasePopulatedEvent: LiveData<Event<Unit>> get() = _databasePopulatedEvent

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

    private suspend fun insertBrands(brands: List<Brand>) = viewModelScope.launch {
        populateRepository.insertBrands(brands).flowOn(Dispatchers.IO).collect()
    }

    private suspend fun insertImages(images: List<Images>) = viewModelScope.launch {
        populateRepository.insertImages(images).flowOn(Dispatchers.IO).collect()
    }

    private suspend fun insertSneakers(sneakers: List<Sneaker>) = viewModelScope.launch {
        populateRepository.populateSneakersTable(sneakers).flowOn(Dispatchers.IO).collect()
    }

    private suspend fun insertSneakerTypes(types: List<Type>) = viewModelScope.launch {
        populateRepository.populateTypesTable(types).flowOn(Dispatchers.IO).collect()
    }

    private suspend fun insertSneakerCurrencies(currencies: List<Currency>) =
        viewModelScope.launch {
            populateRepository.populateCurrenciesTable(currencies).flowOn(Dispatchers.IO).collect()
        }

    fun populateDatabase(context: Context) = viewModelScope.launch {

        val sneakerTypes =
            JsonParserUtils.getObjectListFromJSON(Type::class.java, TYPES_FILE_NAME, context)

        val currencies =
            JsonParserUtils.getObjectListFromJSON(Currency::class.java, CURRENCY_FILE_NAME, context)

        val brands =
            JsonParserUtils.getObjectListFromJSON(Brand::class.java, BRANDS_FILE_NAME, context)

        val images =
            JsonParserUtils.getObjectListFromJSON(Images::class.java, IMAGES_FILE_NAME, context)

        val sneakers =
            JsonParserUtils.getObjectListFromJSON(Sneaker::class.java, SNEAKERS_FILE_NAME, context)

        awaitAll(
            launch { insertSneakerCurrencies(currencies) },
            launch { insertSneakerTypes(sneakerTypes) },
            launch { insertBrands(brands) },
            launch { insertSneakers(sneakers) },
            launch { insertImages(images) }
        )
        delay(1000)
        _databasePopulatedEvent.postValue(Event(Unit))
    }

    private suspend fun awaitAll(vararg jobs: Job) {
        jobs.asList().joinAll()
    }

    companion object {
        private const val TYPES_FILE_NAME = "types"
        private const val CURRENCY_FILE_NAME = "currency"
        private const val BRANDS_FILE_NAME = "brands"
        private const val IMAGES_FILE_NAME = "images"
        private const val SNEAKERS_FILE_NAME = "sneakers"
    }
}