package com.xavidev.testessential.ui.profile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.repository.UserRepository
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

    private val _signOutEvent = MutableLiveData<Event<Unit>>()
    val signOutEvent: LiveData<Event<Unit>> get() = _signOutEvent

    private val _signedOutEvent = MutableLiveData<Event<Unit>>()
    val signedOutEvent: LiveData<Event<Unit>> get() = _signedOutEvent

    fun clickAddressInfo() {
        _openAddressesEvent.value = Event(Unit)
    }

    fun clickPaymentMethods() {
        _openCardsEvent.value = Event(Unit)
    }

    fun onSignOut() {
        _signOutEvent.value = Event(Unit)
    }

    fun signOut(context: Context) = viewModelScope.launch {
        userRepository.signOut(context).flowOn(Dispatchers.IO).collect { result ->
            if (result is Result.Success) {
                _signedOutEvent.postValue(Event(Unit))
            }
        }
    }
}