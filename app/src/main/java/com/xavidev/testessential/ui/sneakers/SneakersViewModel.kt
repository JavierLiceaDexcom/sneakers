package com.xavidev.testessential.ui.sneakers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xavidev.testessential.data.DummyData
import com.xavidev.testessential.data.entity.Brand
import com.xavidev.testessential.data.entity.Sneaker
import com.xavidev.testessential.utils.NavigationViewModel

class SneakersViewModel : NavigationViewModel() {

    fun getSneakersList(): LiveData<List<Sneaker>> = MutableLiveData(DummyData.sneakers)

    fun getBrandList(): LiveData<List<Brand>> {
        val brands = DummyData.sneakers.map { sneaker -> sneaker.brand }.sortedBy { it.name }
        return MutableLiveData(brands.distinctBy { it.name })
    }
}