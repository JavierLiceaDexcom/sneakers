package com.xavidev.testessential.ui.address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xavidev.testessential.R
import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.repository.AddressRepository
import com.xavidev.testessential.data.source.local.entity.Address
import com.xavidev.testessential.utils.Event
import kotlinx.coroutines.launch

class AddressViewModel(private val addressRepository: AddressRepository) : ViewModel() {

    private val _addressesList = MutableLiveData<List<Address>?>()
    val addressesList: LiveData<List<Address>?> get() = _addressesList

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> get() = _isEmpty

    private val _addressOptionMessage = MutableLiveData<Event<Int>>()
    val addressOptionMessage: LiveData<Event<Int>> get() = _addressOptionMessage

    // Events

    private val _defaultAddressUpdatedEvent = MutableLiveData<Event<Unit>>()
    val defaultAddressUpdatedEvent: LiveData<Event<Unit>> get() = _defaultAddressUpdatedEvent

    private val _addressRemovedEvent = MutableLiveData<Event<Unit>>()
    val addressRemovedEvent: LiveData<Event<Unit>> get() = _addressRemovedEvent

    // Functions

    fun getAddresses() = viewModelScope.launch {
        val result = addressRepository.getAllAddresses()
        if (result is Result.Success) {
            val data = result.data
            _isEmpty.postValue(data.isEmpty())
            _addressesList.postValue(data)
        } else {
            _isEmpty.postValue(true)
        }
    }

    fun setDefaultAddress(addressId: String) = viewModelScope.launch {
        val result = addressRepository.updateDefaultAddress(addressId)
        if (result is Result.Success){
            _defaultAddressUpdatedEvent.postValue(Event(Unit))
        } else {
            _addressOptionMessage.postValue(Event(R.string.text_default_address_error))
        }
    }

    fun removeAddress(addressId: String) = viewModelScope.launch {
        val result = addressRepository.deleteAddressById(addressId)
        if (result is Result.Success){
            _addressRemovedEvent.postValue(Event(Unit))
        } else {
            _addressOptionMessage.postValue(Event(R.string.text_remove_address_error))
        }
    }
}