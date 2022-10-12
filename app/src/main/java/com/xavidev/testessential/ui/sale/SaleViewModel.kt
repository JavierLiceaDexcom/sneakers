package com.xavidev.testessential.ui.sale

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xavidev.testessential.R
import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.repository.AddressRepository
import com.xavidev.testessential.data.repository.CardRepository
import com.xavidev.testessential.data.repository.SneakersRepository
import com.xavidev.testessential.data.source.local.entity.Address
import com.xavidev.testessential.data.source.local.entity.Card
import com.xavidev.testessential.utils.NavigationViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SaleViewModel(
    private val sneakersRepository: SneakersRepository,
    private val addressRepository: AddressRepository,
    private val cardRepository: CardRepository,
) : NavigationViewModel() {

    private var sneakerIds = mutableListOf<String>()
    private var _totalPrice = MutableLiveData<Double>()
    val totalPrice: LiveData<Double> get() = _totalPrice

    private val _deliveryAddress = MutableLiveData<Address?>()
    val deliveryAddress: LiveData<Address?> get() = _deliveryAddress

    private val _allAddresses = MutableLiveData<List<Address>>()
    val allAddresses: LiveData<List<Address>> get() = _allAddresses

    private val _allAddressesEmpty = MutableLiveData<Boolean>()
    val allAddressesEmpty: LiveData<Boolean> get() = _allAddressesEmpty

    private val _defaultCard = MutableLiveData<Card?>()
    val defaultCard: LiveData<Card?> get() = _defaultCard

    private val _allCards = MutableLiveData<List<Card>>()
    val allCards: LiveData<List<Card>> get() = _allCards

    private val _allCardsEmpty = MutableLiveData<Boolean>()
    val allCardsEmpty: LiveData<Boolean> get() = _allCardsEmpty

    fun setSneakerIds(ids: List<String>) {
        sneakerIds.addAll(ids)
    }

    fun getSelectedSneakers() = viewModelScope.launch {
        sneakersRepository.getSneakersByIds(sneakerIds).flowOn(Dispatchers.IO)
            .collect { result ->
                when (result) {
                    is Result.Loading -> {}
                    is Result.Success -> {
                        val subtotal =
                            result.data.map { sneaker -> sneaker.price - ((sneaker.price * sneaker.discountPercentage) / 100) }
                                .first()
                        _totalPrice.postValue(subtotal)
                    }
                    is Result.Error -> {}
                }
            }
    }

    fun getDefaultAddress() = viewModelScope.launch {
        val result = addressRepository.getDefaultAddress()
        if (result is Result.Success) {
            _deliveryAddress.postValue(result.data)
        }
    }

    fun getAllAddresses() = viewModelScope.launch {
        val result = addressRepository.getAllAddresses()
        if (result is Result.Success) {
            val addresses = result.data
            _allAddresses.postValue(addresses)
            _allAddressesEmpty.postValue(addresses.isEmpty())
        } else {
            _allAddressesEmpty.postValue(true)
        }
    }

    fun setDefaultAddress(addressId: String) = viewModelScope.launch {
        val result = addressRepository.updateDefaultAddress(addressId)
        if (result is Result.Success){
            getAllAddresses()
        }
    }

    fun getDefaultCard() = viewModelScope.launch {
        cardRepository.getDefaultCard().flowOn(Dispatchers.IO)
            .collect { result ->
                when (result) {
                    is Result.Loading -> {}
                    is Result.Success -> _defaultCard.postValue(result.data)
                    is Result.Error -> {}
                }
            }
    }

    fun getAllCards() = viewModelScope.launch {
        cardRepository.getAllCards().flowOn(Dispatchers.IO)
            .collect { result ->
                when (result) {
                    is Result.Loading -> {}
                    is Result.Success -> {
                        val cards = result.data
                        _allCards.postValue(cards)
                        _allCardsEmpty.postValue(cards.isEmpty())
                    }
                    is Result.Error -> {}
                }

            }
    }

    private val _deliveryType = MutableLiveData(DeliveryType.DELIVER_HOME)
    val deliveryType: LiveData<DeliveryType> get() = _deliveryType

    fun onEditAddress(view: View) {
        navigateTo(view, R.id.action_orderAddressFragment_to_addressSelectionFragment)
    }

    fun onDelivery(view: View) {
        _deliveryType.value = DeliveryType.DELIVER_HOME
        navigateTo(view, R.id.action_orderAddressFragment_to_paymentMethodFragment)
    }

    fun onPickUp(view: View) {
        _deliveryType.value = DeliveryType.PICK_UP
        navigateTo(view, R.id.action_orderAddressFragment_to_paymentMethodFragment)
    }

    fun onConfirmOrder(view: View) {
        //Extra code here
        navigateTo(view, R.id.action_orderConfirmationFragment_to_purchaseFinishedDialogFragment2)
    }

    fun onOrderCompleteClose(view: View) {
        //Code here

    }
}

enum class DeliveryType {
    DELIVER_HOME, PICK_UP
}