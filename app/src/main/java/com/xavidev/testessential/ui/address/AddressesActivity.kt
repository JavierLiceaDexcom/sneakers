package com.xavidev.testessential.ui.address

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.xavidev.testessential.R
import com.xavidev.testessential.SneakersApplication
import com.xavidev.testessential.databinding.ActivityAddressesBinding
import com.xavidev.testessential.ui.addEditAddress.AddressFormFragment
import com.xavidev.testessential.ui.addEditAddress.OnAddressSavedListener
import com.xavidev.testessential.ui.address.adapters.AddressesAdapter
import com.xavidev.testessential.utils.EventObserver
import com.xavidev.testessential.utils.ViewModelFactory
import com.xavidev.testessential.utils.showAlertDialog
import com.xavidev.testessential.utils.toast

class AddressesActivity : AppCompatActivity(), OnAddressSavedListener {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityAddressesBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<AddressViewModel> {
        ViewModelFactory(
            addressRepository = (this.applicationContext as SneakersApplication).addressRepository,
            owner = this
        )
    }

    private val addressItemListener = object : (String) -> Unit {
        override fun invoke(id: String) {
            openAddEditDialog(id)
        }
    }

    private val defaultAddressListener = object : (String) -> Unit {
        override fun invoke(id: String) {
            setAddressAsDefault(id)
        }
    }

    private fun setAddressAsDefault(addressId: String) {
        showAlertDialog(
            titleId = R.string.text_default_address_title,
            messageId = R.string.text_default_address_message,
            onAccept = object : () -> Unit {
                override fun invoke() {
                    viewModel.setDefaultAddress(addressId)
                }
            })
    }

    private val editAddressListener = object : (String) -> Unit {
        override fun invoke(id: String) {
            openAddEditDialog(id)
        }
    }

    private val removeAddressListener = object : (String) -> Unit {
        override fun invoke(id: String) {
            toast("Remove address with ID: $id")
        }
    }

    private val addressesAdapter =
        AddressesAdapter(editAddressListener, defaultAddressListener, removeAddressListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            lifecycleOwner = this@AddressesActivity
            vm = viewModel
        }

        setAddressesList()
        handleListeners()
        viewModel.defaultAddressUpdatedEvent.observe(this, EventObserver {
            viewModel.getAddresses()
        })
    }

    private fun setAddressesList() {
        viewModel.getAddresses()
        binding.rvAddresses.apply {
            adapter = addressesAdapter
            layoutManager = LinearLayoutManager(this@AddressesActivity)
        }

        viewModel.addressesList.observe(this) { list ->
            addressesAdapter.submitList(list)
        }
    }

    private fun handleListeners() = with(binding) {
        tbrAddresses.setNavigationOnClickListener {
            finish()
        }

        btnAddAddress.setOnClickListener {
            openAddEditDialog(null)
        }
    }

    private fun openAddEditDialog(addressId: String?) {
        val bundle = bundleOf()
        bundle.putString(AddressFormFragment.ADDRESS_ID_EXTRA, addressId)
        val fragment = AddressFormFragment()
        fragment.arguments = bundle
        fragment.show(supportFragmentManager, AddressFormFragment.TAG)
    }

    override fun onSaved() {
        viewModel.getAddresses()
    }
}