package com.xavidev.testessential.ui.sneakers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExampleViewModel: ViewModel() {

    private val _myValue = MutableLiveData<Any>()
    val myValue: LiveData<Any> get() = _myValue

    fun setValue(value: Any){
        _myValue.value = value
    }
}