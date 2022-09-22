package com.xavidev.testessential.ui.address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.xavidev.testessential.R
import com.xavidev.testessential.databinding.FragmentAddressesBinding

class AddressesFragment : Fragment() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentAddressesBinding.inflate(layoutInflater)
    }

    private val viewModel: AddressViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }

        binding.tbrAddresses.setNavigationOnClickListener {
            viewModel.navigateTo(view, R.id.action_addressesFragment_to_profileFragment)
        }
    }
}