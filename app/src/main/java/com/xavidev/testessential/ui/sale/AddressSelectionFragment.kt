package com.xavidev.testessential.ui.sale

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.xavidev.testessential.R
import com.xavidev.testessential.data.entity.Address
import com.xavidev.testessential.databinding.FragmentAddressSelectionBinding
import com.xavidev.testessential.ui.sale.adapters.AddressSelectionAdapter


class AddressSelectionFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentAddressSelectionBinding.inflate(layoutInflater)
    }

    private val viewModel: SaleViewModel by viewModels()

    private val addressSelectionAdapter = AddressSelectionAdapter(object : (Address, Int) -> Unit {
        override fun invoke(address: Address, pos: Int) {

        }
    })

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

        binding.btnSelectAddress.setOnClickListener {
            // Do something here
            viewModel.navigateTo(
                view,
                R.id.action_addressSelectionFragment2_to_orderAddressFragment2
            )
        }

        binding.tbrSelectAddress.setNavigationOnClickListener {
            viewModel.navigateTo(
                view,
                R.id.action_addressSelectionFragment2_to_orderAddressFragment2
            )
        }
    }

}