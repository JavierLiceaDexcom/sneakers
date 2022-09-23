package com.xavidev.testessential.ui.address

import android.view.View
import androidx.lifecycle.viewModelScope
import com.xavidev.testessential.R
import com.xavidev.testessential.data.repository.AddressRepository
import com.xavidev.testessential.data.source.local.entity.Address
import com.xavidev.testessential.utils.NavigationViewModel
import kotlinx.coroutines.launch

class AddressViewModel(private val addressRepository: AddressRepository) : NavigationViewModel() {

    fun openAddressFormFragment(view: View) {
        navigateTo(view, R.id.action_addressesFragment_to_addressFormFragment)
    }
}