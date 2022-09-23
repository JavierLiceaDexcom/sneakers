package com.xavidev.testessential.ui.addEditAddress

import androidx.lifecycle.*
import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.Result.Success
import com.xavidev.testessential.data.repository.AddressRepository
import com.xavidev.testessential.data.source.local.entity.Address
import com.xavidev.testessential.utils.Event
import com.xavidev.testessential.utils.NavigationViewModel
import kotlinx.coroutines.launch

class AddEditAddressViewModel(private val addressRepository: AddressRepository) :
    NavigationViewModel() {

    private val _addressId = MutableLiveData<String>()

    private val _address = _addressId.switchMap { addressId ->
        addressRepository.observeAddressById(addressId).map { computeResult(it) }
    }

    val address: LiveData<Address?> get() = _address

    // Events

    private val _saveAddressEvent = MutableLiveData<Event<Unit>>()
    val saveAddressEvent: LiveData<Event<Unit>> get() = _saveAddressEvent

    // Functions

    private val _addressUpdatedEvent = MutableLiveData<Event<Unit>>()
    val addressUpdatedEvent: LiveData<Event<Unit>> get() = _addressUpdatedEvent

    fun getAddressById(addressId: String) = viewModelScope.launch {
        addressRepository.getAddressById(addressId)
    }

    fun insertAddress(address: Address) = viewModelScope.launch {
        addressRepository.insertAddress(address)
        _addressUpdatedEvent.postValue(Event(Unit))
    }

    fun updateAddress(address: Address) = viewModelScope.launch {
        addressRepository.updateAddress(address)
    }

    private fun computeResult(addressResult: Result<Address>): Address? {
        return if (addressResult is Success) {
            addressResult.data
        } else {
            null
        }
    }

    // Listeners
    fun onSaveAddress() {
        _saveAddressEvent.value = Event(Unit)
    }

}