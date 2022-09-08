package com.xavidev.testessential.ui.purchases

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xavidev.testessential.R
import com.xavidev.testessential.data.db.DatabaseBuilder
import com.xavidev.testessential.repository.PurchaseRepository
import com.xavidev.testessential.resources.PurchaseResources
import com.xavidev.testessential.utils.App
import com.xavidev.testessential.utils.NavigationViewModel
import com.xavidev.testessential.utils.startNewActivity

class PurchasesViewModel(private val purchaseRepository: PurchaseRepository) :
    NavigationViewModel() {

    private val _errorMessage = MutableLiveData<MutableList<String>>(mutableListOf())
    val errorMessages: LiveData<MutableList<String>> get() = _errorMessage

    private val _sizeSelected = MutableLiveData(false)
    private val sizeSelected: LiveData<Boolean> get() = _sizeSelected

    private val _colorSelected = MutableLiveData(false)
    private val colorSelected: LiveData<Boolean> get() = _colorSelected

    private val _isValid = MutableLiveData(false)
    val isValid: LiveData<Boolean> get() = _isValid

    fun validateSizeAndColor() {
        _errorMessage.value?.clear()
        if (!sizeSelected.value!!) {
            _errorMessage.value?.add(App.getContext().getString(R.string.text_select_size))
            _errorMessage.postValue(_errorMessage.value)
        }

        if (!colorSelected.value!!) {
            _errorMessage.value?.add(App.getContext().getString(R.string.text_select_color))
            _errorMessage.postValue(_errorMessage.value)
        }
        _isValid.postValue(errorMessages.value?.isEmpty())
    }

    fun onBuySneaker(fragment: FragmentActivity, destiny: AppCompatActivity) {
        fragment.startNewActivity(targetActivity = destiny, finish = false)
    }

    fun setColorSelected() {
        _colorSelected.postValue(true)
    }

    fun setSizeSelected() {
        _sizeSelected.postValue(true)
    }

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PurchasesViewModel::class.java)) {
                return PurchasesViewModel(
                    PurchaseResources(DatabaseBuilder.instance.database.purchasesDao())
                ) as T
            }
            throw Exception("")
        }
    }
}