package com.xavidev.testessential.ui.sneakers

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xavidev.testessential.R
import com.xavidev.testessential.data.DummyData
import com.xavidev.testessential.data.entity.Brand
import com.xavidev.testessential.data.entity.Sneaker
import com.xavidev.testessential.utils.App
import com.xavidev.testessential.utils.NavigationViewModel
import com.xavidev.testessential.utils.startNewActivity

class SneakersViewModel : NavigationViewModel() {

    private val _sneaker = MutableLiveData<Sneaker>()
    val sneaker: LiveData<Sneaker> get() = _sneaker

    fun setSneaker(sneaker: Sneaker) {
        _sneaker.value = sneaker
    }

    fun onBuySneaker(fragment: FragmentActivity, destiny: AppCompatActivity) {
        fragment.startNewActivity(targetActivity = destiny, finish = false)
    }
}