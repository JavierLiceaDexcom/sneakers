package com.xavidev.testessential.ui.addEditAddress

import androidx.lifecycle.*
import com.xavidev.testessential.R
import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.Result.Success
import com.xavidev.testessential.data.repository.AddressRepository
import com.xavidev.testessential.data.source.local.entity.Address
import com.xavidev.testessential.utils.Event
import com.xavidev.testessential.utils.NavigationViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AddEditAddressViewModel(private val addressRepository: AddressRepository) :
    NavigationViewModel() {

    private var _isNewAddress: Boolean = false
    private val _addressId = MutableLiveData<String>()

    private val _address = _addressId.switchMap { addressId ->
        addressRepository.observeAddressById(addressId).map { computeResult(it) }
    }

    val address: LiveData<Address?> get() = _address

    private val _addressSavedMessage = MutableLiveData<Event<Int>>()
    val addressSavedMessage: LiveData<Event<Int>> get() = _addressSavedMessage

    // Events

    private val _saveAddressEvent = MutableLiveData<Event<Unit>>()
    val saveAddressEvent: LiveData<Event<Unit>> get() = _saveAddressEvent

    private val _addressUpdatedEvent = MutableLiveData<Event<Unit>>()
    val addressUpdatedEvent: LiveData<Event<Unit>> get() = _addressUpdatedEvent

    // Functions

    fun start(addressId: String?) {
        addressId?.let { _addressId.value = it }
        if (addressId == null) {
            _isNewAddress = true
            return
        }

        _isNewAddress = false
        getAddressById(addressId)
    }

    private fun getAddressById(addressId: String) = viewModelScope.launch {
        addressRepository.getAddressById(addressId)
    }

    fun saveAddress(address: Address) {
        viewModelScope.launch {
            if (_isNewAddress) {
                addressRepository.insertAddress(address)
                _addressSavedMessage.postValue(Event(R.string.text_address_saved))
            } else {
                addressRepository.updateAddress(address)
                _addressSavedMessage.postValue(Event(R.string.text_address_updated))
            }

            delay(1000)
            _addressUpdatedEvent.postValue(Event(Unit))
        }
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