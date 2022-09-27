package com.xavidev.testessential.ui.profile

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.repository.UserRepository
import com.xavidev.testessential.data.source.local.entity.User
import com.xavidev.testessential.utils.Event
import com.xavidev.testessential.utils.NavigationViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class ProfileViewModel(private val userRepository: UserRepository) : NavigationViewModel() {

    private val _openAddressesEvent = MutableLiveData<Event<Unit>>()
    val openAddressesEvent: LiveData<Event<Unit>> get() = _openAddressesEvent

    private val _openCardsEvent = MutableLiveData<Event<Unit>>()
    val openCardsEvent: LiveData<Event<Unit>> get() = _openCardsEvent

    fun clickAddressInfo() {
        _openAddressesEvent.value = Event(Unit)
    }

    fun clickPaymentMethods() {
        _openCardsEvent.value = Event(Unit)
    }

    fun insertUser(user: User) = viewModelScope.launch {
        userRepository.insertUser(user).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response) {
                    is Result.Loading -> {}
                    is Result.Success -> {}
                    is Result.Error -> {}
                }
            }
    }

    fun getUser(userId: String) = viewModelScope.launch {
        userRepository.getUser(userId).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response) {
                    is Result.Loading -> {}
                    is Result.Success -> {}
                    is Result.Error -> {}
                }
            }
    }

    fun updateUser(user: User) = viewModelScope.launch {
        userRepository.updateUser(user).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response) {
                    is Result.Loading -> {}
                    is Result.Success -> {}
                    is Result.Error -> {}
                }
            }
    }
}