package com.xavidev.testessential.ui.sneakers

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import com.xavidev.testessential.data.State
import com.xavidev.testessential.data.db.DatabaseManager
import com.xavidev.testessential.data.entity.Sneaker
import com.xavidev.testessential.repository.SneakersRepository
import com.xavidev.testessential.resources.SneakersResources
import com.xavidev.testessential.utils.NavigationViewModel
import com.xavidev.testessential.utils.startNewActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SneakersViewModel(private val sneakersRepository: SneakersRepository) :
    NavigationViewModel() {

    private val _sneaker = MutableLiveData<Sneaker>()
    val sneaker: LiveData<Sneaker> get() = _sneaker

    fun setSneaker(sneaker: Sneaker) {
        _sneaker.value = sneaker
    }

    fun onBuySneaker(fragment: FragmentActivity, destiny: AppCompatActivity) {
        fragment.startNewActivity(targetActivity = destiny, finish = false)
    }

    fun insertSneakers(sneakers: List<Sneaker>) = viewModelScope.launch {
        sneakersRepository.populateSneakersTable(sneakers).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response.status!!) {
                    State.LOADING -> {}
                    State.SUCCESS -> {}
                    State.ERROR -> {}
                }
            }
    }

    fun getAllSneakers() = viewModelScope.launch {
        sneakersRepository.getAllSneakers().flowOn(Dispatchers.IO)
            .collect { response ->
                when (response.status!!) {
                    State.LOADING -> {}
                    State.SUCCESS -> {}
                    State.ERROR -> {}
                }
            }
    }

    fun getSneakersByBrand(brandId: String) = viewModelScope.launch {
        sneakersRepository.getSneakersByBrand(brandId).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response.status!!) {
                    State.LOADING -> {}
                    State.SUCCESS -> {}
                    State.ERROR -> {}
                }
            }
    }

    fun getSneakersByType(typeId: String) = viewModelScope.launch {
        sneakersRepository.getSneakersByType(typeId).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response.status!!) {
                    State.LOADING -> {}
                    State.SUCCESS -> {}
                    State.ERROR -> {}
                }
            }
    }

    fun getSneaker(sneakerId: String) = viewModelScope.launch {
        sneakersRepository.getSneaker(sneakerId).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response.status!!) {
                    State.LOADING -> {}
                    State.SUCCESS -> {}
                    State.ERROR -> {}
                }
            }
    }

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SneakersViewModel::class.java)) {
                return SneakersResources(DatabaseManager.instance.database.sneakerDao()) as T
            }
            throw Exception("Class not supported")
        }
    }
}