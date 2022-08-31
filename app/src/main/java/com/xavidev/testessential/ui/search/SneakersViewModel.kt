package com.xavidev.testessential.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xavidev.testessential.R
import com.xavidev.testessential.data.DummyData
import com.xavidev.testessential.data.entity.Brand
import com.xavidev.testessential.data.entity.Sneaker
import com.xavidev.testessential.utils.App
import com.xavidev.testessential.utils.NavigationViewModel

class SneakersViewModel : NavigationViewModel() {

    private val _sneaker = MutableLiveData<Sneaker>()
    val sneaker: LiveData<Sneaker> get() = _sneaker

    fun setSneaker(sneaker: Sneaker) {
        _sneaker.value = sneaker
    }

    fun getSneakersList(): LiveData<List<Sneaker>> = MutableLiveData(DummyData.sneakers)

    fun getBrandList(): LiveData<List<Brand>> {
        val brands = DummyData.sneakers.map { sneaker -> sneaker.brand }.sortedBy { it.name }
        return MutableLiveData(brands.distinctBy { it.name })
    }

    fun getPriceWithCurrency() = MutableLiveData(
        App.getContext().getString(
            R.string.text_price_currency,
            sneaker.value?.price,
            sneaker.value?.currency?.name
        )
    )
}