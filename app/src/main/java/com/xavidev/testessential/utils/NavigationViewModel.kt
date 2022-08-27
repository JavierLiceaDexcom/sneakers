package com.xavidev.testessential.utils

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController

abstract class NavigationViewModel : ViewModel() {

    open fun navigateTo(view: View, @IdRes destinationId: Int) {
        view.findNavController().navigate(destinationId)
    }

    open fun navigateTo(view: View, @IdRes destinationId: Int, bundle: Bundle) {
        view.findNavController().navigate(destinationId, bundle)
    }
}