package com.xavidev.testessential.ui.sale

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xavidev.testessential.R
import com.xavidev.testessential.utils.NavigationViewModel

class SaleViewModel : NavigationViewModel() {

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
}

enum class DeliveryType {
    DELIVER_HOME, PICK_UP
}