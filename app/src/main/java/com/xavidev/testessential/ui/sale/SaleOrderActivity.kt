package com.xavidev.testessential.ui.sale

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xavidev.testessential.SneakersApplication
import com.xavidev.testessential.data.repository.AddressRepository
import com.xavidev.testessential.data.repository.SneakersRepository
import com.xavidev.testessential.data.source.local.entity.SneakerComplete
import com.xavidev.testessential.databinding.ActivitySaleOrderBinding
import com.xavidev.testessential.utils.ViewModelFactory
import com.xavidev.testessential.utils.toast

class SaleOrderActivity : AppCompatActivity() {

    companion object {
        const val SNEAKER_ID_EXTRA = "SNEAKERS_IDS"
    }

    private val viewModel: SaleViewModel by viewModels {
        ViewModelFactory(
            sneakersRepository = (this.applicationContext as SneakersApplication).sneakersRepository,
            addressRepository = (this.applicationContext as SneakersApplication).addressRepository,
            owner = this
        )
    }

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivitySaleOrderBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getExtras()
    }

    private fun getExtras() {
        val extras = intent.extras
        val sneakerIds = extras?.getStringArray(SNEAKER_ID_EXTRA)
        sneakerIds?.let { ids -> viewModel.setSneakerIds(ids.toList()) }
    }
}