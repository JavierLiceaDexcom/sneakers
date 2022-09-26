package com.xavidev.testessential.ui.addEditAddress

import android.util.Log
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

    private val ONE_SECOND_DELAY = 1000L

    private var _isNewAddress: Boolean = false

    private val _addressId = MutableLiveData<String>()

    private val _address = _addressId.switchMap { addressId ->
        addressRepository.observeAddressById(addressId).map { computeResult(it) }
    }

    val address: LiveData<Address?> get() = _address

    private val _addressSavedMessage = MutableLiveData<Event<Int>>()
    val addressSavedMessage: LiveData<Event<Int>> get() = _addressSavedMessage

    private val _addressErrorMessage = MutableLiveData<Event<Int>>()
    val addressErrorMessage: LiveData<Event<Int>> get() = _addressErrorMessage

    private val _actionText = MutableLiveData<Int>()
    val actionText: LiveData<Int> get() = _actionText

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
            _actionText.value = R.string.text_save_address
            return
        }

        _isNewAddress = false
        _actionText.value = R.string.text_update_address
    }

    fun saveAddress(address: Address) {
        viewModelScope.launch {
            if (_isNewAddress) insertAddress(address) else updateAddress(address)
        }
    }

    private suspend fun insertAddress(address: Address) {
        val result = addressRepository.insertAddress(address)
        if (result is Success) {
            _addressSavedMessage.postValue(Event(R.string.text_address_saved))
            delay(ONE_SECOND_DELAY)
            _addressUpdatedEvent.postValue(Event(Unit))
        } else {
            _addressSavedMessage.postValue(Event(R.string.text_address_saved_error))
        }
    }

    private suspend fun updateAddress(address: Address) {
        val result = addressRepository.updateAddress(address)
        if (result is Success) {
            _addressSavedMessage.postValue(Event(R.string.text_address_updated))
            delay(ONE_SECOND_DELAY)
            _addressUpdatedEvent.postValue(Event(Unit))
        } else {
            _addressSavedMessage.postValue(Event(R.string.text_address_updated_error))
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