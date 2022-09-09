package com.xavidev.testessential.ui.purchases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xavidev.testessential.data.db.DatabaseBuilder
import com.xavidev.testessential.repository.PurchaseRepository
import com.xavidev.testessential.resources.PurchaseResources
import com.xavidev.testessential.utils.NavigationViewModel

class PurchasesViewModel(private val purchaseRepository: PurchaseRepository) :
    NavigationViewModel() {


    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PurchasesViewModel::class.java)) {
                return PurchasesViewModel(
                    PurchaseResources(DatabaseBuilder.instance.database.purchasesDao())
                ) as T
            }
            throw Exception("Class not supported")
        }
    }
}