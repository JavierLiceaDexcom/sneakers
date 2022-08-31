package com.xavidev.testessential.ui.profile

import android.view.View
import com.xavidev.testessential.R
import com.xavidev.testessential.utils.NavigationViewModel

class AddressViewModel: NavigationViewModel() {

    fun openAddressFormFragment(view: View){
        navigateTo(view, R.id.action_addressesFragment_to_addressFormFragment)
    }
}