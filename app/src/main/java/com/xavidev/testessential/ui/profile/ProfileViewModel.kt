package com.xavidev.testessential.ui.profile

import android.util.Log
import android.view.View
import com.xavidev.testessential.R
import com.xavidev.testessential.utils.NavigationViewModel

class ProfileViewModel : NavigationViewModel() {

    fun clickAddressInfo(view: View) {
        Log.i("JAVI", "KK")
        navigateTo(view, R.id.action_profileFragment_to_addressesFragment)
    }

    fun clickPaymentMethods(view: View) {
        navigateTo(view, R.id.action_profileFragment_to_paymentMethodsFragment)
    }
}