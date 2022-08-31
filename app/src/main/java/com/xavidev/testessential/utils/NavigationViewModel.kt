package com.xavidev.testessential.utils

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.IdRes
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController

abstract class NavigationViewModel : ViewModel() {

    companion object {
        const val TAG = "NavigationViewModel"
    }

    open fun navigateTo(view: View, @IdRes destinationId: Int) {
        view.findNavController().navigate(destinationId)
        Log.i(TAG, "Navigate from ${view.id} to $destinationId")
    }

    open fun navigateTo(view: View, @IdRes destinationId: Int, bundle: Bundle) {
        view.findNavController().navigate(destinationId, bundle)
        Log.i(TAG, "Navigate from ${view.id} to $destinationId")
    }
}