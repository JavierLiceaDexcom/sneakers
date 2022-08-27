package com.xavidev.testessential.ui.intro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class IntroViewModel : ViewModel() {

    class IntroViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(IntroViewModel::class.java)) {
                return IntroViewModel() as T
            }
            throw Exception("No class supported")
        }
    }
}