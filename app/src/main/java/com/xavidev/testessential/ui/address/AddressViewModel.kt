package com.xavidev.testessential.ui.address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.repository.AddressRepository
import com.xavidev.testessential.data.source.local.entity.Address
import com.xavidev.testessential.utils.NavigationViewModel
import kotlinx.coroutines.launch

class AddressViewModel(private val addressRepository: AddressRepository) : NavigationViewModel() {

    private val _addressesList = MutableLiveData<List<Address>?>()
    val addressesList: LiveData<List<Address>?> get() = _addressesList

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> get() = _isEmpty

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
}