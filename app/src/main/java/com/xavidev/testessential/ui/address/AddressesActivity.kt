package com.xavidev.testessential.ui.address

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.xavidev.testessential.R
import com.xavidev.testessential.SneakersApplication
import com.xavidev.testessential.databinding.ActivityAddressesBinding
import com.xavidev.testessential.ui.addEditAddress.AddressFormFragment
import com.xavidev.testessential.utils.ViewModelFactory

class AddressesActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityAddressesBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<AddressViewModel> {
        ViewModelFactory(
            addressRepository = (this.applicationContext as SneakersApplication).addressRepository,
            owner = this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            lifecycleOwner = this@AddressesActivity
            vm = viewModel
        }

        binding.tbrAddresses.setNavigationOnClickListener {
            finish()
        }

        binding.btnAddAddress.setOnClickListener {
            val bundle = bundleOf()
            bundle.putString(AddressFormFragment.ADDRESS_ID_EXTRA, null)
            val fragment = AddressFormFragment()
            fragment.arguments = bundle
            fragment.show(supportFragmentManager, AddressFormFragment.TAG)
        }
    }
}