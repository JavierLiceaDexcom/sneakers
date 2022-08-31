package com.xavidev.testessential.ui.profile

import android.view.View
import com.xavidev.testessential.R
import com.xavidev.testessential.utils.NavigationViewModel

class PaymentMethodViewModel: NavigationViewModel() {

    fun openCardFormFragment(view: View){
        navigateTo(view, R.id.action_paymentMethodsFragment_to_cardFormFragment)
    }
}